package solver.probabilitySolver;

import controllers.main.assets.NumberRounder;
import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.game.Game;
import gameLogic.player.Player;
import lombok.Getter;
import lombok.Setter;
import solver.Algorithm;
import solver.algorithms.principalVariationSearch.PVSWithCutoff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static gameLogic.game.Game.getGame;
import static gameLogic.game.GameConstants.*;
import static java.lang.Math.max;

@Getter
public class ProbabilitySolver {

    private List<CardProbability>[] lastProbabilities;
    private List<CardProbability>[] lastProbabilitiesSums;
    @Setter
    private int lostTricksNumber;

    public ProbabilitySolver() {
        lostTricksNumber = 0;
    }

    public List<CardProbability>[] solve(List<Card>[] cards, Card[] playedCards,
                                         List<Card> remainingCards, Player startingPlayer) {
        List<Card> playedCardsList = getPlayedCardList(playedCards, startingPlayer);
        Player currentPlayer = Player.values()[(startingPlayer.ordinal() + playedCardsList.size()) % PLAYER_NUMBER];
        int numberOfCardsToTake = max(cards[0].size(), cards[2].size());
        List<CardProbability>[] probabilities = new List[MIN_CARDS_PER_PLAYER + 1];
        for (int i = 0; i < MIN_CARDS_PER_PLAYER + 1; i++) {
            probabilities[i] = new ArrayList<>();
        }
        Card prevCard = null;
        for (int i = 0; i < cards[currentPlayer.ordinal()].size(); i++) {
            Card card = cards[currentPlayer.ordinal()].get(i);
            if (prevCard != null && prevCard.getId() + 1 == card.getId()) {
                for (int j = 0; j < MIN_CARDS_PER_PLAYER + 1; j++) {
                    probabilities[j].add(new CardProbability(card,
                            probabilities[j].get(probabilities[j].size() - 1).getProbability()));
                }
            } else {
                int[] numberOfSuccessfulGames = new int[MIN_CARDS_PER_PLAYER + 1];
                Arrays.fill(numberOfSuccessfulGames, 0);
                for (int j = 0; j < Math.pow(2, remainingCards.size()); j++) {
                    Game game = prepareGame(getCopyOfCards(cards), playedCards, new ArrayList<>(playedCardsList),
                            remainingCards, startingPlayer, currentPlayer, j);
                    game.playCard(card);
                    if (game.hasRoundEnded()) {
                        game.summarizeRound();
                    }
                    Algorithm algorithm = new PVSWithCutoff();
                    int taken = algorithm.solve(game).getPoints()[0];
                    if (numberOfCardsToTake - taken <= MAX_CARDS_PER_PLAYER) {
                        numberOfSuccessfulGames[numberOfCardsToTake - taken]++;
                    }
                }
                for (int j = 0; j < MIN_CARDS_PER_PLAYER + 1; j++) {
                    double probability = (numberOfSuccessfulGames[j] / Math.pow(2, remainingCards.size()));
                    double probabilityPercents = NumberRounder.round(100 * probability, 2);
                    probabilities[j].add(new CardProbability(card, probabilityPercents));
                }
            }
            prevCard = card;
        }
        lastProbabilities = probabilities;
        lastProbabilitiesSums = new List[MIN_CARDS_PER_PLAYER + 1];
        for (int i = 0; i < MIN_CARDS_PER_PLAYER + 1; i++) {
            lastProbabilitiesSums[i] = new ArrayList<>();
        }
        for(int i = 0; i < lastProbabilities.length; i++) {
            for(int j = 0; j < lastProbabilities[i].size(); j++) {
                double prevProbability = i > 0 ? lastProbabilitiesSums[i - 1].get(j).getProbability() : 0;
                double probability = prevProbability + lastProbabilities[i].get(j).getProbability();
                double roundedProbability = Math.min(100.00, probability);
                lastProbabilitiesSums[i].add(new CardProbability(lastProbabilities[i].get(j).getCard(), roundedProbability));
            }
        }
        return probabilities;
    }

    private List<Card> getPlayedCardList(Card[] playedCards, Player startingPlayer) {
        List<Card> playedCardsList = new ArrayList<>();
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            if (playedCards[(i + startingPlayer.ordinal()) % PLAYER_NUMBER] != null) {
                playedCardsList.add(playedCards[(i + startingPlayer.ordinal()) % PLAYER_NUMBER]);
            } else {
                break;
            }
        }
        return playedCardsList;
    }

    private Game prepareGame(List<Card>[] cards, Card[] playedCards, List<Card> playedCardsList,
                             List<Card> remainingCards, Player startingPlayer, Player currentPlayer, int cardDivisor) {
        for (Card remainingCard : remainingCards) {
            cards[1 + 2 * (cardDivisor % 2)].add(remainingCard);
            cardDivisor /= 2;
        }
        int maxNumberOfCards = getMaxNumberOfCards(cards);
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            while (cards[i].size() < maxNumberOfCards) {
                cards[i].add(new Card(i % 2));
            }
            if (playedCards[i] != null) {
                int finalI = i;
                cards[i] = cards[i].stream()
                        .filter(card -> card.getId() != playedCards[finalI].getId())
                        .toList();
            }
        }
        Game game = getGame();
        game.initializeGame(Color.HEART, cards, playedCardsList, startingPlayer, currentPlayer);
        return game;
    }

    private int getMaxNumberOfCards(List<Card>[] cards) {
        return Arrays.stream(cards)
                .map(List::size)
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    private List<Card>[] getCopyOfCards(List<Card>[] cards) {
        List<Card>[] copyOfCards = new List[PLAYER_NUMBER];
        for (int j = 0; j < PLAYER_NUMBER; j++) {
            copyOfCards[j] = new ArrayList<>();
            for (Card copiedCard : cards[j]) {
                copyOfCards[j].add(copiedCard);
            }
        }
        return copyOfCards;
    }

}

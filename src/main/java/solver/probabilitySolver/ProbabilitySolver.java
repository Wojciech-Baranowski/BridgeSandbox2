package solver.probabilitySolver;

import controllers.main.assets.NumberRounder;
import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.game.Game;
import gameLogic.player.Player;
import solver.Algorithm;
import solver.result.Result;

import java.util.*;

import static gameLogic.game.Game.getGame;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class ProbabilitySolver {

    private Algorithm algorithm;

    public ProbabilitySolver(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public List<CardProbability> solve(List<Card>[] cards, Card[] playedCards,
                                       List<Card> remainingCards, Player startingPlayer) {
        List<Card> playedCardsList = getPlayedCardList(playedCards, startingPlayer);
        Player currentPlayer = Player.values()[(startingPlayer.ordinal() + playedCardsList.size()) % PLAYER_NUMBER];
        List<CardProbability> probabilities = new ArrayList<>();
        Card prevCard = null;
        for(int i = 0; i < cards[currentPlayer.ordinal()].size(); i++) {
            Card card = cards[currentPlayer.ordinal()].get(i);
            if(prevCard != null && prevCard.getId() + 1 == card.getId()) {
                probabilities.add(
                        new CardProbability(card, probabilities.get(probabilities.size() - 1).getProbability()));
            } else {
                int numberOfSuccessfulGames = 0;
                for(int j = 0; j < Math.pow(2, remainingCards.size()); j++) {
                    Game game = prepareGame(getCopyOfCards(cards), playedCards, new ArrayList<>(playedCardsList),
                            remainingCards, startingPlayer, currentPlayer, j);
                    int maxNumberOfCards = getMaxNumberOfCards(game.getCards());
                    game.playCard(card);
                    if(game.hasRoundEnded()) {
                        game.summarizeRound();
                    }
                    algorithm.preprocessing();
                    Result result = algorithm.solve(game);
                    if(result.getPoints()[0] >= maxNumberOfCards) {
                        numberOfSuccessfulGames++;
                    }
                }
                double probability = NumberRounder.round(
                        100 * (numberOfSuccessfulGames / Math.pow(2, remainingCards.size())), 3);
                probabilities.add(new CardProbability(card, probability));
            }
            prevCard = card;
        }
        return probabilities;
    }

    private List<Card> getPlayedCardList(Card[] playedCards, Player startingPlayer) {
        List<Card> playedCardsList = new ArrayList<>();
        for(int i = 0; i < PLAYER_NUMBER; i++) {
            if(playedCards[(i + startingPlayer.ordinal()) % PLAYER_NUMBER] != null) {
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
        for(int i = 0; i < PLAYER_NUMBER; i++) {
            while (cards[i].size() < maxNumberOfCards) {
                cards[i].add(new Card(i % 2));
            }
            if(playedCards[i] != null) {
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
        for(int j = 0; j < PLAYER_NUMBER; j++) {
            copyOfCards[j] = new ArrayList<>();
            for(Card copiedCard : cards[j]) {
                copyOfCards[j].add(copiedCard);
            }
        }
        return copyOfCards;
    }

}

package solver.algorithms;

import gameLogic.game.Game;

import java.util.Arrays;
import java.util.Collection;

import static gameLogic.game.GameConstants.FIGURE_NUMBER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class BaseNode {

    public static byte allCardsNumber;
    public byte[][] cards;
    public byte[][] allOutcomeCards;
    public byte[] cardsSize;
    public byte[] playedCards;
    public byte[] allPlayedCards;
    public byte playedCardsSize;
    public byte startingPlayer;
    public byte currentPlayer;
    public byte atu;
    public byte nsPoints;
    public byte depth;

    public BaseNode(Game game) {
        allCardsNumber = (byte) (Arrays.stream(game.getCards())
                .map(Collection::size)
                .reduce(0, Integer::sum)
                .byteValue() + game.getPlayedCards().size());
        setCards(game);
        setPlayedCards(game);
        setPlayerData(game);
    }

    public boolean isCardValid(byte index) {
        if (cards[currentPlayer][index] / FIGURE_NUMBER != playedCards[startingPlayer] / FIGURE_NUMBER
                && playedCardsSize > 0) {
            for (byte i = 0; i < cardsSize[currentPlayer]; i++) {
                if (cards[currentPlayer][i] / FIGURE_NUMBER == playedCards[startingPlayer] / FIGURE_NUMBER) {
                    return false;
                }
            }
        }
        return true;
    }

    public byte winningPlayerIndex() {
        byte winnerIndex = startingPlayer;
        for (byte i = 0; i < PLAYER_NUMBER; i++) {
            if (i != startingPlayer) {
                if (playedCards[i] / FIGURE_NUMBER == playedCards[winnerIndex] / FIGURE_NUMBER) {
                    if (playedCards[i] > playedCards[winnerIndex]) {
                        winnerIndex = i;
                    }
                } else if (playedCards[i] / FIGURE_NUMBER == atu) {
                    winnerIndex = i;
                }
            }
        }
        return winnerIndex;
    }

    public void playCard(byte index) {
        allPlayedCards[depth] = playedCards[currentPlayer] = cards[currentPlayer][index];;
        depth++;
        shiftCards(index);
        cardsSize[currentPlayer]--;
        playedCardsSize++;
        currentPlayer = (byte) ((currentPlayer + 1) & 3);
    }

    public void summarize() {
        startingPlayer = currentPlayer = winningPlayerIndex();
        nsPoints += ((startingPlayer & 1) == 0) ? 1 : 0;
        playedCardsSize = 0;
    }

    public void revertPlayCard(byte cardPlace) {
        currentPlayer = (byte) ((currentPlayer - 1 + PLAYER_NUMBER) & 3);
        playedCardsSize--;
        depth--;
        insertCardBack(playedCards[currentPlayer], cardPlace);
        cardsSize[currentPlayer]++;
    }

    public void revertSummarize(byte[] playedCards, byte lastStartingPlayer) {
        playedCardsSize = PLAYER_NUMBER;
        nsPoints -= ((startingPlayer & 1) == 0) ? 1 : 0;
        currentPlayer = startingPlayer = lastStartingPlayer;
        for (byte i = 0; i < PLAYER_NUMBER; i++) {
            this.playedCards[i] = playedCards[i];
        }
    }

    private void shiftCards(byte index) {
        for (byte i = index; i < cards[currentPlayer].length - 1; i++) {
            cards[currentPlayer][i] = cards[currentPlayer][i + 1];
        }
    }

    private void insertCardBack(byte card, byte cardPlace) {
        for(byte i = cardsSize[currentPlayer]; i > cardPlace; i--) {
            cards[currentPlayer][i] = cards[currentPlayer][i - 1];
        }
        cards[currentPlayer][cardPlace] = card;
    }

    private void setCards(Game game) {
        cards = new byte[PLAYER_NUMBER][];
        cardsSize = new byte[PLAYER_NUMBER];
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            cards[i] = new byte[game.getCards()[i].size()];
            for (int j = 0; j < game.getCards()[i].size(); j++) {
                cards[i][j] = (byte) game.getCards()[i].get(j).getId();
            }
            Arrays.sort(cards[i]);
            cardsSize[i] = (byte) cards[i].length;
        }
    }

    private void setPlayedCards(Game game) {
        playedCards = new byte[PLAYER_NUMBER];
        allPlayedCards = new byte[allCardsNumber];
        allOutcomeCards = new byte[game.getStartingNumberOfCardsPerPlayer() + 1][allCardsNumber];
        for(int i = 0; i < game.getStartingNumberOfCardsPerPlayer() + 1; i++) {
            allOutcomeCards[i][0] = -1;
        }
        for (int i = 0; i < game.getPlayedCards().size(); i++) {
            int cardsOwnerOrdinal = (i + game.getStartingPlayer().ordinal()) % PLAYER_NUMBER;
            playedCards[cardsOwnerOrdinal] = (byte) game.getPlayedCards().get(i).getId();
        }
        playedCardsSize = (byte) game.getPlayedCards().size();
    }

    private void setPlayerData(Game game) {
        startingPlayer = (byte) game.getStartingPlayer().ordinal();
        currentPlayer = (byte) game.getCurrentPlayer().ordinal();
        atu = (byte) game.getAtu().ordinal();
        nsPoints = (byte) game.getPoints()[0];
    }

}

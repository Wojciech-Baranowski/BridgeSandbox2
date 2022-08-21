package solver.algorithms.minmax;

import gameLogic.game.Game;

import java.util.Arrays;
import java.util.Collection;

import static gameLogic.game.GameConstants.*;

public class Node {

    public static byte allCardsNumber;
    public byte[][] cards;
    public byte[] cardsSize;
    public byte[] playedCards;
    public byte playedCardsSize;
    public byte startingPlayer;
    public byte currentPlayer;
    public byte atu;
    public byte nsPoints;
    public byte depth;
    public boolean maximizing;

    Node(Game game) {
        allCardsNumber = Response.allCardsNumber = (byte) (Arrays.stream(game.getCards())
                                .map(Collection::size)
                                .reduce(0, Integer::sum)
                                .byteValue() + game.getPlayedCards().size());
        depth = (byte) game.getPlayedCards().size();
        maximizing = game.getCurrentPlayer().ordinal() % 2 == 0;
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
        for(byte i = 0; i < PLAYER_NUMBER; i++) {
            if(i != startingPlayer) {
                if(playedCards[i] / FIGURE_NUMBER == playedCards[winnerIndex] / FIGURE_NUMBER) {
                    if(playedCards[i] > playedCards[winnerIndex]) {
                        winnerIndex = i;
                    }
                } else if(playedCards[i] / FIGURE_NUMBER == atu) {
                    winnerIndex = i;
                }
            }
        }
        return winnerIndex;
    }

    public void playCard(byte index) {
        playedCards[currentPlayer] = cards[currentPlayer][index];
        shiftCards(index);
        cardsSize[currentPlayer]--;
        playedCardsSize++;
        currentPlayer = (byte) ((currentPlayer + 1) & 3);
        maximizing = !maximizing;
        depth++;
    }

    public void summarize() {
        startingPlayer = currentPlayer = winningPlayerIndex();
        nsPoints += ((startingPlayer & 1) == 0) ? 1 : 0;
        playedCardsSize = 0;
    }

    public void revertPlayCard() {
        depth--;
        maximizing = !maximizing;
        currentPlayer = (byte) ((currentPlayer - 1 + PLAYER_NUMBER) & 3);
        playedCardsSize--;
        insertCardBack(playedCards[currentPlayer]);
        cardsSize[currentPlayer]++;
    }

    public void revertSummarize(byte[] playedCards, byte lastStartingPlayer) {
        playedCardsSize = PLAYER_NUMBER;
        nsPoints -= ((startingPlayer & 1) == 0) ? 1 : 0;
        currentPlayer = startingPlayer = lastStartingPlayer;
        for(byte i = 0; i < PLAYER_NUMBER; i++) {
            this.playedCards[i] = playedCards[i];
        }
    }

    private void shiftCards(byte index) {
        for (byte i = index; i < cards[currentPlayer].length - 1; i++) {
            cards[currentPlayer][i] = cards[currentPlayer][i + 1];
        }
    }

    private void insertCardBack(byte card) {
        byte index = (byte) (cardsSize[currentPlayer] - 1);
        while(index >= 0) {
            if(cards[currentPlayer][index] < card) {
                cards[currentPlayer][index + 1] = card;
                return;
            }
            cards[currentPlayer][index + 1] = cards[currentPlayer][index];
            index--;
        }
        cards[currentPlayer][0] = card;
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

package solver.algorithms.minmax;

import gameLogic.game.Game;

import java.util.Arrays;
import java.util.Collection;

import static gameLogic.game.GameConstants.PLAYER_NUMBER;

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
        allCardsNumber = Response.allCardsNumber = Arrays.stream(game.getCards())
                .map(Collection::size)
                .reduce(0, Integer::sum)
                .byteValue();
        depth = 0;
        maximizing = (game.getCurrentPlayer().ordinal() % 2 == 0);
        setCards(game);
        setPlayedCards(game);
        setPlayerData(game);
    }

    public boolean isCardValid(byte index) {
        return false;
    }

    public byte winningPlayerIndex() {
        return 0;
    }

    public void playCard(byte index) {
        playedCards[currentPlayer] = cards[currentPlayer][index];
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

    public void revertPlayCard() {
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
        for (byte i = index; i < cards[currentPlayer].length - 2; i++) {
            cards[currentPlayer][i] = cards[currentPlayer][i + 1];
        }
    }

    private void insertCardBack(byte card) {
        byte index = cardsSize[currentPlayer];
        while(index > 0) {
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
        cards = new byte[PLAYER_NUMBER][allCardsNumber];
        cardsSize = new byte[PLAYER_NUMBER];
        for (int i = 0; i < PLAYER_NUMBER; i++) {
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
        playedCardsSize = (byte) playedCards.length;
    }

    private void setPlayerData(Game game) {
        startingPlayer = (byte) game.getStartingPlayer().ordinal();
        currentPlayer = (byte) game.getCurrentPlayer().ordinal();
        atu = (byte) game.getAtu().ordinal();
        nsPoints = (byte) game.getPoints()[0];
    }

}

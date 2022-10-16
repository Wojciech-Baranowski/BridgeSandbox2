package solver.algorithms.principalVariationSearch;

import gameLogic.game.Game;

import static gameLogic.game.GameConstants.*;

public class PVSLookupNode extends PVSNode {

    public static byte[][][][][][] winningPlayerTable;
    public byte[][] cardsInColor;

    public PVSLookupNode(Game game) {
        super(game);
        cardsInColor = new byte[PLAYER_NUMBER][COLOR_NUMBER];
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            cardsInColor[i][0] = cardsInColor[i][1] = cardsInColor[i][2] = cardsInColor[i][3] = 0;
            for (int j = 0; j < cardsSize[i]; j++) {
                cardsInColor[i][cards[i][j] / FIGURE_NUMBER]++;
            }
        }
    }

    public void summarize() {
        startingPlayer = currentPlayer = winningPlayerTable
                [atu]
                [startingPlayer]
                [playedCards[0]]
                [playedCards[1]]
                [playedCards[2]]
                [playedCards[3]];
        nsPoints += ((startingPlayer & 1) == 0) ? 1 : 0;
        playedCardsSize = 0;
    }

    public static void initializeWinningPlayerLookupTable() {
        winningPlayerTable = new byte[COLOR_NUMBER + 1][PLAYER_NUMBER][DECK_SIZE][DECK_SIZE][DECK_SIZE][DECK_SIZE];
        for (byte i = 0; i < COLOR_NUMBER + 1; i++) {
            for (byte j = 0; j < PLAYER_NUMBER; j++) {
                for (byte k = 0; k < DECK_SIZE; k++) {
                    for (byte l = 0; l < DECK_SIZE; l++) {
                        for (byte m = 0; m < DECK_SIZE; m++) {
                            for (byte n = 0; n < DECK_SIZE; n++) {
                                winningPlayerTable[i][j][k][l][m][n] = chooseWinner(i, j, new byte[]{k, l, m, n});
                            }
                        }
                    }
                }
            }
        }
    }

    private static byte chooseWinner(byte atu, byte startingPlayer, byte[] cards) {
        byte winnerIndex = startingPlayer;
        for (byte i = 0; i < PLAYER_NUMBER; i++) {
            if (i != startingPlayer) {
                if (cards[i] / FIGURE_NUMBER == cards[winnerIndex] / FIGURE_NUMBER) {
                    if (cards[i] > cards[winnerIndex]) {
                        winnerIndex = i;
                    }
                } else if (cards[i] / FIGURE_NUMBER == atu) {
                    winnerIndex = i;
                }
            }
        }
        return winnerIndex;
    }

    public boolean isCardValid(byte index) {
        return (cards[currentPlayer][index] / FIGURE_NUMBER == playedCards[startingPlayer] / FIGURE_NUMBER
                || playedCardsSize == 0
                || cardsInColor[currentPlayer][playedCards[startingPlayer] / FIGURE_NUMBER] == 0);
    }

    public void playCard(byte index, byte alpha, byte beta) {
        allPlayedCards[depth] = playedCards[currentPlayer] = cards[currentPlayer][index];
        depth++;
        shiftCards(index);
        cardsInColor[currentPlayer][playedCards[currentPlayer] / FIGURE_NUMBER]--;
        cardsSize[currentPlayer]--;
        playedCardsSize++;
        currentPlayer = (byte) ((currentPlayer + 1) & 3);
        color *= -1;
        this.alpha = alpha;
        this.beta = beta;
    }

    public void revertPlayCard(byte cardPlace) {
        currentPlayer = (byte) ((currentPlayer - 1 + PLAYER_NUMBER) & 3);
        playedCardsSize--;
        depth--;
        cardsInColor[currentPlayer][playedCards[currentPlayer] / FIGURE_NUMBER]++;
        insertCardBack(playedCards[currentPlayer], cardPlace);
        cardsSize[currentPlayer]++;
        color *= -1;
    }

    public void playDummyCard() {
        color *= -1;
        byte tempAlpha = alpha;
        alpha = (byte) -beta;
        beta = (byte) -tempAlpha;
    }

    public void revertPlayDummyCard() {
        color *= -1;
        byte tempAlpha = alpha;
        alpha = (byte) -beta;
        beta = (byte) -tempAlpha;
    }

    public boolean isSummarizeParity(byte lastStartingPlayer) {
        return (lastStartingPlayer & 1) != (startingPlayer & 1);
    }

}

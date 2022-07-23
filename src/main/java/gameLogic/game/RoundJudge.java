package gameLogic.game;

import gameLogic.Player;
import gameLogic.card.Card;
import gameLogic.card.Color;

import java.util.Comparator;
import java.util.List;

import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static gameLogic.utils.ModuloOperator.modAdd;

public class RoundJudge {

    RoundJudge() {
    }

    Player chooseWinningPlayer(List<Card> playedCards, Player startingPlayer, Color atu) {
        Card winningCard = (isAnyCardAtu(playedCards, atu))
                ? chooseWinningCardIfAtuPresent(playedCards, atu)
                : chooseWinningCardIfAtuNotPresent(playedCards, startingPlayer, atu);
        return findOwnerOfWinningCard(playedCards, startingPlayer, winningCard);
    }

    private boolean isAnyCardAtu(List<Card> playedCards, Color atu) {
        return playedCards.stream()
                .map(Card::getColor)
                .anyMatch(c -> c.equals(atu));
    }

    private Card chooseWinningCardIfAtuPresent(List<Card> playedCards, Color atu) {
        return playedCards.stream()
                .filter(c -> c.getColor().equals(atu))
                .max(Comparator.comparingInt(c -> c.getFigure().ordinal()))
                .orElse(null);
    }

    private Card chooseWinningCardIfAtuNotPresent(List<Card> playedCards, Player startingPlayer, Color atu) {
        Color startingCardColor = playedCards.get(startingPlayer.ordinal()).getColor();
        return playedCards.stream()
                .filter(c -> c.getColor().equals(startingCardColor))
                .max(Comparator.comparingInt(c -> c.getFigure().ordinal()))
                .orElse(null);
    }

    private Player findOwnerOfWinningCard(List<Card> playedCards, Player startingPlayer, Card winningCard) {
        int indexOfWinningPlayer = getIndexOfWinningPlayer(playedCards, startingPlayer, winningCard);
        return Player.values()[indexOfWinningPlayer];
    }

    private int getIndexOfWinningPlayer(List<Card> playedCards, Player startingPlayer, Card winningCard) {
        int indexOfWinningCard = playedCards.indexOf(winningCard);
        int indexOfStartingPlayer = startingPlayer.ordinal();
        return modAdd(indexOfWinningCard, indexOfStartingPlayer, PLAYER_NUMBER);
    }

}

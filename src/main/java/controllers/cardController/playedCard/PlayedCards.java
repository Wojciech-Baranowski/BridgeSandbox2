package controllers.cardController.playedCard;

import controllers.backgroundController.HandCardSpace;
import controllers.backgroundController.PlayedCardSpace;
import engine.display.Drawable;
import gameLogic.card.Card;
import gameLogic.player.Player;

import java.util.HashSet;
import java.util.Set;

import static controllers.backgroundController.BackgroundController.getBackgroundController;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class PlayedCards {

    private final int[] xPos;
    private final int[] yPos;
    private final Set<PlayedCard> playedCards;

    public PlayedCards() {
        xPos = new int[PLAYER_NUMBER];
        yPos = new int[PLAYER_NUMBER];
        initializePositions();
        playedCards = new HashSet<>();
    }

    public void addPlayedCard(Card card, Player player) {
        PlayedCardSpace playedCardSpace = getBackgroundController().getPlayedCardSpace();
        int x = xPos[player.ordinal()];
        int y = yPos[player.ordinal()];
        PlayedCard playedCard = new PlayedCard(card, x, y);
        playedCards.add(playedCard);
        getScene().addObjectHigherThan(playedCard.getDrawable(), playedCardSpace.getPlayedCardSlots()[0]);
    }

    public void removePlayedCards() {
        for(PlayedCard playedCard : playedCards) {
            getScene().removeObject(playedCard.getDrawable());
        }
        playedCards.clear();
    }

    private void initializePositions() {
        Drawable[] playedCardSlots = getBackgroundController().getPlayedCardSpace().getPlayedCardSlots();
        int relativeXPos = 2;
        int relativeYPos = 2;
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            xPos[i] = playedCardSlots[i].getX() + relativeXPos;
            yPos[i] = playedCardSlots[i].getY() + relativeYPos;
        }
    }

}

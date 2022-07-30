package controllers.cardController.handCard;

import controllers.backgroundController.HandCardSpace;
import engine.display.Drawable;
import gameLogic.card.Card;
import gameLogic.player.Player;

import java.util.LinkedList;
import java.util.List;

import static controllers.backgroundController.BackgroundController.getBackgroundController;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.Game.getGame;
import static gameLogic.game.GameConstants.FIGURE_NUMBER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class HandCards {

    private final int[][] xPos;
    private final int[][] yPos;
    private final List<HandCard>[] handCards;

    public HandCards() {
        xPos = new int[PLAYER_NUMBER][FIGURE_NUMBER];
        yPos = new int[PLAYER_NUMBER][FIGURE_NUMBER];
        initializePositions();
        handCards = new List[PLAYER_NUMBER];
    }

    public void initializeHandsCards() {
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            handCards[i] = new LinkedList<>();
            initializeHandCards(i);
        }
    }

    public void removeHandCard(HandCard handCard, Player player) {
        handCards[player.ordinal()].remove(handCard);
        getScene().removeObject(handCard.getButton());
    }

    private void initializeHandCards(int i) {
        Player player = Player.values()[i];
        List<Card> playerCards = getGame().getCards()[i];
        HandCardSpace handCardSpace = getBackgroundController().getHandCardSpace();
        for (int j = 0; j < playerCards.size(); j++) {
            HandCard handCard = new HandCard(playerCards.get(j), player);
            handCard.move(xPos[i][j], yPos[i][j]);
            handCards[i].add(handCard);
            getScene().addObjectHigherThan(handCard.getButton(), (j == 0)
                    ? handCardSpace.getHandCardSlots()[i].getDrawable()
                    : handCards[i].get(j - 1).getButton());
        }
    }

    private void initializePositions() {
        Drawable[] handCardSlots = getBackgroundController().getHandCardSpace().getHandCardSlots();
        int cardSpread = 17;
        int relativeYPos = 2;
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            for (int j = 0; j < FIGURE_NUMBER; j++) {
                int relativeXPos = 2 + cardSpread * j;
                xPos[i][j] = handCardSlots[i].getX() + relativeXPos;
                yPos[i][j] = handCardSlots[i].getY() + relativeYPos;
            }
        }
    }

}

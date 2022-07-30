package controllers.cardController;

import controllers.backgroundController.HandCardSpace;
import engine.display.Drawable;
import gameLogic.card.Card;
import gameLogic.game.Game;
import gameLogic.player.Player;

import java.util.LinkedList;
import java.util.List;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.FIGURE_NUMBER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class HandCards {

    private final int[][] xPos;
    private final int[][] yPos;
    private final List<HandCard>[] handCards;

    public HandCards(Drawable[] handCardSlots) {
        xPos = new int[PLAYER_NUMBER][FIGURE_NUMBER];
        yPos = new int[PLAYER_NUMBER][FIGURE_NUMBER];
        initializePositions(handCardSlots);
        handCards = new List[PLAYER_NUMBER];
    }

    public void initializeHandsCards(Game game, HandCardSpace handCardSpace) {
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            handCards[i] = new LinkedList<>();
            initializeHandCards(game, handCardSpace, i);
        }
    }

    private void initializeHandCards(Game game, HandCardSpace handCardSpace, int i) {
        Player player = Player.values()[i];
        List<Card> playerCards = game.getCards()[i];
        for (int j = 0; j < playerCards.size(); j++) {
            HandCard handCard = new HandCard(playerCards.get(j), player, xPos[i][j], yPos[i][j]);
            handCards[i].add(handCard);
            getScene().addObjectHigherThan(handCard.getDrawable(), (j == 0)
                    ? handCardSpace.getHandCardSlots()[i].getDrawable()
                    : handCards[i].get(j - 1).getDrawable());
        }
    }

    private void initializePositions(Drawable[] handCardSlots) {
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

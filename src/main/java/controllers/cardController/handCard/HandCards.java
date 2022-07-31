package controllers.cardController.handCard;

import controllers.backgroundController.HandCardSpace;
import engine.display.Drawable;
import gameLogic.card.Card;
import gameLogic.player.Player;
import lombok.Setter;

import java.util.Comparator;
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

    @Setter
    private Comparator<Card> cardOrder;

    public HandCards() {
        xPos = new int[PLAYER_NUMBER][FIGURE_NUMBER];
        yPos = new int[PLAYER_NUMBER][FIGURE_NUMBER];
        cardOrder = Comparator.comparingInt(Card::getId);
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

    public void repositionPlayerCards(Player player) {
        List<HandCard> playerCards = handCards[player.ordinal()];
        for (int i = 0; i < playerCards.size(); i++) {
            int x = xPos[player.ordinal()][i];
            int y = yPos[player.ordinal()][i];
            playerCards.get(i).move(x, y);
        }
    }

    public void updateOverlays() {
        for(int i = 0; i < PLAYER_NUMBER; i++) {
            for(HandCard handCard : handCards[i]) {
                if(getGame().isMoveValid(handCard.getCard())) {
                    handCard.deactivateOverlay();
                } else {
                    handCard.activateOverlay();
                }
            }
        }
    }

    private void initializeHandCards(int handId) {
        List<Card> playerCards = getGame().getCards()[handId];
        playerCards.sort(cardOrder);
        HandCardSpace handCardSpace = getBackgroundController().getHandCardSpace();
        for (int j = 0; j < playerCards.size(); j++) {
            initializeHandCard(playerCards, handCardSpace, handId, j);
        }
    }

    private void initializeHandCard(List<Card> playerCards, HandCardSpace handCardSpace, int handId, int cardInHandId) {
        HandCard handCard = new HandCard(playerCards.get(cardInHandId));
        handCard.move(xPos[handId][cardInHandId], yPos[handId][cardInHandId]);
        handCards[handId].add(handCard);
        getScene().addObjectHigherThan(handCard.getButton(), (cardInHandId == 0)
                ? handCardSpace.getHandCardSlots()[handId].getDrawable()
                : handCards[handId].get(cardInHandId - 1).getButton());
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
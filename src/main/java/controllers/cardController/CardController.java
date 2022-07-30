package controllers.cardController;

import controllers.cardController.handCard.HandCard;
import controllers.cardController.handCard.HandCards;
import controllers.cardController.playedCard.PlayedCards;
import gameLogic.card.Card;
import gameLogic.player.Player;

public class CardController {

    private static CardController cardController;
    private final HandCards handCards;
    private final PlayedCards playedCards;

    private CardController() {
        handCards = new HandCards();
        playedCards = new PlayedCards();
    }

    public static CardController getCardController() {
        if (cardController == null) {
            cardController = new CardController();
        }
        return cardController;
    }

    public void initializeHandCards() {
        handCards.initializeHandsCards();
    }

    public void removeHandCard(HandCard handCard, Player player) {
        handCards.removeHandCard(handCard, player);
    }

    public void repositionPlayerCards(Player player) {
        handCards.repositionPlayerCards(player);
    }

    public void updateOverlays() {
        handCards.updateOverlays();
    }

    public void addPlayedCard(Card card, Player player) {
        playedCards.addPlayedCard(card, player);
    }

    public void removePlayedCards() {
        playedCards.removePlayedCards();
    }

}

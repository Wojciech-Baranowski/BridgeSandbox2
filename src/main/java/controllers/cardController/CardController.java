package controllers.cardController;

import controllers.backgroundController.HandCardSpace;
import controllers.cardController.handCard.HandCard;
import controllers.cardController.handCard.HandCards;
import controllers.cardController.playedCard.PlayedCards;
import gameLogic.card.Card;
import gameLogic.game.Game;
import gameLogic.player.Player;

import static controllers.backgroundController.BackgroundController.getBackgroundController;
import static gameLogic.game.Game.getGame;

public class CardController {

    private static CardController cardController;
    private final HandCards handCards;
    private final PlayedCards playedCards;
    private CardController() {
        handCards = new HandCards();
        playedCards = new PlayedCards();
    }

    public static CardController getCardController() {
        if(cardController == null) {
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

    public void addPlayedCard(Card card, Player player) {
        playedCards.addPlayedCard(card, player);
    }

    public void removePlayedCards() {
        playedCards.removePlayedCards();
    }

}

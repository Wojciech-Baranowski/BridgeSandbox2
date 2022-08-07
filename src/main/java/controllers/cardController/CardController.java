package controllers.cardController;

import controllers.cardController.handCard.HandCard;
import controllers.cardController.handCard.HandCards;
import controllers.cardController.playedCard.PlayedCard;
import controllers.cardController.playedCard.PlayedCards;
import engine.display.DrawableFactory;
import gameLogic.card.Card;
import gameLogic.player.Player;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

import static engine.display.DisplayBean.getDisplay;
import static engine.scene.SceneBean.getScene;

public class CardController {

    private static final DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
    private static CardController cardController;
    private final HandCards handCards;
    private final PlayedCards playedCards;

    private CardController() {
        handCards = new HandCards(drawableFactory);
        playedCards = new PlayedCards();
    }

    public static CardController getCardController() {
        if (cardController == null) {
            cardController = new CardController();
        }
        return cardController;
    }

    public void initialize() {
        handCards.initializeHandsCards();
        handCards.updateOverlays(Player.N);
    }

    public void reinitialize() {
        removeAllCards();
        initialize();
    }

    public void removeHandCard(HandCard handCard, Player player) {
        handCards.removeHandCard(handCard, player);
    }

    public void repositionPlayerCards(Player player) {
        handCards.repositionPlayerCards(player);
    }

    public void updateOverlays(Player player) {
        handCards.updateOverlays(player);
    }

    public void addPlayedCard(Card card, Player player) {
        playedCards.addPlayedCard(card, player);
    }

    public void removePlayedCards() {
        playedCards.removePlayedCards();
    }

    public Comparator<Card> getCardOrder() {
        return handCards.getCardOrder();
    }

    public void setCardOrder(Comparator<Card> cardOrder) {
        handCards.setCardOrder(cardOrder);
    }

    private void removeAllCards() {
        for (HandCard handCard : Stream.of(handCards.getHandCards()).flatMap(Collection::stream).toList()) {
            getScene().removeObject(handCard.getButton());
            getScene().removeObject(handCard.getInactiveOverlay().getDrawable());
        }
        for (PlayedCard playedCard : Stream.of(playedCards.getPlayedCards()).flatMap(Collection::stream).toList()) {
            getScene().removeObject(playedCard.getDrawable());
        }
    }

}

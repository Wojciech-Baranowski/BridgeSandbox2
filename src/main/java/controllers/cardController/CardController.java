package controllers.cardController;

import controllers.cardController.handCard.HandCard;
import controllers.cardController.handCard.HandCards;
import controllers.cardController.playedCard.PlayedCard;
import controllers.cardController.playedCard.PlayedCards;
import engine.display.Drawable;
import gameLogic.card.Card;
import gameLogic.player.Player;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static controllers.main.assets.CardDrawables.getAllCardDrawables;
import static engine.scene.SceneBean.getScene;

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

    public void initialize() {
        handCards.initializeHandsCards();
        handCards.updateOverlays();
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

    public void updateOverlays() {
        handCards.updateOverlays();
    }

    public void addPlayedCard(Card card, Player player) {
        playedCards.addPlayedCard(card, player);
    }

    public void removePlayedCards() {
        playedCards.removePlayedCards();
    }

    private void removeAllCards() {
        for(HandCard handCard : Stream.of(handCards.getHandCards()).flatMap(Collection::stream).toList()) {
            getScene().removeObject(handCard.getButton());
            getScene().removeObject(handCard.getInactiveOverlay().getDrawable());
        }
        for(PlayedCard playedCard : Stream.of(playedCards.getPlayedCards()).flatMap(Collection::stream).toList()) {
            getScene().removeObject(playedCard.getDrawable());
        }
    }

}

package controllers.gameControllers.cardController;

import controllers.gameControllers.cardController.handCard.HandCard;
import controllers.gameControllers.cardController.handCard.HandCards;
import controllers.gameControllers.cardController.playedCard.PlayedCard;
import controllers.gameControllers.cardController.playedCard.PlayedCards;
import gameLogic.card.Card;
import gameLogic.player.Player;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

import static engine.display.DisplayBean.getDisplay;
import static engine.scene.SceneBean.getScene;

public class GameCardController {

    private static GameCardController gameCardController;
    private final HandCards handCards;
    private final PlayedCards playedCards;

    private GameCardController() {
        handCards = new HandCards(getDisplay().getDrawableFactory());
        playedCards = new PlayedCards();
    }

    public static GameCardController getGameCardController() {
        if (gameCardController == null) {
            gameCardController = new GameCardController();
            gameCardController.initialize();
        }
        return gameCardController;
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

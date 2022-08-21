package controllers.gameControllers.historyController;

import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import gameLogic.card.Card;
import gameLogic.game.Game;
import gameLogic.player.Player;
import lombok.Getter;
import solver.result.ResultRound;

import java.util.List;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

@Getter
public class HistoryEntry {

    private Drawable entry;
    private HistoryEntryOverlay overlay;
    private boolean predicted;

    public HistoryEntry(DrawableFactory drawableFactory, Drawable background, Game game, int entryId) {
        predicted = false;
        initializeEntryBackground(drawableFactory, background, game.getWinningPlayer(), entryId);
        initializeText(drawableFactory, background, game.getPlayedCards(), game.getStartingPlayer(), entryId);
        getScene().addObjectHigherThan(entry, background);
        int overlayFieldId = (game.getWinningPlayer().ordinal()
                - game.getStartingPlayer().ordinal() + PLAYER_NUMBER) % PLAYER_NUMBER;
        overlay = new HistoryEntryOverlay(drawableFactory, this, overlayFieldId);
    }

    public HistoryEntry(DrawableFactory drawableFactory, Drawable background, ResultRound resultRound, int entryId) {
        predicted = true;
        initializeEntryBackground(drawableFactory, background, resultRound.getWinningPlayer(), entryId);
        initializeText(drawableFactory, background, resultRound.getCards(), resultRound.getStartingPlayer(), entryId);
        getScene().addObjectHigherThan(entry, background);
        int overlayFieldId = (resultRound.getWinningPlayer().ordinal()
                - resultRound.getStartingPlayer().ordinal() + PLAYER_NUMBER) % PLAYER_NUMBER;
        overlay = new HistoryEntryOverlay(drawableFactory, this, overlayFieldId);
    }

    private void initializeEntryBackground(
            DrawableFactory drawableFactory, Drawable background, Player winningPlayer, int entryId) {
        entry = drawableFactory.makeFramedRectangle(
                background.getX() + entryId * 63 + 9,
                background.getY() + 508,
                60,
                33,
                2,
                (winningPlayer.ordinal() % 2 == 0) ? "pink" : "violet",
                "lightBlue");
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            Drawable drawable = drawableFactory.makeFramedRectangle(
                    background.getX() + entryId * 63 + 9,
                    background.getY() + 539 + 31 * i,
                    28,
                    33,
                    2,
                    (i % 2 == 0) ? "darkGray" : "lightGray",
                    "lightBlue");
            entry = new DrawableComposition(entry, drawable);

            drawable = drawableFactory.makeFramedRectangle(
                    background.getX() + entryId * 63 + 35,
                    background.getY() + 539 + 31 * i,
                    34,
                    33,
                    2,
                    (i % 2 == 0) ? "darkGray" : "lightGray",
                    "lightBlue");
            entry = new DrawableComposition(entry, drawable);
        }
    }

    private void initializeText(
            DrawableFactory drawableFactory, Drawable background, List<Card> cards, Player startingPlayer, int id) {
        Drawable drawable = drawableFactory.makeText(
                String.valueOf(id + 1),
                background.getX() + id * 63 + 30,
                background.getY() + 514,
                "HBE24",
                predicted ? "black" : "yellow");
        entry = new DrawableComposition(entry, drawable);
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            drawable = drawableFactory.makeText(
                    Player.values()[(startingPlayer.ordinal() + i) % PLAYER_NUMBER].getSymbolString(),
                    background.getX() + id * 63 + 13,
                    background.getY() + 539 + 31 * i + 6,
                    "HBE24",
                    "black");
            entry = new DrawableComposition(entry, drawable);

            Card playedCard = cards.get(i);
            drawable = drawableFactory.makeText(
                    playedCard.getFigure().getSymbolString() + playedCard.getColor().getSymbolString(),
                    background.getX() + id * 63 + 38,
                    background.getY() + 539 + 31 * i + 6,
                    "HBE24",
                    (playedCard.getId() < 13 || playedCard.getId() >= 39) ? "black" : "red");
            entry = new DrawableComposition(entry, drawable);
        }
    }
}

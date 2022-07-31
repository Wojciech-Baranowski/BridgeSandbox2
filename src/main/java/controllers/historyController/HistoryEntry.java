package controllers.historyController;

import engine.display.DisplayBean;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import gameLogic.card.Card;
import gameLogic.game.Game;
import gameLogic.player.Player;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.Game.getGame;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class HistoryEntry {

    @Getter
    private Drawable entry;
    @Getter
    private HistoryEntryOverlay overlay;

    HistoryEntry(DrawableFactory drawableFactory, Game game, Drawable background, int entryId) {
        initializeEntryBackground(drawableFactory, background, entryId);
        initializeText(drawableFactory, game, background, entryId);
        getScene().addObjectHigherThan(entry, background);
        overlay = new HistoryEntryOverlay(drawableFactory,
                this, game.getPlayedCards().indexOf(game.getWinningCard()));
    }

    private void initializeEntryBackground(DrawableFactory drawableFactory, Drawable background, int entryId) {
        entry = drawableFactory.makeFramedRectangle(
                background.getX() + entryId * 64 + 7,
                background.getY() + 509,
                60,
                33,
                2,
                "lightGray",
                "lightBlue");
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            Drawable drawable = drawableFactory.makeFramedRectangle(
                    background.getX() + entryId * 64 + 7,
                    background.getY() + 540 + 31 * i,
                    28,
                    33,
                    2,
                    (i % 2 == 0) ? "gray" : "lightGray",
                    "lightBlue");
            entry = new DrawableComposition(entry, drawable);

            drawable = drawableFactory.makeFramedRectangle(
                    background.getX() + entryId * 64 + 33,
                    background.getY() + 540 + 31 * i,
                    34,
                    33,
                    2,
                    (i % 2 == 0) ? "gray" : "lightGray",
                    "lightBlue");
            entry = new DrawableComposition(entry, drawable);
        }
    }

    private void initializeText(DrawableFactory drawableFactory, Game game, Drawable background, int entryId) {
        Drawable drawable = drawableFactory.makeText(
                String.valueOf(entryId + 1),
                background.getX() + entryId * 64 + 28,
                background.getY() + 515,
                "HBE24",
                "black");
        entry = new DrawableComposition(entry, drawable);
        for(int i = 0; i < PLAYER_NUMBER; i++) {
            drawable = drawableFactory.makeText(
                    Player.values()[(game.getStartingPlayer().ordinal() + i) % PLAYER_NUMBER].getSymbolString(),
                    background.getX() + entryId * 64 + 11,
                    background.getY() + 540 + 31 * i + 6,
                    "HBE24",
                    "black");
            entry = new DrawableComposition(entry, drawable);

            Card playedCard = game.getPlayedCards().get(i);
            drawable = drawableFactory.makeText(
                    playedCard.getFigure().getSymbolString() + playedCard.getColor().getSymbolString(),
                    background.getX() + entryId * 64 + 36,
                    background.getY() + 540 + 31 * i + 6,
                    "HBE24",
                    (playedCard.getId() < 13 || playedCard.getId() >= 39) ? "black" : "red");
            entry = new DrawableComposition(entry, drawable);
        }
    }
}

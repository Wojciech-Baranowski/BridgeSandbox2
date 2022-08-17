package controllers.gameControllers.historyController;

import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import gameLogic.card.Card;
import gameLogic.game.Game;
import gameLogic.player.Player;
import lombok.Getter;
import solver.ResultRound;

import java.util.List;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class HistoryEntry {

    @Getter
    private Drawable entry;
    @Getter
    private HistoryEntryOverlay overlay;

    public HistoryEntry(DrawableFactory drawableFactory, Drawable background, Game game, int entryId) {
        initializeEntryBackground(drawableFactory, background, game.getWinningPlayer(), entryId);
        initializeText(drawableFactory,
                background,
                game.getPlayedCards(),
                game.getStartingPlayer(),
                entryId,
                false);
        getScene().addObjectHigherThan(entry, background);
        overlay = new HistoryEntryOverlay(drawableFactory, this, game.getWinningPlayer().ordinal());
    }

    public HistoryEntry(DrawableFactory drawableFactory, Drawable background, ResultRound resultRound, int entryId) {
        initializeEntryBackground(
                drawableFactory,
                background,
                resultRound.getWinningPlayer(),
                entryId);

        initializeText(drawableFactory,
                background,
                resultRound.getCards(),
                resultRound.getStartingPlayer(),
                entryId,
                true);
        getScene().addObjectHigherThan(entry, background);
        overlay = new HistoryEntryOverlay(drawableFactory, this, resultRound.getWinningPlayer().ordinal());
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
                    (i % 2 == 0) ? "gray" : "lightGray",
                    "lightBlue");
            entry = new DrawableComposition(entry, drawable);

            drawable = drawableFactory.makeFramedRectangle(
                    background.getX() + entryId * 63 + 35,
                    background.getY() + 539 + 31 * i,
                    34,
                    33,
                    2,
                    (i % 2 == 0) ? "gray" : "lightGray",
                    "lightBlue");
            entry = new DrawableComposition(entry, drawable);
        }
    }

    private void initializeText(DrawableFactory drawableFactory,
                                Drawable background,
                                List<Card> cards,
                                Player startingPlayer,
                                int id,
                                boolean predicted) {
        Drawable drawable = drawableFactory.makeText(
                String.valueOf(id + 1),
                background.getX() + id * 63 + 30,
                background.getY() + 514,
                "HBE24",
                predicted ? "yellow" : "black");
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

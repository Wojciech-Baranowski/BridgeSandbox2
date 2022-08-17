package controllers.gameControllers.historyController;

import engine.display.DisplayBean;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import gameLogic.game.Game;
import solver.ResultRound;

import java.util.LinkedList;
import java.util.List;

import static controllers.gameControllers.backgroundController.GameBackgroundController.getGameBackgroundController;
import static engine.scene.SceneBean.getScene;

public class GameHistoryController {

    private static GameHistoryController gameHistoryController;
    private final List<HistoryEntry> historyEntries;

    private GameHistoryController() {
        historyEntries = new LinkedList<>();
    }

    public static GameHistoryController getGameHistoryController() {
        if (gameHistoryController == null) {
            gameHistoryController = new GameHistoryController();
        }
        return gameHistoryController;
    }

    public void addHistoryEntry(Game game) {
        DrawableFactory drawableFactory = DisplayBean.getDisplay().getDrawableFactory();
        Drawable background = getGameBackgroundController().getBackground().getBackground();

        HistoryEntry historyEntry = new HistoryEntry(drawableFactory, background, game, historyEntries.size());
        historyEntries.add(historyEntry);
    }

    public void addHistoryEntry(ResultRound resultRound) {
        DrawableFactory drawableFactory = DisplayBean.getDisplay().getDrawableFactory();
        Drawable background = getGameBackgroundController().getBackground().getBackground();

        HistoryEntry historyEntry = new HistoryEntry(drawableFactory, background, resultRound, historyEntries.size());
        historyEntries.add(historyEntry);
    }

    public void removeHistoryEntry(int entryId) {
        getScene().removeObject(historyEntries.get(entryId).getEntry());
        getScene().removeObject(historyEntries.get(entryId).getOverlay().getOverlay());
        historyEntries.remove(entryId);
    }

    public void removeAllHistoryEntries() {
        for (HistoryEntry historyEntry : historyEntries) {
            getScene().removeObject(historyEntry.getEntry());
            getScene().removeObject(historyEntry.getOverlay().getOverlay());
        }
        historyEntries.clear();
    }

}

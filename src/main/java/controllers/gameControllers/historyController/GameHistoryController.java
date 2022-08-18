package controllers.gameControllers.historyController;

import engine.display.DisplayBean;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import gameLogic.game.Game;
import solver.result.ResultRound;

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
        removeAllPredictedHistoryEntries();
        HistoryEntry historyEntry = new HistoryEntry(drawableFactory, background, game, historyEntries.size());
        historyEntries.add(historyEntry);
    }

    public void addHistoryEntry(ResultRound resultRound) {
        DrawableFactory drawableFactory = DisplayBean.getDisplay().getDrawableFactory();
        Drawable background = getGameBackgroundController().getBackground().getBackground();

        HistoryEntry historyEntry = new HistoryEntry(drawableFactory, background, resultRound, historyEntries.size());
        historyEntries.add(historyEntry);
    }

    public void removeAllPredictedHistoryEntries() {
        while(!historyEntries.isEmpty() && historyEntries.get(historyEntries.size() - 1).isPredicted()) {
            HistoryEntry historyEntryToRemove = historyEntries.get(historyEntries.size() - 1);
            getScene().removeObject(historyEntryToRemove.getEntry());
            getScene().removeObject(historyEntryToRemove.getOverlay().getOverlay());
            historyEntries.remove(historyEntryToRemove);
        }
    }

    public void removeAllHistoryEntries() {
        for (HistoryEntry historyEntry : historyEntries) {
            getScene().removeObject(historyEntry.getEntry());
            getScene().removeObject(historyEntry.getOverlay().getOverlay());
        }
        historyEntries.clear();
    }

}

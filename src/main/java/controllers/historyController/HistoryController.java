package controllers.historyController;

import engine.display.DisplayBean;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import gameLogic.game.Game;

import java.util.LinkedList;
import java.util.List;

import static controllers.backgroundController.BackgroundController.getBackgroundController;
import static engine.scene.SceneBean.getScene;

public class HistoryController {

    private static HistoryController historyController;
    private final List<HistoryEntry> historyEntries;

    private HistoryController() {
        historyEntries = new LinkedList<>();
    }

    public static HistoryController getHistoryController() {
        if (historyController == null) {
            historyController = new HistoryController();
        }
        return historyController;
    }

    public void addHistoryEntry(Game game) {
        DrawableFactory drawableFactory = DisplayBean.getDisplay().getDrawableFactory();
        Drawable background = getBackgroundController().getBackground().getBackground();

        HistoryEntry historyEntry = new HistoryEntry(drawableFactory, game, background, historyEntries.size());
        historyEntries.add(historyEntry);
    }

    public void removeAllHistoryEntries() {
        for(HistoryEntry historyEntry : historyEntries) {
            getScene().removeObject(historyEntry.getEntry());
            getScene().removeObject(historyEntry.getOverlay().getOverlay());
        }
        historyEntries.clear();
    }

}

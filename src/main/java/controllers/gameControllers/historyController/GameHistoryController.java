package controllers.gameControllers.historyController;

import engine.display.DisplayBean;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import gameLogic.game.Game;

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

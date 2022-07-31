package controllers.historyController;

import engine.display.DisplayBean;
import engine.display.DrawableFactory;
import gameLogic.game.Game;

import java.util.LinkedList;
import java.util.List;

import static controllers.backgroundController.BackgroundController.getBackgroundController;

public class HistoryController {

    private static final DrawableFactory drawableFactory = DisplayBean.getDisplay().getDrawableFactory();
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
        HistoryEntry historyEntry = new HistoryEntry(drawableFactory, game,
                getBackgroundController().getBackground().getBackground(), historyEntries.size());
        historyEntries.add(historyEntry);
    }

}

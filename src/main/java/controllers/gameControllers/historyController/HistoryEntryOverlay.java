package controllers.gameControllers.historyController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;

public class HistoryEntryOverlay {

    @Getter
    private final Drawable overlay;

    HistoryEntryOverlay(DrawableFactory drawableFactory, HistoryEntry historyEntry, int id) {
        overlay = drawableFactory.makeFramedRectangle(
                historyEntry.getEntry().getX(),
                historyEntry.getEntry().getY() + 31 * (id + 1),
                60,
                33,
                2,
                "transparent",
                "yellow");
        getScene().addObjectHigherThan(overlay, historyEntry.getEntry());
    }

}

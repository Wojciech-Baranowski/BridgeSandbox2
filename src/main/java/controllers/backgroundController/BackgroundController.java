package controllers.backgroundController;

import engine.display.DisplayBean;
import engine.display.DrawableFactory;
import gameLogic.player.Player;
import lombok.Getter;

public class BackgroundController {
    private static final DrawableFactory drawableFactory = DisplayBean.getDisplay().getDrawableFactory();

    private static BackgroundController backgroundController;
    private final Background background;
    private final ProgramTitle programTitle;
    private final Table table;
    private final TableCenter tableCenter;
    @Getter
    private final PlayedCardSpace playedCardSpace;
    @Getter
    private HandCardSpace handCardSpace;


    private BackgroundController() {
        background = new Background(drawableFactory);
        programTitle = new ProgramTitle(drawableFactory, background.getBackground());
        table = new Table(drawableFactory, background.getBackground());
        tableCenter = new TableCenter(drawableFactory, table.getTable());
        handCardSpace = new HandCardSpace(drawableFactory, table.getTable());
        playedCardSpace = new PlayedCardSpace(drawableFactory, tableCenter.getTableCenter());
    }

    public static BackgroundController getBackgroundController() {
        if (backgroundController == null) {
            backgroundController = new BackgroundController();
        }
        return backgroundController;
    }

    public void updateOverlays(Player player) {
        handCardSpace.updateOverlay(player);
    }

}

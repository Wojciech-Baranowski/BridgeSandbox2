package controllers.gameControllers.backgroundController;

import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.display.DisplayBean.getDisplay;

public class GameBackgroundController {

    private static GameBackgroundController backgroundController;
    @Getter
    private final Background background;
    private final Table table;
    @Getter
    private final ButtonsSpace buttonsSpace;
    private final TableCenter tableCenter;
    @Getter
    private final PlayedCardSpace playedCardSpace;
    @Getter
    private final PlayerSymbolSpace playerSymbolSpace;
    @Getter
    private HandCardSpace handCardSpace;


    private GameBackgroundController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        background = new Background(drawableFactory);
        table = new Table(drawableFactory, background.getBackground());
        buttonsSpace = new ButtonsSpace(drawableFactory, background.getBackground());
        tableCenter = new TableCenter(drawableFactory, table.getTable());
        handCardSpace = new HandCardSpace(drawableFactory, table.getTable());
        playedCardSpace = new PlayedCardSpace(drawableFactory, tableCenter.getTableCenter());
        playerSymbolSpace = new PlayerSymbolSpace(drawableFactory, handCardSpace.getHandCardSlots());
    }

    public static GameBackgroundController getGameBackgroundController() {
        if (backgroundController == null) {
            backgroundController = new GameBackgroundController();
        }
        return backgroundController;
    }

}

package controllers.backgroundController;

import engine.display.DisplayBean;
import engine.display.DrawableFactory;
import lombok.Getter;

public class BackgroundController {
    private static final DrawableFactory drawableFactory = DisplayBean.getDisplay().getDrawableFactory();
    private final Background background;
    private final Table table;
    private final TableCenter tableCenter;
    @Getter
    private final PlayedCardSpace playedCardSpace;
    @Getter
    private HandCardSpace handCardSpace;


    public BackgroundController() {
        background = new Background(drawableFactory);
        table = new Table(drawableFactory, background.getBackground());
        tableCenter = new TableCenter(drawableFactory, table.getTable());
        handCardSpace = new HandCardSpace(drawableFactory, table.getTable());
        playedCardSpace = new PlayedCardSpace(drawableFactory, tableCenter.getTableCenter());
    }

}

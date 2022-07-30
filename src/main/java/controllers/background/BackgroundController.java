package controllers.background;

import engine.display.DisplayBean;
import engine.display.DrawableFactory;

public class BackgroundController {
    private static final DrawableFactory drawableFactory = DisplayBean.getDisplay().getDrawableFactory();
    private final Background background;
    private final Table table;
    private final TableCenter tableCenter;
    private final PlayedCardSpace playedCardSpace;
    private HandCardSpace handCardSpace;


    public BackgroundController() {
        background = new Background(drawableFactory);
        table = new Table(drawableFactory, background.getBackground());
        tableCenter = new TableCenter(drawableFactory, table.getTable());
        handCardSpace = new HandCardSpace(drawableFactory, table.getTable());
        playedCardSpace = new PlayedCardSpace(drawableFactory, tableCenter.getTableCenter());
    }

}

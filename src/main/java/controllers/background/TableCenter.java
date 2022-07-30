package controllers.background;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;

public class TableCenter {

    @Getter
    private final Drawable tableCenter;

    TableCenter(DrawableFactory drawableFactory, Drawable table) {
        tableCenter = drawableFactory.makeFramedRectangle(
                table.getX() + 286,
                table.getY() + 123,
                263,
                217,
                2,
                "green",
                "lightBlue");
        getScene().addObjectHigherThan(tableCenter, table);
    }

}

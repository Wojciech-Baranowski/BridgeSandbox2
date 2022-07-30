package controllers.backgroundController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;

public class TableCenter {

    @Getter
    private final Drawable tableCenter;

    TableCenter(DrawableFactory drawableFactory, Drawable table) {
        tableCenter = drawableFactory.makeFramedRectangle(
                table.getX() + 291,
                table.getY() + 113,
                253,
                239,
                2,
                "green",
                "lightBlue");
        getScene().addObjectHigherThan(tableCenter, table);
    }

}

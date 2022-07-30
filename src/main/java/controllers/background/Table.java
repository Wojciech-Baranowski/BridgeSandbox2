package controllers.background;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;

public class Table {

    @Getter
    private final Drawable table;

    Table(DrawableFactory drawableFactory, Drawable background) {
        table = drawableFactory.makeFramedRectangle(
                background.getX(),
                background.getY() + 35,
                835,
                465,
                2,
                "green",
                "lightBlue");
        getScene().addObjectHigherThan(table, background.getDrawable());
    }

}

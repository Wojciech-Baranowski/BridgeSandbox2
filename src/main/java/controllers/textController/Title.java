package controllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;

import static engine.scene.SceneBean.getScene;

public class Title {

    private final Drawable title;

    Title(DrawableFactory drawableFactory, Drawable background) {
        title = drawableFactory.makeText(
                "Bridge Sandbox2 v1.16",
                6 + background.getX(),
                4 + background.getY(),
                "HBE32",
                "black");
        getScene().addObjectHigherThan(title, background);
    }

}

package controllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;

public class ProgramTitle {

    @Getter
    private final Text title;

    ProgramTitle(DrawableFactory drawableFactory, Drawable background) {
        title = drawableFactory.makeText(
                "BridgeSandbox2 v1.1",
                background.getX() + 4,
                background.getY() + 4,
                "HBE32",
                "black");
        getScene().addObjectHigherThan(title, background);
    }

}

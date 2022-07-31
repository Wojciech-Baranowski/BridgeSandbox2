package controllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;

import static engine.scene.SceneBean.getScene;

public class Points {

    private final Text points;

    Points(DrawableFactory drawableFactory, Drawable background) {
        points = drawableFactory.makeText(
                "Points: N/S: 0 | E/W: 0",
                background.getX() + 200,
                background.getY() + 4,
                "HBE32",
                "black");
        getScene().addObjectHigherThan(points, background);
    }

    void updatePoints(int[] points) {
        String text =  new StringBuilder()
                .append("Points: N/S: ")
                .append(points[0])
                .append(" | E/W: ")
                .append(points[1])
                .toString();
        this.points.setText(text);
    }

}

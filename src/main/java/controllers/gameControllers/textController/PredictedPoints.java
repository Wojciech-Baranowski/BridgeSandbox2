package controllers.gameControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;

import static engine.scene.SceneBean.getScene;

public class PredictedPoints {

    private final Text pointsNS;
    private final Text pointsEW;

    PredictedPoints(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        pointsNS = drawableFactory.makeText(
                "",
                140 + buttonsSpace.getX(),
                46 + buttonsSpace.getY(),
                "HBE32",
                "black");
        getScene().addObjectHigherThan(pointsNS, buttonsSpace);

        pointsEW = drawableFactory.makeText(
                "",
                140 + buttonsSpace.getX(),
                86 + buttonsSpace.getY(),
                "HBE32",
                "black");
        getScene().addObjectHigherThan(pointsEW, buttonsSpace);
    }

    void updatePoints(int[] points) {
        String text = (points != null) ? new StringBuilder()
                .append("(")
                .append(points[0])
                .append(")")
                .toString()
                : "";
        this.pointsNS.setText(text);

        text = (points != null) ? new StringBuilder()
                .append("(")
                .append(points[1])
                .append(")")
                .toString()
                : "";
        this.pointsEW.setText(text);
    }

}

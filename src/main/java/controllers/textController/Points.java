package controllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;

import static engine.scene.SceneBean.getScene;

public class Points {

    private final Text pointsNS;
    private final Text pointsEW;

    Points(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        pointsNS = drawableFactory.makeText(
                "N/S: 0",
                10 + buttonsSpace.getX(),
                50 + buttonsSpace.getY(),
                "HBE32",
                "black");
        getScene().addObjectHigherThan(pointsNS, buttonsSpace);

        pointsEW = drawableFactory.makeText(
                "E/W: 0",
                10 + buttonsSpace.getX(),
                90 + buttonsSpace.getY(),
                "HBE32",
                "black");
        getScene().addObjectHigherThan(pointsEW, buttonsSpace);
    }

    void updatePoints(int[] points) {
        String text = new StringBuilder()
                .append("N/S: ")
                .append(points[0])
                .toString();
        this.pointsNS.setText(text);

        text = new StringBuilder()
                .append("E/W: ")
                .append(points[1])
                .toString();
        this.pointsEW.setText(text);
    }

}

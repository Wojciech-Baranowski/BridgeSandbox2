package controllers.solverSettingsControllers.algorithmsController;

import engine.button.radioButton.RadioButton;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;
import engine.input.inputCombination.InputCombination;
import lombok.Getter;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class Algorithm {

    @Getter
    private RadioButton radioButton;
    private Text name;
    @Getter
    private int algorithmMethod;

    Algorithm(DrawableFactory drawableFactory, Drawable background, String name, int algorithmMethod, int index) {
        this.algorithmMethod = algorithmMethod;
        Drawable inactiveButtonDrawable = drawableFactory.makeFramedRectangle(
                ((index > 5) ? 620 : 20) + background.getX(),
                ((index > 5) ? 20 : 330) + 50 * (index > 5 ? index - 6 : index) + background.getY(),
                32,
                32,
                2,
                "gray",
                "lightBlue");

        Drawable activeButtonDrawable = drawableFactory.makeFramedRectangle(
                inactiveButtonDrawable.getX(),
                inactiveButtonDrawable.getY(),
                inactiveButtonDrawable.getW(),
                inactiveButtonDrawable.getH(),
                2,
                "lightBlue",
                "yellow");

        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        radioButton = new RadioButton(inactiveButtonDrawable, activeButtonDrawable, activationCombination);

        this.name = drawableFactory.makeText(
                name,
                42 + inactiveButtonDrawable.getX(),
                2 + inactiveButtonDrawable.getY(),
                "HBE32",
                "black");

        getScene().addObjectHigherThan(radioButton, background);
        getScene().addObjectHigherThan(this.name, background);
    }

}
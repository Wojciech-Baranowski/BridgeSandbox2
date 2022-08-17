package controllers.solverSettingsControllers.algorithmsController;

import engine.button.radioButton.RadioButton;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;
import engine.input.inputCombination.InputCombination;
import lombok.Getter;
import solver.Algorithm;
import solver.Solver;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class AlgorithmChangerOption {

    @Getter
    private RadioButton radioButton;
    private Text name;
    @Getter
    private Algorithm algorithm;

    AlgorithmChangerOption(
            DrawableFactory drawableFactory, Drawable background, Solver solver, int index) {
        this.algorithm = solver.getAlgorithm();
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
                solver.getName(),
                42 + inactiveButtonDrawable.getX(),
                2 + inactiveButtonDrawable.getY(),
                "HBE32",
                "black");

        getScene().addObjectHigherThan(radioButton, background);
        getScene().addObjectHigherThan(this.name, background);
    }

}

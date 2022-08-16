package controllers.solverSettingsControllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import lombok.Getter;

import static controllers.solverSettingsControllers.textController.SolverSettingsTextController.getSolverSettingsTextController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class GamesNumberChanger {

    private class DecrementGamesNumberCommand implements Command {

        @Override
        public void execute() {
            updateGamesNumber(((gamesNumber - 2 + 9999999) % 9999999) / 10 + 1);
        }
    }

    private class IncrementGamesNumberCommand implements Command {

        @Override
        public void execute() {
            updateGamesNumber((gamesNumber * 10) % 9999999);
        }

    }

    private final SimpleButton incGamesButton;
    private final SimpleButton decGamesButton;
    @Getter
    private int gamesNumber;

    GamesNumberChanger(DrawableFactory drawableFactory, Drawable background) {
        gamesNumber = 1000000;
        Drawable buttonBackground = drawableFactory.makeFramedRectangle(
                484 + background.getX(),
                83 + background.getY(),
                32,
                32,
                2,
                "gray",
                "lightBlue");

        Drawable buttonSymbol = drawableFactory.makeText(
                "-",
                10 + buttonBackground.getX(),
                buttonBackground.getY(),
                "HBE32",
                "black");

        Drawable decDrawable = new DrawableComposition(buttonBackground, buttonSymbol);

        buttonBackground = drawableFactory.makeFramedRectangle(
                50 + decDrawable.getX(),
                decDrawable.getY(),
                32,
                32,
                2,
                "gray",
                "lightBlue");

        buttonSymbol = drawableFactory.makeText(
                "+",
                7 + buttonBackground.getX(),
                buttonBackground.getY(),
                "HBE32",
                "black");

        Drawable incDrawable = new DrawableComposition(buttonBackground, buttonSymbol);

        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        incGamesButton = new SimpleButton(incDrawable, activationCombination, new IncrementGamesNumberCommand());
        decGamesButton = new SimpleButton(decDrawable, activationCombination, new DecrementGamesNumberCommand());
        getScene().addObjectHigherThan(incGamesButton, background);
        getScene().addObjectHigherThan(decGamesButton, background);
    }

    public void updateGamesNumber(int gamesNumber) {
        this.gamesNumber = gamesNumber;
        getSolverSettingsTextController().getGamesNumber().updateGamesNumber(gamesNumber);
    }

}

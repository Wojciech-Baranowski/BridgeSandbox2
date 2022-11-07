package controllers.probabilityModeControllers.buttonController;

import controllers.probabilityModeControllers.cardController.ProbabilityModeCardController;
import engine.button.SimpleButton;
import engine.button.radioButton.RadioButtonBundle;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import static controllers.probabilityModeControllers.buttonController.ProbabilityModeButtonController.getProbabilityModeButtonController;
import static controllers.probabilityModeControllers.cardController.ProbabilityModeCardController.getProbabilityModeCardController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class ClearChanges {

    private static class ClearCommand implements Command {

        @Override
        public void execute() {
            ProbabilityModeButtonController buttonController = getProbabilityModeButtonController();
            ProbabilityModeCardController cardController = getProbabilityModeCardController();

            for(RadioButtonBundle bundle : cardController.getChooseCards().getChooseCardsBundles()) {
                bundle.unset();
            }
            for(RadioButtonBundle bundle : buttonController.getPlayedCardsChanger()
                    .getChoosePlayedCardsButtonsBundles()) {
                bundle.unset();
            }
            buttonController.getStartingPlayerChanger().getChooseStartingPlayerButtonsBundle().unset();
        }

    }

    private final SimpleButton clearChanges;

    ClearChanges(DrawableFactory drawableFactory, Drawable background) {
        Drawable buttonBackground = drawableFactory.makeFramedRectangle(
                560 + background.getX(),
                517 + background.getY(),
                254,
                64,
                2,
                "gray",
                "lightBlue");

        Drawable buttonText = drawableFactory.makeText(
                "CLEAR",
                48 + buttonBackground.getX(),
                10 + buttonBackground.getY(),
                "HBE48",
                "black");

        Drawable buttonDrawable = new DrawableComposition(buttonBackground, buttonText);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        clearChanges = new SimpleButton(buttonDrawable, activationCombination, new ClearCommand());
        getScene().addObjectHigherThan(clearChanges, background);
    }
}

package controllers.gameControllers.buttonController;

import controllers.main.assets.CardComparator;
import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import static controllers.gameControllers.cardController.GameCardController.getGameCardController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class CardsOrderChanger {

    private static class CardOrderChangeCommand implements Command {

        @Override
        public void execute() {
            CardComparator currentComparator = getGameCardController().getCardOrderComparator();
            CardComparator newComparator = currentComparator.getNextComparator();
            getGameCardController().setCardOrder(newComparator);
            getGameCardController().reinitialize();
        }
    }

    private final SimpleButton cardOrderChanger;

    CardsOrderChanger(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                10 + buttonsSpace.getX(),
                270 + buttonsSpace.getY(),
                318,
                40,
                2,
                "gray",
                "lightBlue");

        Drawable text = drawableFactory.makeText(
                "Change cards order",
                8 + background.getX(),
                6 + background.getY(),
                "HBE32",
                "black");

        Drawable drawable = new DrawableComposition(background, text);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        cardOrderChanger = new SimpleButton(drawable, activationCombination, new CardOrderChangeCommand());
        getScene().addObjectHigherThan(cardOrderChanger, buttonsSpace);
    }

}

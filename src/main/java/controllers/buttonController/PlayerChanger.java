package controllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import gameLogic.game.Game;

import static controllers.cardController.CardController.getCardController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.Game.getGame;

public class PlayerChanger {

    private static class SwitchToNextPlayerCommand implements Command {

        @Override
        public void execute() {
            Game game = getGame();
            if(!game.isGameOngoing()) {
                game.switchToNextPlayer();
                getCardController().updateOverlays(game.getCurrentPlayer());
            }
        }
    }

    private final SimpleButton nextPlayer;

    PlayerChanger(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                10 + buttonsSpace.getX(),
                170 + buttonsSpace.getY(),
                190,
                40,
                2,
                "gray",
                "lightBlue");

        Drawable text = drawableFactory.makeText(
                "Next player",
                8 + background.getX(),
                6 + background.getY(),
                "HBE32",
                "black");

        Drawable drawable = new DrawableComposition(background, text);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        nextPlayer = new SimpleButton(drawable, activationCombination, new SwitchToNextPlayerCommand());
        getScene().addObjectHigherThan(nextPlayer, buttonsSpace);
    }

}

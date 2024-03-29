package controllers.solverSettingsControllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import gameLogic.game.Game;

import static controllers.gameControllers.buttonController.GameButtonController.getGameButtonController;
import static controllers.gameControllers.cardController.GameCardController.getGameCardController;
import static controllers.gameControllers.historyController.GameHistoryController.getGameHistoryController;
import static controllers.gameControllers.textController.GameTextController.getGameTextController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.Game.getGame;

public class GameSwitch {

    private static class SwitchToGameCommand implements Command {

        @Override
        public void execute() {
            getScene().switchCollection("game");
        }

    }

    private final SimpleButton gameSwitch;

    GameSwitch(DrawableFactory drawableFactory, Drawable background) {
        Drawable buttonBackground = drawableFactory.makeFramedRectangle(
                828 + background.getX(),
                594 + background.getY(),
                354,
                64,
                2,
                "gray",
                "lightBlue");

        Drawable buttonText = drawableFactory.makeText(
                "Back to game",
                18 + buttonBackground.getX(),
                10 + buttonBackground.getY(),
                "HBE48",
                "black");

        DrawableComposition drawable = new DrawableComposition(buttonBackground, buttonText);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        gameSwitch = new SimpleButton(drawable, activationCombination, new SwitchToGameCommand());
        getScene().addObjectHigherThan(gameSwitch, background);
    }

}

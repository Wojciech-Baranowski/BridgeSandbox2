package controllers.gameControllers.buttonController;

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

public class GameCreator {

    private static class CreateGameCommand implements Command {

        @Override
        public void execute() {
            Game game = getGame();
            game.initializeGame(game.getAtu(), game.getStartingNumberOfCardsPerPlayer());
            getGameHistoryController().removeAllHistoryEntries();
            getGameCardController().reinitialize();
            getGameTextController().updatePoints();
            getGameButtonController().getSolverTrigger().getSolverTrigger().setSelected(false);
        }

    }

    private final SimpleButton gameCreator;

    GameCreator(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                10 + buttonsSpace.getX(),
                220 + buttonsSpace.getY(),
                178,
                40,
                2,
                "gray",
                "lightBlue");

        Drawable text = drawableFactory.makeText(
                "New game",
                8 + background.getX(),
                6 + background.getY(),
                "HBE32",
                "black");

        DrawableComposition drawable = new DrawableComposition(background, text);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        gameCreator = new SimpleButton(drawable, activationCombination, new CreateGameCommand());
        getScene().addObjectHigherThan(gameCreator, buttonsSpace);
    }

}

package controllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import gameLogic.game.Game;

import static controllers.cardController.CardController.getCardController;
import static controllers.historyController.HistoryController.getHistoryController;
import static controllers.textController.TextController.getTextController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.Game.getGame;

public class GameRestarter {

    private static class RestartGameCommand implements Command {

        @Override
        public void execute() {
            Game game = getGame();
            game.initializeGame(game.getAtu(), game.getStartingNumberOfCardsPerPlayer());
            getHistoryController().removeAllHistoryEntries();
            getCardController().reinitialize();
            getTextController().updatePoints();
        }

    }

    private final SimpleButton gameRestarter;

    public GameRestarter(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                10 + buttonsSpace.getX(),
                220 + buttonsSpace.getY(),
                220,
                40,
                2,
                "gray",
                "lightBlue");

        Drawable text = drawableFactory.makeText(
                "Restart game",
                8 + background.getX(),
                6 + background.getY(),
                "HBE32",
                "black");

        DrawableComposition drawable = new DrawableComposition(background, text);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        gameRestarter = new SimpleButton(drawable, activationCombination, new RestartGameCommand());
        getScene().addObjectHigherThan(gameRestarter, buttonsSpace);
    }

}

package controllers.gameControllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import gameLogic.game.Game;

import static controllers.gameControllers.cardController.GameCardController.getCardController;
import static controllers.gameControllers.historyController.GameHistoryController.getHistoryController;
import static controllers.gameControllers.textController.GameTextController.getTextController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.Game.getGame;
import static gameLogic.game.GameConstants.MAX_CARDS_PER_PLAYER;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class CardsNumberChanger {

    private static class DecrementCardNumberCommand implements Command {

        @Override
        public void execute() {
            Game game = getGame();
            if(game.getStartingNumberOfCardsPerPlayer() > 1 && !game.isGameOngoing()) {
                game.initializeGame(game.getAtu(), max(1, game.getStartingNumberOfCardsPerPlayer() - 1));
                getHistoryController().removeAllHistoryEntries();
                getCardController().reinitialize();
                getTextController().updatePoints();
            }
        }
    }

    private static class IncrementCardNumberCommand implements Command {

        @Override
        public void execute() {
            Game game = getGame();
            if(game.getStartingNumberOfCardsPerPlayer() < MAX_CARDS_PER_PLAYER && !game.isGameOngoing()) {
                game.initializeGame(game.getAtu(),
                        min(MAX_CARDS_PER_PLAYER, game.getStartingNumberOfCardsPerPlayer() + 1));
                getHistoryController().removeAllHistoryEntries();
                getCardController().reinitialize();
                getTextController().updatePoints();
            }
        }

    }

    private SimpleButton incCardsButton;
    private SimpleButton decCardsButton;

    CardsNumberChanger(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                110 + buttonsSpace.getX(),
                124 + buttonsSpace.getY(),
                32,
                32,
                2,
                "gray",
                "lightBlue");

        Drawable symbol = drawableFactory.makeText(
                "-",
                10 + background.getX(),
                background.getY(),
                "HBE32",
                "black");

        Drawable decDrawable = new DrawableComposition(background, symbol);

        background = drawableFactory.makeFramedRectangle(
                150 + buttonsSpace.getX(),
                124 + buttonsSpace.getY(),
                32,
                32,
                2,
                "gray",
                "lightBlue");

        symbol = drawableFactory.makeText(
                "+",
                7 + background.getX(),
                background.getY(),
                "HBE32",
                "black");

        Drawable incDrawable = new DrawableComposition(background, symbol);

        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        incCardsButton = new SimpleButton(incDrawable, activationCombination, new IncrementCardNumberCommand());
        decCardsButton = new SimpleButton(decDrawable, activationCombination, new DecrementCardNumberCommand());
        getScene().addObjectHigherThan(incCardsButton, buttonsSpace);
        getScene().addObjectHigherThan(decCardsButton, buttonsSpace);
    }

}

package controllers.gameControllers.buttonController;

import controllers.editGameControllers.buttonController.EditGameButtonController;
import controllers.editGameControllers.cardController.EditGameCardController;
import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import gameLogic.game.Game;

import static controllers.editGameControllers.buttonController.EditGameButtonController.getEditGameButtonController;
import static controllers.editGameControllers.cardController.EditGameCardController.getEditGameCardController;
import static controllers.gameControllers.buttonController.GameButtonController.getGameButtonController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.Game.getGame;

public class GameEditSwitch {

    private static class SwitchToEditGameCommand implements Command {

        @Override
        public void execute() {
            Game game = getGame();
            if (game.getStartingPlayer() == game.getCurrentPlayer()) {
                EditGameButtonController buttonController = getEditGameButtonController();
                EditGameCardController cardController = getEditGameCardController();

                buttonController.getAtuChanger().updateChooseAtuButtonsBundle(game.getAtu());
                buttonController.getStartingPlayerChanger().updateChooseStartingPlayerButtons(game.getCurrentPlayer());
                buttonController.getCardsNumberChanger().updateCardNumber(game.getStartingNumberOfCardsPerPlayer());
                cardController.getChooseCards().updateChosenCards(game.getCards());

                getScene().switchCollection("editGame");
                getGameButtonController().getSolverTrigger().getSolverTrigger().setSelected(false);
            }
        }

    }

    private final SimpleButton gameEditSwitch;

    GameEditSwitch(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                10 + buttonsSpace.getX(),
                420 + buttonsSpace.getY(),
                166,
                40,
                2,
                "gray",
                "lightBlue");

        Drawable text = drawableFactory.makeText(
                "Edit game",
                8 + background.getX(),
                6 + background.getY(),
                "HBE32",
                "black");

        DrawableComposition drawable = new DrawableComposition(background, text);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        gameEditSwitch = new SimpleButton(drawable, activationCombination, new SwitchToEditGameCommand());
        getScene().addObjectHigherThan(gameEditSwitch, buttonsSpace);
    }

}

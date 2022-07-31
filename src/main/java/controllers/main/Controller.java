package controllers.main;

import controllers.backgroundController.BackgroundController;
import controllers.cardController.CardController;
import controllers.historyController.HistoryController;
import controllers.main.assets.CardDrawables;
import controllers.textController.TextController;
import engine.assets.Assets;
import engine.assets.font.Font;
import engine.display.Display;
import engine.scene.Scene;
import gameLogic.card.Color;
import gameLogic.game.Game;

import java.util.Random;

import static controllers.backgroundController.BackgroundController.getBackgroundController;
import static controllers.cardController.CardController.getCardController;
import static controllers.historyController.HistoryController.getHistoryController;
import static controllers.textController.TextController.getTextController;
import static engine.assets.AssetsBean.getAssets;
import static engine.display.DisplayBean.getDisplay;
import static engine.scene.SceneBean.getScene;
import static gameLogic.card.Color.CLUB;
import static gameLogic.game.Game.getGame;
import static gameLogic.game.GameConstants.MAX_CARDS_PER_PLAYER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class Controller {

    private static Controller controller;
    private final Assets assets;
    private final Display display;
    private final Scene scene;
    private CardDrawables cardDrawables;
    private BackgroundController backgroundController;
    private TextController textController;
    private CardController cardController;
    private HistoryController historyController;
    private Game game;

    private Controller() {
        assets = getAssets();
        display = getDisplay();
        scene = getScene();
        game = getGame();
    }

    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public void initializeController() {
        initializeColors();
        initializeFonts();
        initializeScenes();
        initializeAssets();
        initializeGame();
        initializeControllers();
        scene.update();
    }

    private void initializeColors() {
        assets.addColor("black", 0xFF000000);
        assets.addColor("white", 0xFFFFFFFF);
        assets.addColor("green", 0xFF007D00);
        assets.addColor("darkGreen", 0xFF0E4C05);
        assets.addColor("red", 0xFFAA0707);
        assets.addColor("lightBlue", 0xFF99CCCC);
        assets.addColor("yellow", 0xFFFFCE00);
        assets.addColor("lightGray", 0xFFA8A8A8);
        assets.addColor("gray", 0xFF888888);
        assets.addColor("darkGray", 0xFF555555);
        assets.addColor("transparentGray", 0x44444444);
        assets.addColor("transparent", 0xFFFF00FF);
        assets.addColor("blue", 0xFF3E79FF);
        assets.addColor("violet", 0xFF8042FF);
    }

    private void initializeFonts() {
        assets.addFont("HBE24", "/HelveticaBoldExtended24.png", Font.getExtendedAlphabet());
        assets.addFont("HBE32", "/HelveticaBoldExtended32.png", Font.getExtendedAlphabet());
        assets.addFont("HBE48", "/HelveticaBoldExtended48.png", Font.getExtendedAlphabet());
    }

    private void initializeScenes() {
        scene.addCollection("game");
        scene.switchCollection("game");
    }

    private void initializeAssets() {
        cardDrawables = CardDrawables.getCardDrawables();
    }

    private void initializeGame() {
        game.initializeGame(CLUB, MAX_CARDS_PER_PLAYER);
    }

    private void initializeControllers() {
        backgroundController = getBackgroundController();
        backgroundController.updateOverlays(game.getCurrentPlayer());
        textController = getTextController();
        cardController = getCardController();
        cardController.initializeHandCards();
        cardController.updateOverlays();
        historyController = getHistoryController();
    }

    public static void main(String[] args) {
        new Controller().initializeController();
    }

}

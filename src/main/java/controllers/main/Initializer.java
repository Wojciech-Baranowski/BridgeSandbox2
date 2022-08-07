package controllers.main;

import controllers.gameControllers.backgroundController.GameBackgroundController;
import controllers.gameControllers.buttonController.GameButtonController;
import controllers.gameControllers.cardController.GameCardController;
import controllers.gameControllers.historyController.GameHistoryController;
import controllers.gameControllers.textController.GameTextController;
import engine.assets.Assets;
import engine.assets.font.Font;
import engine.display.Display;
import engine.scene.Scene;
import gameLogic.game.Game;

import static controllers.gameControllers.backgroundController.GameBackgroundController.getGameBackgroundController;
import static controllers.gameControllers.buttonController.GameButtonController.getButtonController;
import static controllers.gameControllers.cardController.GameCardController.getCardController;
import static controllers.gameControllers.historyController.GameHistoryController.getHistoryController;
import static controllers.gameControllers.textController.GameTextController.getTextController;
import static engine.assets.AssetsBean.getAssets;
import static engine.display.DisplayBean.getDisplay;
import static engine.scene.SceneBean.getScene;
import static gameLogic.card.Color.CLUB;
import static gameLogic.game.Game.getGame;
import static gameLogic.game.GameConstants.MAX_CARDS_PER_PLAYER;

public class Initializer {

    private static Initializer initializer;
    private final Assets assets;
    private final Display display;
    private final Scene scene;
    private GameBackgroundController gameBackgroundController;
    private GameTextController gameTextController;
    private GameCardController gameCardController;
    private GameHistoryController gameHistoryController;
    private GameButtonController gameButtonController;
    private Game game;

    private Initializer() {
        assets = getAssets();
        display = getDisplay();
        scene = getScene();
        game = getGame();
    }

    public static Initializer getInitializer() {
        if (initializer == null) {
            initializer = new Initializer();
        }
        return initializer;
    }

    public void initialize() {
        initializeColors();
        initializeFonts();
        initializeScenes();
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

    private void initializeGame() {
        game.initializeGame(CLUB, MAX_CARDS_PER_PLAYER);
    }

    private void initializeControllers() {
        initializeGameControllers();
    }

    private void initializeGameControllers() {
        gameBackgroundController = getGameBackgroundController();
        gameTextController = getTextController();
        gameCardController = getCardController();
        gameHistoryController = getHistoryController();
        gameButtonController = getButtonController();
    }

    public static void main(String[] args) {
        new Initializer().initialize();
    }

}

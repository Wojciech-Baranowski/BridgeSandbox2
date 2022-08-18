package controllers.main;

import engine.assets.Assets;
import engine.assets.font.Font;
import engine.display.Display;
import engine.scene.Scene;
import gameLogic.game.Game;

import static controllers.editGameControllers.backgroundController.EditGameBackgroundController.getEditGameBackgroundController;
import static controllers.editGameControllers.buttonController.EditGameButtonController.getEditGameButtonController;
import static controllers.editGameControllers.cardController.EditGameCardController.getEditGameCardController;
import static controllers.editGameControllers.textController.EditGameTextController.getEditGameTextController;
import static controllers.gameControllers.backgroundController.GameBackgroundController.getGameBackgroundController;
import static controllers.gameControllers.buttonController.GameButtonController.getGameButtonController;
import static controllers.gameControllers.cardController.GameCardController.getGameCardController;
import static controllers.gameControllers.historyController.GameHistoryController.getGameHistoryController;
import static controllers.gameControllers.textController.GameTextController.getGameTextController;
import static controllers.solverSettingsControllers.algorithmsController.SolverSettingsAlgorithmsController.getSolverSettingsAlgorithmsController;
import static controllers.solverSettingsControllers.backgroundController.SolverSettingsBackgroundController.getSolverSettingsBackgroundController;
import static controllers.solverSettingsControllers.buttonController.SolverSettingsButtonController.getSolverSettingsButtonController;
import static controllers.solverSettingsControllers.textController.SolverSettingsTextController.getSolverSettingsTextController;
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
    private final Game game;

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
        scene.switchCollection("game");
        scene.update();
    }

    private void initializeColors() {
        assets.addColor("black", 0xFF000000);
        assets.addColor("white", 0xFFFFFFFF);
        assets.addColor("green", 0xFF007D00);
        assets.addColor("darkGreen", 0xFF0E4C05);
        assets.addColor("red", 0xFFAA0707);
        assets.addColor("lightBlue", 0xFF01BAEF);
        assets.addColor("yellow", 0xFFFFFF00);
        assets.addColor("lightGray", 0xFFA8A8A8);
        assets.addColor("gray", 0xFF4378FF);
        assets.addColor("darkGray", 0xFF898989);
        assets.addColor("transparentGray", 0x44444444);
        assets.addColor("transparentBlue", 0x554378FF);
        assets.addColor("transparent", 0xFFFF00FF);
        assets.addColor("blue", 0xFF1F57FF);
        assets.addColor("violet", 0xFF8042FF);
        assets.addColor("pink", 0xFFB477FF);
        assets.addColor("orange", 0xFFFFA82C);
    }

    private void initializeFonts() {
        assets.addFont("HBE24", "/HelveticaBoldExtended24.png", Font.getExtendedAlphabet());
        assets.addFont("HBE32", "/HelveticaBoldExtended32.png", Font.getExtendedAlphabet());
        assets.addFont("HBE48", "/HelveticaBoldExtended48.png", Font.getExtendedAlphabet());
    }

    private void initializeScenes() {
        scene.addCollection("game");
        scene.addCollection("editGame");
        scene.addCollection("solverSettings");
    }

    private void initializeGame() {
        game.initializeGame(CLUB, MAX_CARDS_PER_PLAYER);
    }

    private void initializeControllers() {
        initializeGameControllers();
        initializeEditGameControllers();
        initializeSolverSettingsControllers();
    }

    private void initializeGameControllers() {
        scene.switchCollection("game");
        getGameBackgroundController();
        getGameTextController();
        getGameCardController();
        getGameHistoryController();
        getGameButtonController();
    }

    private void initializeEditGameControllers() {
        scene.switchCollection("editGame");
        getEditGameBackgroundController();
        getEditGameTextController();
        getEditGameCardController();
        getEditGameButtonController();
    }

    private void initializeSolverSettingsControllers() {
        scene.switchCollection("solverSettings");
        getSolverSettingsBackgroundController();
        getSolverSettingsTextController();
        getSolverSettingsButtonController();
        getSolverSettingsAlgorithmsController();
    }

    public static void main(String[] args) {
        new Initializer().initialize();
    }

}

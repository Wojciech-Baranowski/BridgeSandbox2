package controllers.main;

import controllers.backgroundController.BackgroundController;
import controllers.cardController.CardController;
import controllers.main.assets.CardDrawables;
import engine.assets.Assets;
import engine.assets.AssetsBean;
import engine.assets.font.Font;
import engine.display.Display;
import engine.display.DisplayBean;
import engine.scene.Scene;
import engine.scene.SceneBean;
import gameLogic.card.Color;
import gameLogic.game.Game;

import java.util.Random;

import static gameLogic.game.GameConstants.MAX_CARDS_PER_PLAYER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class Controller {

    private static Controller controller;
    private final Assets assets;
    private final Display display;
    private final Scene scene;
    private CardDrawables cardDrawables;
    private BackgroundController backgroundController;
    private CardController cardController;

    private Game game;

    private Controller() {
        assets = AssetsBean.getAssets();
        display = DisplayBean.getDisplay();
        scene = SceneBean.getScene();
        game = new Game();
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
        assets.addColor("gray", 0xFFCBCBCB);
        assets.addColor("darkGray", 0xFF989898);
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
        Color atu = Color.values()[new Random().nextInt(PLAYER_NUMBER)];
        game.initializeGame(atu, MAX_CARDS_PER_PLAYER);
    }

    private void initializeControllers() {
        backgroundController = new BackgroundController();
        cardController = new CardController(game, backgroundController.getHandCardSpace());
    }

    public static void main(String[] args) {
        new Controller().initializeController();
    }

}

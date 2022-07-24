package view.main;

import engine.assets.Assets;
import engine.assets.AssetsBean;
import engine.assets.font.Font;
import engine.display.Display;
import engine.display.DisplayBean;
import engine.scene.Scene;
import engine.scene.SceneBean;
import view.background.Background;

public class View {

    private static View view;
    private final Assets assets;
    private final Display display;
    private final Scene scene;

    private View() {
        assets = AssetsBean.getAssets();
        display = DisplayBean.getDisplay();
        scene = SceneBean.getScene();
    }

    public static View getView() {
        if (view == null) {
            view = new View();
        }
        return view;
    }

    public void initializeView() {
        initializeColors();
        initializeFonts();
        initializeScenes();
        initializeGameObjects();
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
    }

    private void initializeGameObjects() {
        scene.switchCollection("game");
        new Background();
    }

    public static void main(String[] args) {
        new View().initializeView();
    }

}

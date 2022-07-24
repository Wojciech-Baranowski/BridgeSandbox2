package view.main;

import engine.assets.Assets;
import engine.assets.AssetsBean;
import engine.assets.font.Font;
import engine.scene.Scene;
import engine.scene.SceneBean;

public class View {

    private static View view;

    public static View getView() {
        if (view == null) {
            view = new View();
        }
        return view;
    }

    public void initializeView() {
        Assets assets = AssetsBean.getAssets();
        Scene scene = SceneBean.getScene();
        initializeColors(assets);
        initializeFonts(assets);
        initializeScenes(scene);
    }

    private View() {
    }

    private void initializeColors(Assets assets) {
        assets.addColor("black", 0xFF000000);
        assets.addColor("white", 0xFFFFFFFF);
        assets.addColor("green", 0xFF007D00);
        assets.addColor("red", 0xFFAA0707);
        assets.addColor("lightBlue", 0xFF99CCCC);
        assets.addColor("yellow", 0xFFFFCE00);
        assets.addColor("gray", 0xFFCBCBCB);
    }

    private void initializeFonts(Assets assets) {
        assets.addFont("HBE24", "/HelveticaBoldExtended24.png", Font.getExtendedAlphabet());
        assets.addFont("HBE32", "/HelveticaBoldExtended32.png", Font.getExtendedAlphabet());
        assets.addFont("HBE48", "/HelveticaBoldExtended48.png", Font.getExtendedAlphabet());
    }

    private void initializeScenes(Scene scene) {
        scene.addCollection("game");
    }

    public static void main(String[] args) {
        new View().initializeView();
    }

}

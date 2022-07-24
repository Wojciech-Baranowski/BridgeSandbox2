package view.main;

import engine.assets.Assets;
import engine.assets.AssetsBean;
import engine.assets.font.Font;

public class View {

    public static void initializeView() {
        Assets assets = AssetsBean.getAssets();
        initializeColors(assets);
        initializeFonts(assets);
    }

    private static void initializeColors(Assets assets) {
        assets.addColor("black", 0xFF000000);
        assets.addColor("white", 0xFFFFFFFF);
        assets.addColor("green", 0xFF007D00);
        assets.addColor("red", 0xFFAA0707);
        assets.addColor("lightBlue", 0xFF99CCCC);
        assets.addColor("yellow", 0xFFFFCE00);
        assets.addColor("gray", 0xFFCBCBCB);

    }

    private static void initializeFonts(Assets assets) {
        assets.addFont("HBE24", "/HelveticaBoldExtended24.png", Font.getExtendedAlphabet());
        assets.addFont("HBE32", "/HelveticaBoldExtended32.png", Font.getExtendedAlphabet());
        assets.addFont("HBE48", "/HelveticaBoldExtended48.png", Font.getExtendedAlphabet());
    }

    private View() {
    }

    public static void main(String[] args) {
        initializeView();
    }

}

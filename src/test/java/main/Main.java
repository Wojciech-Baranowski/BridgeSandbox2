package main;

import assets.AssetsBean;
import assets.font.Font;
import display.DisplayBean;
import display.rectangle.Rectangle;
import display.text.Text;
import scene.SceneBean;

public class Main {

    public static void main(String[] args) {
        new BeanConfig().buildBeans();

        //test

        AssetsBean.getAssets().addColor("red", 0xBBFF0000);
        AssetsBean.getAssets().addColor("blue", 0xFF0000FF);
        AssetsBean.getAssets().addColor("magenta", 0xFFFF00FE);
        AssetsBean.getAssets().addFont("HBE48", "/HelveticaBoldExtended48.png", Font.getExtendedAlphabet());
        Text text = DisplayBean.getDisplay().getDrawableFactory().makeText("Kocham Palinke \u2665", 100, 100, "HBE48", "red");
        Rectangle rectangle = DisplayBean.getDisplay().getDrawableFactory().makeFramedRectangle(50, 50, 700, 200, 5, "blue", "magenta");
        SceneBean.getScene().addCollection("1");
        SceneBean.getScene().switchCollection("1");
        SceneBean.getScene().addOnHighest(text);
        SceneBean.getScene().addOnLowest(rectangle);
        DisplayBean.getDisplay().setObjectsToDraw(SceneBean.getScene().getCurrentObjectCollection());
        DisplayBean.getDisplay().draw();
    }

}

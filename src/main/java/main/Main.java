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
        AssetsBean.getAssets().addColor("red", 0x88FF0000);
        AssetsBean.getAssets().addColor("blue", 0xFF0000FF);
        AssetsBean.getAssets().addColor("cyan", 0xFF00FFFF);
        AssetsBean.getAssets().addColor("yellow", 0xA0FFFF00);
        AssetsBean.getAssets().addFont("HBE48", "/HelveticaBoldExtended48.png", Font.getExtendedAlphabet());
        Rectangle rectangle1 = DisplayBean.getDisplay().getDrawableFactory().makeRectangle(10, 10, 100, 100, "red");
        Rectangle rectangle2 = DisplayBean.getDisplay().getDrawableFactory().makeFramedRectangle(50, 50, 100, 100, 5, "blue", "cyan");
        Text text = DisplayBean.getDisplay().getDrawableFactory().makeText("Zest is best\n\u2665",30, 30, "HBE48", "yellow");
        SceneBean.getScene().addCollection("c1");
        SceneBean.getScene().switchCollection("c1");
        SceneBean.getScene().addOnHighest(rectangle2);
        SceneBean.getScene().addObjectHigherThan(rectangle1, rectangle2);
        SceneBean.getScene().addOnHighest(text);
        SceneBean.getScene().update();
    }

}

package button;

import assets.Assets;
import assets.AssetsBean;
import display.Display;
import display.DisplayBean;
import display.Drawable;
import display.DrawableFactory;
import display.rectangle.Rectangle;
import input.Input;
import input.InputBean;
import input.inputCombination.InputCombinationFactory;
import org.junit.Test;
import scene.Scene;
import scene.SceneBean;

import static org.junit.Assert.assertSame;

public class SimpleButtonTest {

    @Test
    public void get_drawable_test() {
        //given
        Assets assets = AssetsBean.getAssets();
        assets.addColor("n1", 0xFFFFFFFF);
        Display display = DisplayBean.getDisplay();
        DrawableFactory drawableFactory = display.getDrawableFactory();
        Rectangle inputRectangle = drawableFactory.makeRectangle(10, 10, 10, 10, "n1");
        SimpleButton simpleButton = new SimpleButton(inputRectangle, null, null);
        Drawable output;
        //when
        output = simpleButton.getDrawable();
        //then
        assertSame(inputRectangle, output);
    }

    public static void main(String[] args) {
        new SimpleButtonTest().update_manual_test();
    }

    public void update_manual_test() {
        //given
        Assets assets = AssetsBean.getAssets();
        assets.addColor("n1", 0xFFFF0000);
        Display display = DisplayBean.getDisplay();
        Input input = InputBean.getInput();
        InputCombinationFactory inputCombinationFactory = input.getInputCombinationFactory();
        DrawableFactory drawableFactory = display.getDrawableFactory();
        Rectangle inputRectangle = drawableFactory.makeRectangle(10, 10, 100, 100, "n1");
        SimpleButton simpleButton = new SimpleButton(inputRectangle, inputCombinationFactory.makeLmbCombination(), () -> System.out.println("Hellothere!"));
        Scene scene = SceneBean.getScene();
        scene.addCollection("c1");
        scene.switchCollection("c1");
        scene.addOnHighest(simpleButton);
        //when
        scene.update();
        //then
        //"Hellothere!" should appear in console logs
    }

}

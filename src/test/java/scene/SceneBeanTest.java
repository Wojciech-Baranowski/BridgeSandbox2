package scene;

import assets.Assets;
import assets.AssetsBean;
import display.Display;
import display.DisplayBean;
import display.Drawable;
import display.DrawableFactory;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class SceneBeanTest {

    @Test
    public void add_collection_test() {
        //given
        String[] inputNames = {"n1", "n2", "n3", "n4"};
        Drawable[] inputDrawables = new Drawable[inputNames.length];
        Assets assets = AssetsBean.getAssets();
        assets.addColor("red", 0xFFFF0000);
        Display display = DisplayBean.getDisplay();
        DrawableFactory drawableFactory = display.getDrawableFactory();
        for (int i = 0; i < inputNames.length; i++) {
            inputDrawables[i] = drawableFactory.makeRectangle(1, 1, 1, 1, "red");
        }
        Scene scene = SceneBean.getScene();
        //then1
        assertNull(scene.getCurrentObjectCollection());
        for (int i = 0; i < inputNames.length; i++) {
            //when
            scene.addCollection(inputNames[i]);
            scene.switchCollection(inputNames[i]);
            scene.addOnHighest(inputDrawables[i]);
            //then2
            assertSame(inputDrawables[i], scene.getCurrentObjectCollection().toArray()[0]);
        }
    }

    @Test
    public void remove_collection_test() {
        //given
        String[] inputNames = {"n1", "n2", "n3", "n4"};
        Drawable[] inputDrawables = new Drawable[inputNames.length];
        Assets assets = AssetsBean.getAssets();
        assets.addColor("red", 0xFFFF0000);
        Display display = DisplayBean.getDisplay();
        DrawableFactory drawableFactory = display.getDrawableFactory();
        for (int i = 0; i < inputNames.length; i++) {
            inputDrawables[i] = drawableFactory.makeRectangle(1, 1, 1, 1, "red");
        }
        Scene scene = SceneBean.getScene();
        for (int i = 0; i < inputNames.length; i++) {
            //when
            scene.addCollection(inputNames[i]);
            scene.switchCollection(inputNames[i]);
            scene.addOnHighest(inputDrawables[i]);
            scene.removeCollection(inputNames[i]);
            //then
            assertNull(scene.getCurrentObjectCollection());
        }
        //when2
        scene.removeCollection("n1");
        //then2
        assertNull(scene.getCurrentObjectCollection());
    }

    @Test
    public void switch_collection_test() {
        //given
        String[] inputNames = {"n1", "n2", "n3", "n4"};
        Drawable[] inputDrawables = new Drawable[inputNames.length];
        Assets assets = AssetsBean.getAssets();
        assets.addColor("red", 0xFFFF0000);
        Display display = DisplayBean.getDisplay();
        DrawableFactory drawableFactory = display.getDrawableFactory();
        for (int i = 0; i < inputNames.length; i++) {
            inputDrawables[i] = drawableFactory.makeRectangle(1, 1, 1, 1, "red");
        }
        Scene scene = SceneBean.getScene();
        for (int i = 0; i < inputNames.length; i++) {
            scene.addCollection(inputNames[i]);
        }
        String[] inputSwitchNames = {"n1", "n2", "n2", "n3", "n1", "n4", "n4", "n5"};
        int[] outputCheckIds = {0, 1, 1, 2, 0, 3, 3, 4};
        for (int i = 0; i < inputSwitchNames.length; i++) {
            //when
            scene.switchCollection(inputSwitchNames[i]);
            //then
            assertSame(inputDrawables[outputCheckIds[i]], scene.getCurrentObjectCollection().toArray()[0]);
        }
    }

}

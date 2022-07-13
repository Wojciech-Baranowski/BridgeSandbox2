package scene;

import assets.Assets;
import assets.AssetsBean;
import display.Display;
import display.DisplayBean;
import display.DrawableFactory;
import display.rectangle.Rectangle;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class DrawablePriorityCollectionTest {

    @Test
    public void get_top_object_on_position_test() {
        //given
        Assets assets = AssetsBean.getAssets();
        assets.addColor("red", 0xFFFF0000);
        assets.addColor("magenta", 0xFFFF00FF);
        Display display = DisplayBean.getDisplay();
        DrawableFactory drawableFactory = display.getDrawableFactory();
        Rectangle rectangle1 = drawableFactory.makeRectangle(0, 0, 100, 100, "red");
        Rectangle rectangle2 = drawableFactory.makeRectangle(50, 0, 100, 100, "red");
        Rectangle rectangle3 = drawableFactory.makeRectangle(20, 0, 20, 20, "magenta");
        DrawablePriorityCollection priorityCollection = new DrawablePriorityCollection(new PriorityList());
        priorityCollection.setOnHighest(rectangle1);
        priorityCollection.setOnHighest(rectangle2);
        priorityCollection.setOnHighest(rectangle3);
        int[] inputX = {10, 140, 90, 300, 30};
        int[] inputY = {50, 50, 50, 50, 30};
        Rectangle[] outputCheck = new Rectangle[]{rectangle1, rectangle2, rectangle2, null, rectangle1};
        Rectangle[] output = new Rectangle[inputX.length];
        //when
        for (int i = 0; i < inputX.length; i++) {
            output[i] = (Rectangle) priorityCollection.getTopObjectOnPosition(inputX[i], inputY[i]);
        }
        //then
        for (int i = 0; i < inputX.length; i++) {
            assertSame(outputCheck[i], output[i]);
        }
    }

}

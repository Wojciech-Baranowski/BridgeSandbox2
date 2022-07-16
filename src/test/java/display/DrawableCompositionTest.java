package display;

import assets.Assets;
import assets.AssetsBean;
import display.drawable.DrawableComposition;
import display.drawable.DrawableFactory;
import display.rectangle.Rectangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DrawableCompositionTest {

    @Test
    public void accessors_test() {
        //given
        int[] inputX = {4, 2, 1, 4, 5, 4, 5, 8, 9};
        int[] inputY = {8, 3, 4, 1, 7, 9, 2, 5, 6};
        int[] inputW = {5, 2, 8, 1, 2, 8, 4, 2, 3};
        int[] inputH = {3, 7, 1, 2, 8, 4, 4, 4, 3};
        int[] outputCheckX = {4, 2, 1, 1, 1, 1, 1, 1, 1};
        int[] outputCheckY = {8, 3, 3, 1, 1, 1, 1, 1, 1};
        int[] outputCheckW = {5, 7, 8, 8, 8, 11, 11, 11, 11};
        int[] outputCheckH = {3, 8, 8, 10, 14, 14, 14, 14, 14};
        String[] inputColorNames = {"n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9"};
        int[] inputColorValues = {0xFFFFFF00, 0xFF213769, 0xFFFFFFFF, 0x00000000, 0x12345678, 0xFFFF1234, 0x01010101, 0xFFFF1234, 0xFEDCBA98};
        DrawableFactory drawableFactory = new DrawableFactory();
        Assets assets = AssetsBean.getAssets();
        Rectangle[] inputRectangles = new Rectangle[inputX.length];
        for (int i = 0; i < inputX.length; i++) {
            assets.addColor(inputColorNames[i], inputColorValues[i]);
            inputRectangles[i] = drawableFactory.makeRectangle(inputX[i], inputY[i], inputW[i], inputH[i], inputColorNames[i]);
        }
        //when
        DrawableComposition output = new DrawableComposition(inputRectangles[0], inputRectangles[1]);
        //then
        assertEquals(outputCheckX[1], output.getX());
        assertEquals(outputCheckY[1], output.getY());
        assertEquals(outputCheckW[1], output.getW());
        assertEquals(outputCheckH[1], output.getH());
        for (int i = 2; i < inputX.length; i++) {
            //when2
            output = new DrawableComposition(output, inputRectangles[i]);
            //then2
            assertEquals(outputCheckX[i], output.getX());
            assertEquals(outputCheckY[i], output.getY());
            assertEquals(outputCheckW[i], output.getW());
            assertEquals(outputCheckH[i], output.getH());
        }
    }

}

package engine.display.rectangle;

import engine.assets.color.Color;
import engine.assets.color.ColorFactory;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RectangleTest {

    @Test
    public void accessors_test() {
        //given
        int[] inputX = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] inputY = {1, 4, 4, 1, 7, 9, 3, 5, 6};
        int[] inputW = {5, 2, 8, 1, 2, 8, 4, 2, 3};
        int[] inputH = {3, 7, 1, 2, 8, 4, 4, 4, 3};
        int[] inputColorValues = {0xFFFFFF00, 0xFF213769, 0xFFFFFFFF, 0x00000000, 0x12345678, 0xFFFF1234, 0x01010101, 0xFFFF1234, 0xFEDCBA98};
        int[][] inputPixels = new int[inputX.length][];
        for (int i = 0; i < inputX.length; i++) {
            inputPixels[i] = new int[inputW[i] * inputH[i]];
            Arrays.fill(inputPixels[i], inputColorValues[i]);
        }
        ColorFactory colorFactory = new ColorFactory();
        Color[] inputColors = new Color[inputX.length];
        for (int i = 0; i < inputX.length; i++) {
            inputColors[i] = colorFactory.makeArgbColor(inputColorValues[i]);
        }
        Rectangle[] output = new Rectangle[inputX.length];
        //when
        for (int i = 0; i < inputX.length; i++) {
            output[i] = new Rectangle(inputX[i], inputY[i], inputW[i], inputH[i], inputColors[i]);
        }
        //then
        for (int i = 0; i < output.length; i++) {
            assertEquals(inputX[i], output[i].getX());
            assertEquals(inputY[i], output[i].getY());
            assertEquals(inputW[i], output[i].getW());
            assertEquals(inputH[i], output[i].getH());
            assertArrayEquals(inputPixels[i], output[i].getP());
        }
    }

    @Test
    public void mutators_test() {
        //given
        ColorFactory colorFactory = new ColorFactory();
        Rectangle rectangle = new Rectangle(0, 0, 0, 0, colorFactory.makeArgbColor(0));
        int[] inputX = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] inputY = {1, 4, 4, 1, 7, 9, 3, 5, 6};
        for (int i = 0; i < inputX.length; i++) {
            //when
            rectangle.setX(inputX[i]);
            rectangle.setY(inputY[i]);
            //then
            assertEquals(inputX[i], rectangle.getX());
            assertEquals(inputY[i], rectangle.getY());
        }
    }

}

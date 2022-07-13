package display.rectangle;

import assets.color.Color;
import assets.color.ColorFactory;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class FramedRectangleTest {

    @Test
    public void get_p_test() {
        //given
        int[] inputX = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] inputY = {1, 4, 4, 1, 7, 9, 3, 5, 6};
        int[] inputW = {5, 2, 8, 1, 2, 8, 4, 2, 3};
        int[] inputH = {3, 7, 1, 2, 8, 4, 4, 4, 3};
        int[] inputColorValues = {0xFFFFFF00, 0xFF213769, 0xFFFFFFFF, 0x00000000, 0x12345678, 0xFFFF1234, 0x01010101, 0xFFFF1234, 0xFEDCBA98};
        int[] inputFrameColorValues = {0xFF345600, 0xFF213369, 0xFFF42FFF, 0xFFFF0000, 0x87654321, 0xFF001234, 0x02020202, 0xFFFF4321, 0x6543210F};
        int[] inputFrameThicknesses = {1, 1, 2, 2, 3, 2, 10, 1, 1};
        int[][] inputPixels = new int[inputX.length][];
        for(int i = 0; i < inputX.length; i++){
            inputPixels[i] = new int[inputW[i] * inputH[i]];
            Arrays.fill(inputPixels[i], inputFrameColorValues[i]);
            for(int j = inputFrameThicknesses[i]; j < inputW[i] - inputFrameThicknesses[i]; j++){
                for(int k = inputFrameThicknesses[i]; k < inputH[i] - inputFrameThicknesses[i]; k++){
                    inputPixels[i][j + k * inputW[i]] = inputColorValues[i];
                }
            }
        }
        ColorFactory colorFactory = new ColorFactory();
        Color[] inputColors = new Color[inputX.length];
        Color[] inputFrameColors = new Color[inputX.length];
        for(int i = 0; i < inputX.length; i++){
            inputColors[i] = colorFactory.makeArgbColor(inputColorValues[i]);
            inputFrameColors[i] = colorFactory.makeArgbColor(inputFrameColorValues[i]);
        }
        Rectangle[] output = new Rectangle[inputX.length];
        //when
        for(int i = 0; i < inputX.length; i++){
            output[i] = new FramedRectangle(inputX[i], inputY[i], inputW[i], inputH[i], inputFrameThicknesses[i], inputColors[i], inputFrameColors[i]);
        }
        //then
        for(int i = 0; i < output.length; i++){
            assertArrayEquals(inputPixels[i], output[i].getP());
        }
    }

}

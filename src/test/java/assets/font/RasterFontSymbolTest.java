package assets.font;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RasterFontSymbolTest {

    @Test
    public void accessors_test() {
        //given
        int[] inputW = {3, 3, 3, 4, 4, 4, 5, 5, 5};
        int[] inputH = {3, 4, 5, 3, 4, 5, 3, 4, 5};
        int[][] inputP = new int[inputW.length][];
        for (int i = 0; i < inputP.length; i++) {
            inputP[i] = new int[inputW[i] * inputH[i]];
            for (int j = 0; j < inputW[i] * inputH[i]; j++) {
                inputP[i][j] = j;
            }
        }
        RasterFontSymbol[] inputSymbol = new RasterFontSymbol[inputW.length];
        //when
        for (int i = 0; i < inputW.length; i++) {
            inputSymbol[i] = new RasterFontSymbol(inputP[i], inputW[i], inputH[i]);
        }
        //then
        for (int i = 0; i < inputSymbol.length; i++) {
            assertEquals(inputW[i], inputSymbol[i].getW());
            assertEquals(inputH[i], inputSymbol[i].getH());
            assertArrayEquals(inputP[i], inputSymbol[i].getP());
        }
    }

}

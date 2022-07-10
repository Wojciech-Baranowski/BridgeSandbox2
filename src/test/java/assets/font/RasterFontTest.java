package assets.font;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class RasterFontTest {

    @Test
    public void get_symbol_drawable_test() {
        //given
        String inputSymbols = "ABCabc123";
        BufferedImage inputImages = null;
        try {
            InputStream inputStream = RasterFontTest.class.getResourceAsStream("/testFont.png");
            inputImages = ImageIO.read(Objects.requireNonNull(inputStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int[] inputAllPixels = inputImages.getRGB(0, 0, 63, 9, null, 0, 63);
        RasterFontSymbol[] inputRasterables = new RasterFontSymbol[inputSymbols.length()];
        for(int i = 0; i < inputSymbols.length(); i++){
            int[] inputPixels = new int[63];
            for(int j = 0; j < 7; j++){
                for(int k = 0; k < 9; k++){
                    inputPixels[j + 7 * k] = inputAllPixels[i * 7 + j + 63 * k];
                }
            }
            inputRasterables[i] = new RasterFontSymbol(Arrays.copyOf(inputPixels, 63), 7, 9);
        }
        //when
        RasterFont output = new RasterFont(inputSymbols, inputRasterables);
        //then
        for(int i = 0; i < inputSymbols.length(); i++){
            assertEquals(inputRasterables[i], output.getSymbolRasterable(inputSymbols.charAt(i)));
        }
    }

}

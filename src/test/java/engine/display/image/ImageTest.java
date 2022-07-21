package engine.display.image;

import engine.assets.font.RasterFontTest;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ImageTest {

    @Test
    public void accessors_test() {
        //given
        BufferedImage inputImage = null;
        try {
            InputStream inputStream = RasterFontTest.class.getResourceAsStream("/testFont.png");
            inputImage = ImageIO.read(Objects.requireNonNull(inputStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int[] inputPixels = inputImage.getRGB(0, 0, 63, 9, null, 0, 63);
        int[] inputX = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] inputY = {1, 3, 5, 7, 9, 2, 4, 6, 8};
        int[] inputW = {63, 63, 63, 63, 63, 63, 63, 63, 63};
        int[] inputH = {9, 9, 9, 9, 9, 9, 9, 9, 9};
        Image[] inputImages = new Image[inputX.length];
        //when
        for (int i = 0; i < inputX.length; i++) {
            inputImages[i] = new Image(inputImage, inputX[i], inputY[i], inputW[i], inputH[i]);
        }
        //then
        for (int i = 0; i < inputX.length; i++) {
            assertArrayEquals(inputPixels, inputImages[i].getP());
            assertEquals(inputX[i], inputImages[i].getX());
            assertEquals(inputY[i], inputImages[i].getY());
            assertEquals(inputW[i], inputImages[i].getW());
            assertEquals(inputH[i], inputImages[i].getH());
        }
    }

}

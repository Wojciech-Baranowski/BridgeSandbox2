package display.image;

import assets.font.RasterFontTest;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ImageFactoryTest {

    @Test
    public void make_image_test() {
        //given
        String fileName = "/testFont.png";
        BufferedImage inputImage = null;
        try {
            InputStream inputStream = RasterFontTest.class.getResourceAsStream(fileName);
            inputImage = ImageIO.read(Objects.requireNonNull(inputStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int[] inputX = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] inputY = {1, 3, 5, 7, 9, 2, 4, 6, 8};
        int[] inputW = {63, 63, 63, 63, 63, 63, 63, 63, 63};
        int[] inputH = {9, 9, 9, 9, 9, 9, 9, 9, 9};
        Image[] inputImages = new Image[inputX.length];
        for (int i = 0; i < inputX.length; i++) {
            inputImages[i] = new Image(inputImage, inputX[i], inputY[i], inputW[i], inputH[i]);
        }
        ImageFactory imageFactory = new ImageFactory();
        Image[] output = new Image[inputX.length];
        //when
        for (int i = 0; i < inputX.length; i++) {
            output[i] = imageFactory.makeImage(fileName, inputX[i], inputY[i]);
        }
        //then
        for (int i = 0; i < inputX.length; i++) {
            assertArrayEquals(inputImages[i].getP(), output[i].getP());
            assertEquals(inputImages[i].getX(), output[i].getX());
            assertEquals(inputImages[i].getY(), output[i].getY());
            assertEquals(inputImages[i].getW(), output[i].getW());
            assertEquals(inputImages[i].getH(), output[i].getH());
        }
    }

}

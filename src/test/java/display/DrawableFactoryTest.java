package display;

import assets.Assets;
import assets.AssetsBean;
import display.image.Image;
import display.image.ImageFactory;
import display.rectangle.Rectangle;
import display.rectangle.RectangleFactory;
import display.text.Text;
import display.text.TextFactory;
import org.junit.Test;

import java.util.Arrays;

import static assets.Assets.getExtendedAlphabet;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DrawableFactoryTest {

    @Test
    public void make_rectangle_test() {
        //given
        int[] inputX = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] inputY = {1, 4, 4, 1, 7, 9, 3, 5, 6};
        int[] inputZ = {2, 4, 5, 0, 6, 0, -3, -4, -7};
        int[] inputW = {5, 2, 8, 1, 2, 8, 4, 2, 3};
        int[] inputH = {3, 7, 1, 2, 8, 4, 4, 4, 3};
        String[] inputColorNames = {"n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9"};
        int[] inputColorValues = {0xFFFFFF00, 0xFF213769, 0xFFFFFFFF, 0x00000000, 0x12345678, 0xFFFF1234, 0x01010101, 0xFFFF1234, 0xFEDCBA98};
        Assets assets = AssetsBean.getAssets();
        for(int i = 0; i < inputX.length; i++){
            assets.addColor(inputColorNames[i], inputColorValues[i]);
        }
        Rectangle[] inputRectangles = new Rectangle[inputX.length];
        RectangleFactory rectangleFactory = new RectangleFactory();
        for(int i = 0; i < inputX.length; i++){
            inputRectangles[i] = rectangleFactory.makeRectangle(inputX[i], inputY[i], inputZ[i], inputW[i], inputH[i], inputColorNames[i]);
        }
        DrawableFactory drawableFactory = new DrawableFactory();
        Rectangle[] output = new Rectangle[inputX.length];
        //when
        for(int i = 0; i < inputX.length; i++) {
            output[i] = drawableFactory.makeRectangle(inputX[i], inputY[i], inputZ[i], inputW[i], inputH[i], inputColorNames[i]);
        }
        //then
        for(int i = 0; i < inputX.length; i++) {
            assertArrayEquals(inputRectangles[i].getP(), output[i].getP());
            assertEquals(inputRectangles[i].getX(), output[i].getX());
            assertEquals(inputRectangles[i].getY(), output[i].getY());
            assertEquals(inputRectangles[i].getZ(), output[i].getZ());
            assertEquals(inputRectangles[i].getW(), output[i].getW());
            assertEquals(inputRectangles[i].getH(), output[i].getH());
        }
    }

    @Test
    public void make_framed_rectangle_test() {
        //given
        int[] inputX = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] inputY = {1, 4, 4, 1, 7, 9, 3, 5, 6};
        int[] inputZ = {2, 4, 5, 0, 6, 0, -3, -4, -7};
        int[] inputW = {5, 2, 8, 1, 2, 8, 4, 2, 3};
        int[] inputH = {3, 7, 1, 2, 8, 4, 4, 4, 3};
        int[] inputColorValues = {0xFFFFFF00, 0xFF213769, 0xFFFFFFFF, 0x00000000, 0x12345678, 0xFFFF1234, 0x01010101, 0xFFFF1234, 0xFEDCBA98};
        String[] inputColorNames = {"n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9"};
        int[] inputFrameColorValues = {0xFF345600, 0xFF213369, 0xFFF42FFF, 0xFFFF0000, 0x87654321, 0xFF001234, 0x02020202, 0xFFFF4321, 0x6543210F};
        String[] inputFrameColorNames = {"f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9"};
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
        Assets assets = AssetsBean.getAssets();
        for(int i = 0; i < inputX.length; i++){
            assets.addColor(inputColorNames[i], inputColorValues[i]);
            assets.addColor(inputFrameColorNames[i], inputFrameColorValues[i]);
        }
        RectangleFactory rectangleFactory = new RectangleFactory();
        Rectangle[] inputRectangles = new Rectangle[inputX.length];
        for(int i = 0; i < inputX.length; i++){
            inputRectangles[i] = rectangleFactory.makeFramedRectangle(inputX[i], inputY[i], inputZ[i], inputW[i], inputH[i], inputFrameThicknesses[i], inputColorNames[i], inputFrameColorNames[i]);
        }
        DrawableFactory drawableFactory = new DrawableFactory();
        Rectangle[] output = new Rectangle[inputX.length];
        //when
        for(int i = 0; i < inputX.length; i++) {
            output[i] = drawableFactory.makeFramedRectangle(inputX[i], inputY[i], inputZ[i], inputW[i], inputH[i], inputFrameThicknesses[i], inputColorNames[i], inputFrameColorNames[i]);
        }
        //then
        for(int i = 0; i < inputX.length; i++) {
            assertArrayEquals(inputRectangles[i].getP(), output[i].getP());
            assertEquals(inputRectangles[i].getX(), output[i].getX());
            assertEquals(inputRectangles[i].getY(), output[i].getY());
            assertEquals(inputRectangles[i].getZ(), output[i].getZ());
            assertEquals(inputRectangles[i].getW(), output[i].getW());
            assertEquals(inputRectangles[i].getH(), output[i].getH());
        }
    }

    @Test
    public void make_text_test() {
        //given
        String[] inputTexts = new String[]{"A", "A ~", "123\n1234\n123", "A\u2664\u2665$\u2667"};
        int[] inputX = {10, 20, 30, 40};
        int[] inputY = {20, 40, 60, 80};
        int[] inputZ = {-5, -4, 0, 2};
        Assets assets = AssetsBean.getAssets();
        assets.addFont("HBE24", "/HelveticaBoldExtended24.png", getExtendedAlphabet());
        assets.addColor("red", 0xFFFF0000);
        Text[] inputTexts2 = new Text[inputX.length];
        TextFactory textFactory = new TextFactory();
        for(int i = 0; i < inputX.length; i++){
            inputTexts2[i] = textFactory.makeText(inputTexts[i], inputX[i], inputY[i], inputZ[i], "HBE24", "red");
        }
        DrawableFactory drawableFactory = new DrawableFactory();
        Text[] output = new Text[inputX.length];
        //when
        for(int i = 0; i < inputX.length; i++){
            output[i] = drawableFactory.makeText(inputTexts[i], inputX[i], inputY[i], inputZ[i], "HBE24", "red");
        }
        //then
        for(int i = 0; i < inputX.length; i++){
            assertArrayEquals(inputTexts2[i].getP(), output[i].getP());
            assertEquals(inputTexts2[i].getX(), output[i].getX());
            assertEquals(inputTexts2[i].getY(), output[i].getY());
            assertEquals(inputTexts2[i].getZ(), output[i].getZ());
            assertEquals(inputTexts2[i].getW(), output[i].getW());
            assertEquals(inputTexts2[i].getH(), output[i].getH());
        }
    }

    @Test
    public void make_image_test() {
        //given
        String fileName = "/testFont.png";
        int[] inputX = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] inputY = {1, 3, 5, 7, 9, 2, 4, 6, 8};
        int[] inputZ = {-4, -3, -2, -1, 0, 1, 2, 3, 4};
        ImageFactory imageFactory = new ImageFactory();
        Image[] inputImages = new Image[inputX.length];
        for(int i = 0; i < inputX.length; i++){
            inputImages[i] = imageFactory.makeImage(fileName, inputX[i], inputY[i], inputZ[i]);
        }
        DrawableFactory drawableFactory = new DrawableFactory();
        Image[] output = new Image[inputX.length];
        //when
        for(int i = 0; i < inputX.length; i++){
            output[i] = drawableFactory.makeImage(fileName, inputX[i], inputY[i], inputZ[i]);
        }
        //then
        for(int i = 0; i < inputX.length; i++){
            assertArrayEquals(inputImages[i].getP(), output[i].getP());
            assertEquals(inputImages[i].getX(), output[i].getX());
            assertEquals(inputImages[i].getY(), output[i].getY());
            assertEquals(inputImages[i].getZ(), output[i].getZ());
            assertEquals(inputImages[i].getW(), output[i].getW());
            assertEquals(inputImages[i].getH(), output[i].getH());
        }
    }

}

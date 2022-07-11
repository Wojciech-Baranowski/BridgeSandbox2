package display;

import assets.Assets;
import assets.AssetsBean;
import display.rectangle.Rectangle;
import display.text.Text;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static assets.font.Font.getExtendedAlphabet;
import static org.junit.Assert.*;

public class DisplayBeanTest {

    @Test
    public void get_Display_test() {
        //given
        Display input1 = null;
        DisplayBean input2 = null;
        Display input3;
        DisplayBean input4;
        Display input5;
        DisplayBean input6;
        //when
        input3 = DisplayBean.getDisplay();
        input4 = (DisplayBean) DisplayBean.getDisplay();
        input5 = DisplayBean.getDisplay();
        input6 = (DisplayBean) DisplayBean.getDisplay();
        //then
        assertNull(input1);
        assertNull(input2);
        assertNotNull(input3);
        assertNotNull(input4);
        assertEquals(input3, input5);
        assertEquals(input4, input6);
    }

    public static void main(String[] args) {
        DisplayBeanTest displayBeanTest = new DisplayBeanTest();
        displayBeanTest.simple_draw_manual_test();
        displayBeanTest.transparent_draw_manual_test();
    }

    public void simple_draw_manual_test() {
        int inputX1 = 10;
        int inputY1 = 30;
        int inputZ1 = 1;
        int inputW = 300;
        int inputH = 100;
        int inputX2 = 250;
        int inputY2 = 100;
        int inputZ2 = 5;
        int inputThickness = 3;
        String inputText1 = "ABCD1234$$$\u2666\nqwer tyWWWW   WWW WW";
        Assets assets = AssetsBean.getAssets();
        assets.addFont("HBE32", "/HelveticaBoldExtended32.png", getExtendedAlphabet());
        assets.addColor("red", 0xFFFF0000);
        assets.addColor("green", 0xFF00FF00);
        assets.addColor("blue", 0xFF0000FF);
        Display display = DisplayBean.getDisplay();
        DrawableFactory drawableFactory = display.getDrawableFactory();
        Rectangle inputRectangle = drawableFactory.makeFramedRectangle(inputX1, inputY1, inputZ1, inputW, inputH, inputThickness, "red", "green");
        Text inputText2 = drawableFactory.makeText(inputText1, inputX2, inputY2, inputZ2, "HBE32", "blue");
        List<Drawable> inputList = new LinkedList<>();
        inputList.add(inputRectangle);
        inputList.add(inputText2);
        inputList.sort((m1, m2) -> m1.compareTo(m2.getZ()));
        display.setObjectsToDraw(inputList);
        //when
        display.draw();
        //then
        //red 300 x 100 rectangle with 3px green frame starting at (10; 30), with 48px two line "ABCD1234$$$<Diamond>" <\n> "qwer tyWWWW   WWW WW" text above, starting at (250, 100), colored blue
    }

    public void transparent_draw_manual_test() {
        int[] inputX = {10, 50, 80};
        int[] inputY = {410, 420, 430};
        int[] inputZ = {1, 2, 3};
        int[] inputW = {100, 100, 100};
        int[] inputH = {40, 50, 60};
        String[] inputColorNames = new String[]{"red", "green", "blue"};
        Assets assets = AssetsBean.getAssets();
        assets.addColor("red", 0xD2FF0000);
        assets.addColor("green", 0x8800FF00);
        assets.addColor("blue", 0x530000FF);
        Display display = DisplayBean.getDisplay();
        DrawableFactory drawableFactory = display.getDrawableFactory();
        Rectangle[] inputRectangles = new Rectangle[inputX.length];
        for(int i = 0; i < inputX.length; i++){
            inputRectangles[i] = drawableFactory.makeRectangle(inputX[i], inputY[i], inputZ[i], inputW[i], inputH[i], inputColorNames[i]);
        }
        List<Drawable> inputList = new LinkedList<>();
        for(int i = 0; i < inputX.length; i++){
            inputList.add(inputRectangles[i]);
        }
        inputList.sort((m1, m2) -> m1.compareTo(m2.getZ()));
        display.setObjectsToDraw(inputList);
        //when
        display.draw();
        //then
        //three overlapping rectangles, placed at (10; 410), (50; 420) and (80; 430) of sizes 100 x 40, 100 x 50, 100 x 60, colored red, green and blue
    }

}

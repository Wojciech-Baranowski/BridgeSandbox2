package engine.display;

import engine.assets.Assets;
import engine.assets.AssetsBean;
import engine.assets.color.Color;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HoverMarkTest {

    @Test
    public void get_hover_mark_test() {
        //given
        HoverMark hoverMark1 = null;
        HoverMark hoverMark2 = null;
        HoverMark hoverMark3;
        HoverMark hoverMark4;
        //when
        hoverMark3 = HoverMark.getHoverMark();
        hoverMark4 = HoverMark.getHoverMark();
        //then
        assertNull(hoverMark1);
        assertNull(hoverMark2);
        assertNotNull(hoverMark3);
        assertNotNull(hoverMark4);
    }

    @Test
    public void fit_hover_mark_to_drawable_test() {
        //given
        int[] inputX = {2, 5, 7, 1, 2};
        int[] inputY = {6, 2, 8, 1, 2};
        int[] inputW = {10, 40, 20, 60, 40};
        int[] inputH = {20, 30, 40, 50, 10};
        Assets assets = AssetsBean.getAssets();
        assets.addColor("red", 0xFFFF0000);
        Display display = DisplayBean.getDisplay();
        DrawableFactory drawableFactory = display.getDrawableFactory();
        Drawable[] inputDrawables = new Drawable[inputX.length];
        for (int i = 0; i < inputX.length; i++) {
            inputDrawables[i] = drawableFactory.makeRectangle(inputX[i], inputY[i], inputW[i], inputH[i], "red");
        }
        int[][] pixelsOutputCheck = new int[inputX.length][];
        for (int i = 0; i < inputX.length; i++) {
            pixelsOutputCheck[i] = new int[inputW[i] * inputH[i]];
            Arrays.fill(pixelsOutputCheck[i], Color.getHoverMarkColorValue());
        }
        HoverMark output = HoverMark.getHoverMark();
        for(int i = 0; i < inputX.length; i++){
            //when
            output.fitHoverMarkToDrawable(inputDrawables[i]);
            //then
            assertEquals(inputX[i], output.getX());
            assertEquals(inputY[i], output.getY());
            assertEquals(inputW[i], output.getW());
            assertEquals(inputH[i], output.getH());
            assertArrayEquals(pixelsOutputCheck[i], output.getP());
        }
    }

}

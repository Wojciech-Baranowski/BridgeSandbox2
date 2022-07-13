package display.text;

import assets.Assets;
import assets.AssetsBean;
import assets.color.Color;
import assets.font.Font;
import org.junit.Test;

import static assets.font.Font.getExtendedAlphabet;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TextFactoryTest {

    @Test
    public void make_text_test() {
        //given
        String[] inputTexts = new String[]{"A", "A ~", "123\n1234\n123", "A\u2664\u2665$\u2667"};
        int[] inputX = {10, 20, 30, 40};
        int[] inputY = {20, 40, 60, 80};
        Assets assets = AssetsBean.getAssets();
        assets.addFont("HBE24", "/HelveticaBoldExtended24.png", getExtendedAlphabet());
        assets.addColor("red", 0xFFFF0000);
        Font inputFont = assets.getFont("HBE24");
        Color inputColor = assets.getColor("red");
        Text[] inputTexts2 = new Text[inputX.length];
        for(int i = 0; i < inputX.length; i++){
            inputTexts2[i] = new Text(inputTexts[i], inputX[i], inputY[i], inputFont, inputColor);
        }
        TextFactory textFactory = new TextFactory();
        Text[] output = new Text[inputX.length];
        //when
        for(int i = 0; i < inputX.length; i++){
            output[i] = textFactory.makeText(inputTexts[i], inputX[i], inputY[i], "HBE24", "red");
        }
        //then
        for(int i = 0; i < inputX.length; i++){
            assertArrayEquals(inputTexts2[i].getP(), output[i].getP());
            assertEquals(inputTexts2[i].getX(), output[i].getX());
            assertEquals(inputTexts2[i].getY(), output[i].getY());
            assertEquals(inputTexts2[i].getW(), output[i].getW());
            assertEquals(inputTexts2[i].getH(), output[i].getH());
        }
    }

}

package display.text;

import assets.Assets;
import assets.AssetsBean;
import assets.color.Color;
import assets.font.Font;
import common.Rasterable;
import org.junit.Test;

import java.util.Arrays;

import static assets.font.Font.getExtendedAlphabet;
import static assets.color.Color.getTransparentColorValue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TextTest {

    @Test
    public void accessors_test() {
        //given
        String[] inputTexts = new String[]{"A", "A ~", "123\n1234\n123", "A\u2664\u2665$\u2667"};
        int[] inputX = {10, 20, 30, 40};
        int[] inputY = {20, 40, 60, 80};
        int[] inputZ = {-5, -4, 0, 2};
        int[] inputW = {20, 47, 63, 114};
        int[] inputH = {27, 27, 81, 27};
        Assets assets = AssetsBean.getAssets();
        assets.addFont("HBE24", "/HelveticaBoldExtended24.png", getExtendedAlphabet());
        assets.addColor("red", 0xFFFF0000);
        Font inputFont = assets.getFont("HBE24");
        Color inputColor = assets.getColor("red");
        int[][] inputPixels = new int[inputX.length][];
        for(int i = 0; i < inputX.length; i++){
            inputPixels[i] = new int[inputW[i] * inputH[i]];
            Arrays.fill(inputPixels[i], getTransparentColorValue());
            int currX = 0;
            int currY = 0;
            for(int j = 0; j < inputTexts[i].length(); j++){
                if(inputTexts[i].charAt(j) == ' '){
                    currX += 8;
                    continue;
                }
                if(inputTexts[i].charAt(j) == '\n'){
                    currX = 0;
                    currY += 27;
                    continue;
                }
                Rasterable inputRasterable = inputFont.getSymbolRasterable(inputTexts[i].charAt(j));
                for(int x = currX; x < currX + inputRasterable.getW(); x++){
                    for(int y = currY; y < currY + inputRasterable.getH(); y++){
                        int pixel = inputRasterable.getP()[x - currX + (y - currY) * inputRasterable.getW()];
                        inputPixels[i][x + y * inputW[i]] = (pixel != getTransparentColorValue() ? inputColor.getValue() : pixel);
                    }
                }
                currX += inputRasterable.getW() + 3;
            }
        }
        Text[] output = new Text[inputX.length];
        //when
        for(int i = 0; i < inputX.length; i++){
            output[i] = new Text(inputTexts[i], inputX[i], inputY[i], inputZ[i], inputFont, inputColor);
        }
        //then
        for(int i = 0; i < inputX.length; i++) {
            assertEquals(inputX[i], output[i].getX());
            assertEquals(inputY[i], output[i].getY());
            assertEquals(inputZ[i], output[i].getZ());
            assertEquals(inputW[i], output[i].getW());
            assertEquals(inputH[i], output[i].getH());
            assertArrayEquals(inputPixels[i], output[i].getP());
        }
    }

    @Test
    public void mutators_test() {
        //given
        int[] inputX = {10, 20, 30, 40};
        int[] inputY = {20, 40, 60, 80};
        int[] inputZ = {-5, -4, 0, 2};
        String inputText1 = "A";
        String inputText2 = "B";
        int inputW = 20;
        int inputH = 27;
        Assets assets = AssetsBean.getAssets();
        assets.addFont("HBE24", "/HelveticaBoldExtended24.png", getExtendedAlphabet());
        assets.addColor("red", 0xFFFF0000);
        Font inputFont = assets.getFont("HBE24");
        Color inputColor = assets.getColor("red");
        Rasterable inputRasterable = inputFont.getSymbolRasterable(inputText1.charAt(0));
        int[] inputPixels = new int[inputW * inputH];
        Arrays.fill(inputPixels, getTransparentColorValue());
        for(int x = 0; x < inputRasterable.getW(); x++){
            for(int y = 0; y < inputRasterable.getH(); y++){
                int pixel = inputRasterable.getP()[x + y * inputRasterable.getW()];
                inputPixels[x + y * inputW] = (pixel != getTransparentColorValue() ? inputColor.getValue() : pixel);
            }
        }
        //when1
        Text output = new Text(inputText2, 0, 0, 0, inputFont, inputColor);
        output.setText(inputText1);
        //then1
        assertArrayEquals(inputPixels, output.getP());
        for(int i = 0; i < inputX.length; i++){
            //when2
            output.setX(inputX[i]);
            output.setY(inputY[i]);
            output.setZ(inputZ[i]);
            //then2
            assertEquals(inputX[i], output.getX());
            assertEquals(inputY[i], output.getY());
            assertEquals(inputZ[i], output.getZ());
        }
    }

}

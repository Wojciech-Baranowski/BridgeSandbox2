import assets.Assets;
import assets.AssetsBean;
import assets.font.RasterFontSymbol;
import assets.font.RasterFontTest;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.*;

public class AssetsBeanTest {

    @Test
    public void get_assets_test() {
        //given
        Assets input1 = null;
        AssetsBean input2 = null;
        Assets input3;
        AssetsBean input4;
        Assets input5;
        AssetsBean input6;
        //when
        input3 = AssetsBean.getAssets();
        input4 = (AssetsBean) AssetsBean.getAssets();
        input5 = AssetsBean.getAssets();
        input6 = (AssetsBean) AssetsBean.getAssets();
        //then
        assertNull(input1);
        assertNull(input2);
        assertNotNull(input3);
        assertNotNull(input4);
        assertEquals(input3, input5);
        assertEquals(input4, input6);
    }
    @Test
    public void add_and_get_color_test() {
        //given
        String[] inputName = {"n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9"};
        int[] inputValue = {0xFFFFFF00, 0xFF213769, 0xFF213769, 0x00000000, 0x12345678, 0xFFFF1234, 0x01010101};
        Assets inputAssets = AssetsBean.getAssets();
        //when
        for(int i = 0; i < inputValue.length; i++){
            inputAssets.addColor(inputName[i], inputValue[i]);
        }
        //then
        for(int i = 0; i < inputValue.length; i++){
            assertEquals(inputValue[i], inputAssets.getColor(inputName[i]).getValue());
        }
        for(int i = inputValue.length; i < inputName.length; i++){
            assertNull(inputAssets.getColor(inputName[i]));
        }
    }

    @Test
    public void add_and_get_font_test() {
        //given
        String inputName = "name";
        String inputPath = "/testFont.png";
        char[] inputSymbols = {'A', 'B', 'C', 'a', 'b', 'c', '1', '2', '3'};
        BufferedImage inputImages = null;
        try {
            InputStream inputStream = RasterFontTest.class.getResourceAsStream("/testFont.png");
            inputImages = ImageIO.read(Objects.requireNonNull(inputStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int[] inputAllPixels = inputImages.getRGB(0, 0, 63, 9, null, 0, 63);
        int[][] inputPixels = new int[inputSymbols.length][];
        for(int i = 0; i < inputSymbols.length; i++){
            inputPixels[i] = new int[63];
            for(int j = 0; j < 7; j++){
                for(int k = 0; k < 9; k++){
                    inputPixels[i][j + 7 * k] = inputAllPixels[i * 7 + j + 63 * k];
                }
            }
        }
        Assets inputAssets = AssetsBean.getAssets();
        //when
        inputAssets.addFont(inputName, inputPath, inputSymbols);
        //then
        for(int i = 0; i < inputSymbols.length; i++){
            assertArrayEquals(inputPixels[i], inputAssets.getFont(inputName).getSymbolRasterable(inputSymbols[i]).getP());
            assertEquals(7, inputAssets.getFont(inputName).getSymbolRasterable(inputSymbols[i]).getW());
            assertEquals(9, inputAssets.getFont(inputName).getSymbolRasterable(inputSymbols[i]).getH());
        }
        assertNull(inputAssets.getColor("g"));
        assertNull(inputAssets.getFont("d"));
        assertNull(inputAssets.getFont("4"));
    }

}

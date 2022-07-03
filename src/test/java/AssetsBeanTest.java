import assets.Assets;
import assets.AssetsBean;
import org.junit.Test;

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

}

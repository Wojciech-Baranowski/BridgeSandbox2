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

}

package engine.assets.color;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColorFactoryTest {

    @Test
    public void make_argb_color_test() {
        //given
        int[] input = {0xFFFFFF00, 0xFE213769, 0xFFFFFFFF, 0x00000000, 0x12345678, 0xFFFF1234, 0x01010203};
        Color[] output = new ArgbColor[input.length];
        ColorFactory colorFactory = new ColorFactory();
        //when
        for (int i = 0; i < output.length; i++) {
            output[i] = colorFactory.makeArgbColor(input[i]);
        }
        //then
        for (int i = 0; i < output.length; i++) {
            assertEquals(input[i], output[i].getValue());
        }
    }

}

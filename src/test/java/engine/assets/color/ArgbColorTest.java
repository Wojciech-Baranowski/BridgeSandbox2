package engine.assets.color;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgbColorTest {

    @Test
    public void get_value_test() {
        //given
        int[] input = {0xFFFFFF00, 0xFF213769, 0xFFFFFFFF, 0x00000000, 0x12345678, 0xFFFF1234, 0x01010101};
        int[] output = new int[input.length];
        //when
        for (int i = 0; i < input.length; i++) {
            output[i] = new ArgbColor(input[i]).getValue();
        }
        //then
        for (int i = 0; i < output.length; i++) {
            assertEquals(input[i], output[i]);
        }
    }

    @Test
    public void blend_test() {
        //given
        int[] input = {0xFFFFFF00, 0xFE213769, 0xFFFFFFFF, 0x00000000, 0x12345678, 0xFFFF1234, 0x01010203};
        int[] input2 = {0x34656665, 0xFFFF00FF, 0x22334455, 0x12345678, 0x00FFFFFF, 0xFE050505, 0xEDFEAECB};
        int[] outputCheckInt = {0xFFDFDF14, 0xFFFF00FF, 0xFFE3E6E8, 0xFF030608, 0xFF345678, 0xFF050505, 0xFFECA1BC};
        Color[] inputColor = new ArgbColor[input.length];
        Color[] inputColor2 = new ArgbColor[input.length];
        ArgbColor[] outputCheck = new ArgbColor[input.length];
        for (int i = 0; i < outputCheck.length; i++) {
            inputColor[i] = new ArgbColor(input[i]);
            inputColor2[i] = new ArgbColor(input2[i]);
            outputCheck[i] = new ArgbColor(outputCheckInt[i]);
        }
        ArgbColor[] output = new ArgbColor[input.length];
        //when
        for (int i = 0; i < input.length; i++) {
            output[i] = (ArgbColor) inputColor[i].blend(inputColor2[i]);
        }
        //then
        for (int i = 0; i < output.length; i++) {
            assertEquals(outputCheck[i].getValue(), output[i].getValue());
        }
    }

    @Test
    public void is_transparent_test() {
        //given
        int[] input = {0xFFFFFF00, 0xFE213769, 0xFFFFFFFF, 0x00000000, 0x12345678, 0xFFFF1234, 0x01010101};
        boolean[] outputCheck = {false, true, false, true, true, false, true};
        boolean[] output = new boolean[input.length];

        //when
        for (int i = 0; i < input.length; i++) {
            output[i] = new ArgbColor(input[i]).isTransparent();
        }
        //then
        for (int i = 0; i < output.length; i++) {
            assertEquals(outputCheck[i], output[i]);
        }
    }

}

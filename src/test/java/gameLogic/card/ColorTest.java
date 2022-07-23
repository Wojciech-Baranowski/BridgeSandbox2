package gameLogic.card;

import org.junit.Test;

import static gameLogic.card.Color.*;
import static org.junit.Assert.assertEquals;

public class ColorTest {

    @Test
    public void get_symbol_test() {
        //given
        Color[] inputColor = new Color[]{CLUB, DIAMOND, HEART, SPADE, NOATU, SPADE, SPADE};
        char[] outputCheck = new char[]{'\u2667', '\u2666', '\u2665', '\u2664', '\u2668', '\u2664', '\u2664'};
        char[] output = new char[inputColor.length];
        //when
        for (int i = 0; i < inputColor.length; i++) {
            output[i] = inputColor[i].getSymbol();
        }
        //then
        for (int i = 0; i < inputColor.length; i++) {
            assertEquals(outputCheck[i], output[i]);
        }
    }

    @Test
    public void ordinal_test() {
        //given
        Color[] inputColor = new Color[]{CLUB, DIAMOND, HEART, SPADE, NOATU, SPADE, SPADE};
        int[] outputCheck = new int[]{0, 1, 2, 3, 4, 3, 3};
        int[] output = new int[inputColor.length];
        //when
        for (int i = 0; i < inputColor.length; i++) {
            output[i] = inputColor[i].ordinal();
        }
        //then
        for (int i = 0; i < inputColor.length; i++) {
            assertEquals(outputCheck[i], output[i]);
        }
    }

}

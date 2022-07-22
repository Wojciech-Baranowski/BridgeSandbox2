package gameLogic.card;

import org.junit.Test;

import static gameLogic.card.Color.*;
import static org.junit.Assert.assertEquals;

public class ColorTest {

    @Test
    public void get_ascii_test() {
        //given
        Color[] inputColor = new Color[]{CLUB, DIAMOND, HEART, SPADE, NOATU, SPADE, SPADE};
        char[] outputCheck = new char[]{'\u2667', '\u2666', '\u2665', '\u2664', '\u2668', '\u2664', '\u2664'};
        char[] output = new char[inputColor.length];
        //when
        for(int i = 0; i < inputColor.length; i++) {
            output[i] = inputColor[i].getAscii();
        }
        //then
        for(int i = 0; i < inputColor.length; i++) {
            assertEquals(outputCheck[i], output[i]);
        }
    }

}

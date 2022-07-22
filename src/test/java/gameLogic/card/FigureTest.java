package gameLogic.card;

import org.junit.Test;

import static gameLogic.card.Figure.*;
import static org.junit.Assert.assertEquals;

public class FigureTest {

    @Test
    public void get_symbol_test() {
        //given
        Figure[] inputFigure = new Figure[]{_2, _4, _9, _10, J, Q, K, A, A};
        char[] outputCheck = new char[]{'2', '4', '9', '\u2669', 'J', 'Q', 'K', 'A', 'A' };
        char[] output = new char[inputFigure.length];
        //when
        for (int i = 0; i < inputFigure.length; i++) {
            output[i] = inputFigure[i].getSymbol();
        }
        //then
        for (int i = 0; i < inputFigure.length; i++) {
            assertEquals(outputCheck[i], output[i]);
        }
    }

}

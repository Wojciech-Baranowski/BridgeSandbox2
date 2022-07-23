package gameLogic.card;

import org.junit.Test;

import static gameLogic.card.Figure.*;
import static org.junit.Assert.assertEquals;

public class FigureTest {

    @Test
    public void get_symbol_test() {
        //given
        Figure[] inputFigure = new Figure[]{_2, _4, _9, _10, J, Q, K, A, A};
        char[] outputCheck = new char[]{'2', '4', '9', '\u2669', 'J', 'Q', 'K', 'A', 'A'};
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

    @Test
    public void ordinal_test() {
        //given
        Figure[] inputFigure = new Figure[]{_2, _4, _9, _10, J, Q, K, A, A};
        int[] outputCheck = new int[]{0, 2, 7, 8, 9, 10, 11, 12, 12};
        int[] output = new int[inputFigure.length];
        //when
        for (int i = 0; i < inputFigure.length; i++) {
            output[i] = inputFigure[i].ordinal();
        }
        //then
        for (int i = 0; i < inputFigure.length; i++) {
            assertEquals(outputCheck[i], output[i]);
        }
    }

}

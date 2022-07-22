package gameLogic.card;

import org.junit.Test;

import static gameLogic.card.Color.*;
import static gameLogic.card.Figure.*;
import static java.lang.Math.min;
import static org.junit.Assert.assertEquals;

public class CardTest {

    @Test
    public void accessors_test() {
        //given
        Figure[] inputFigures = new Figure[]{_2, _10, J, A};
        Color[] inputColors = new Color[]{CLUB, CLUB, HEART, SPADE};
        int[] inputIds = {14, 27, 33, 51};
        int size = min(inputFigures.length, inputColors.length) + inputIds.length;
        Card[] inputCards = new Card[size];
        for (int i = 0; i < min(inputFigures.length, inputColors.length); i++) {
            inputCards[i] = new Card(inputFigures[i], inputColors[i]);
        }
        for (int i = min(inputFigures.length, inputColors.length); i < size; i++) {
            inputCards[i] = new Card(inputIds[i - min(inputFigures.length, inputColors.length)]);
        }
        Figure[] outputFiguresCheck = new Figure[]{_2, _10, J, A, _3, _3, _9, A};
        Color[] outputColorsCheck = new Color[]{CLUB, CLUB, HEART, SPADE, DIAMOND, HEART, HEART, SPADE};
        int[] outputIdsCheck = {0, 8, 35, 51, 14, 27, 33, 51};
        Figure[] outputFigures = new Figure[size];
        Color[] outputColors = new Color[size];
        int[] outputIds = new int[size];
        //when
        for (int i = 0; i < size; i++) {
            outputFigures[i] = inputCards[i].getFigure();
            outputColors[i] = inputCards[i].getColor();
            outputIds[i] = inputCards[i].getId();
        }
        //then
        for (int i = 0; i < size; i++) {
            assertEquals(outputFiguresCheck[i], outputFigures[i]);
            assertEquals(outputColorsCheck[i], outputColors[i]);
            assertEquals(outputIdsCheck[i], outputIds[i]);
        }
    }

}

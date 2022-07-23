package gameLogic.card;

import org.junit.Test;

import java.util.List;

import static gameLogic.card.Color.*;
import static gameLogic.card.Figure.*;
import static gameLogic.game.GameConstants.FIGURE_NUMBER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static java.lang.Math.min;
import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void get_deck_test() {
        //given
        Deck input1 = null;
        Deck input2 = null;
        Deck input3;
        Deck input4;
        //when
        input3 = Deck.getDeck();
        input4 = Deck.getDeck();
        //then
        assertNull(input1);
        assertNull(input2);
        assertNotNull(input3);
        assertNotNull(input4);
    }

    @Test
    public void accessors_test() {
        //given
        Deck deck = Deck.getDeck();
        Figure[] inputFigures = new Figure[]{_2, _10, J, A};
        Color[] inputColors = new Color[]{CLUB, CLUB, HEART, SPADE};
        int[] inputIds = {14, 27, 33, 51};
        int size = min(inputFigures.length, inputColors.length) + inputIds.length;
        int[] outputCheck = {0, 8, 35, 51, 14, 27, 33, 51};
        int[] output = new int[size];
        //when1
        for (int i = 0; i < min(inputFigures.length, inputColors.length); i++) {
            Card card = deck.getCard(inputFigures[i], inputColors[i]);
            output[i] = card.getId();
        }
        //when2
        for (int i = 0; i < inputIds.length; i++) {
            Card card = deck.getCard(inputIds[i]);
            output[i + min(inputFigures.length, inputColors.length)] = card.getId();
        }
        //then
        for (int i = 0; i < size; i++) {
            assertEquals(outputCheck[i], output[i]);
        }
    }

    @Test
    public void deal_cards_test() {
        //given
        int[] inputNumberOfCardsPerPlayer = {6, 13, 15};
        Deck deck = Deck.getDeck();
        List<Card>[][] output;
        output = new List[inputNumberOfCardsPerPlayer.length][];
        //when
        for (int i = 0; i < inputNumberOfCardsPerPlayer.length; i++) {
            output[i] = deck.dealCards(inputNumberOfCardsPerPlayer[i]);
        }
        //then1
        for (int i = 0; i < inputNumberOfCardsPerPlayer.length; i++) {
            assertEquals(PLAYER_NUMBER, output[i].length);
            for (int j = 0; j < PLAYER_NUMBER; j++) {
                assertEquals(min(FIGURE_NUMBER, inputNumberOfCardsPerPlayer[i]), output[i][j].size());
                for (int k = 0; k < min(FIGURE_NUMBER, inputNumberOfCardsPerPlayer[i]); k++) {
                    assertNotNull(output[i][j].get(k));
                }
            }
        }
    }

}

package gameLogic.game;

import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.card.Deck;
import org.junit.Test;

import java.util.List;

import static gameLogic.card.Color.*;
import static org.junit.Assert.assertEquals;

public class MoveValidatorTest {

    @Test
    public void is_move_valid_test() {
        //given
        Deck deck = Deck.getDeck();
        List<Integer> inputPlayerCardsIds = List.of(40, 15, 42);
        List<Card> inputPlayerCards = inputPlayerCardsIds.stream().map(deck::getCard).toList();
        Color[] inputColorsOfFirstCardPlayed = new Color[]{SPADE, HEART, SPADE, CLUB};
        int[] inputCardIds = {40, 15, 43, 30};
        Card[] inputCards = new Card[inputCardIds.length];
        for(int i = 0; i < inputCardIds.length; i++) {
            inputCards[i] = deck.getCard(inputCardIds[i]);
        }
        MoveValidator moveValidator = new MoveValidator();
        boolean[] outputCheck = {true, true, false, false};
        boolean[] output = new boolean[inputCards.length];
        //when
        for(int i = 0; i < inputCardIds.length; i++) {
            output[i] = moveValidator.isMoveValid(inputPlayerCards, inputColorsOfFirstCardPlayed[i], inputCards[i]);
        }
        //then
        for(int i = 0; i < inputCardIds.length; i++) {
            assertEquals(outputCheck[i], output[i]);
        }
    }

}

package gameLogic;

import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.card.Deck;
import org.junit.Test;

import java.util.List;

import static gameLogic.GameConstants.PLAYER_NUMBER;
import static gameLogic.Player.N;
import static gameLogic.card.Color.DIAMOND;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void initialize_game_test() {
        //given
        Game inputGame = new Game();
        Deck deck = Deck.getDeck();
        List<Card>[] inputCards = deck.dealCards(7);
        Color inputAtu = DIAMOND;
        //when
        inputGame.initializeGame(inputCards, inputAtu);
        //then
        assertSame(Deck.getDeck(), inputGame.getDeck());
        assertSame(inputCards, inputGame.getCards());
        assertNotNull(inputGame.getPoints());
        assertEquals(PLAYER_NUMBER / 2, inputGame.getPoints().length);
        for(int i = 0; i < PLAYER_NUMBER / 2; i++) {
            assertEquals(0, inputGame.getPoints()[i]);
        }
        assertNotNull(inputGame.getAtu());
        assertSame(inputAtu, inputGame.getAtu());
        assertNotNull(inputGame.getCurrentPlayer());
        assertEquals(N, inputGame.getCurrentPlayer());
        assertNotNull(inputGame.getStartingPlayer());
        assertEquals(N, inputGame.getStartingPlayer());
        assertNotNull(inputGame.getPlayedCards());
        assertEquals(0, inputGame.getPlayedCards().size());
    }

}

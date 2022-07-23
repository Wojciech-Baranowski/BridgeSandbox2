package gameLogic.game;

import gameLogic.Player;
import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.card.Deck;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static gameLogic.Player.*;
import static gameLogic.card.Color.DIAMOND;
import static gameLogic.card.Color.HEART;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void initialize_game_test() {
        //given
        Game inputGame = new Game();
        Deck deck = Deck.getDeck();
        int numberOfCardsPerPlayer = 7;
        List<Card>[] inputCards = deck.dealCards(numberOfCardsPerPlayer);
        Color inputAtu = DIAMOND;
        //when
        inputGame.initializeGame(inputAtu, inputCards);
        //then
        assertSame(Deck.getDeck(), inputGame.getDeck());
        assertSame(inputCards, inputGame.getCards());
        assertNotNull(inputGame.getPoints());
        assertEquals(PLAYER_NUMBER / 2, inputGame.getPoints().length);
        for (int i = 0; i < PLAYER_NUMBER / 2; i++) {
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

    @Test
    public void play_card_test() {
        //given
        Game inputGame = new Game();
        Deck deck = Deck.getDeck();
        Color inputAtu = HEART;
        inputGame.initializeGame(inputAtu, 1);
        List<Card>[] inputCards = new List[PLAYER_NUMBER];
        inputCards[0] = Stream.of(5, 15, 38).map(deck::getCard).toList();
        inputCards[1] = Stream.of(14, 26, 35).map(deck::getCard).toList();
        inputCards[2] = Stream.of(10, 19, 44).map(deck::getCard).toList();
        inputCards[3] = Stream.of(27, 41, 50).map(deck::getCard).toList();
        inputGame.setCards(inputCards);
        int size = 15;
        List<Card> inputCardList = Stream.of(15, 14, 19, 27, 50, 15, 16, 38, 26, 10, 44, 5, 35, 10, 41).map(deck::getCard).toList();
        Card[] inputCard = new Card[size];
        for(int i = 0; i < size; i++) {
            inputCard[i] = inputCardList.get(i);
        }
        Game[] outputCheckGames = new Game[size];
        List<Card>[][] outputCheckCards = new List[size][];
        outputCheckCards[0] = new List[PLAYER_NUMBER];
        outputCheckCards[0][0] = Stream.of(5, 38).map(deck::getCard).toList();
        outputCheckCards[0][1] = Stream.of(14, 26, 35).map(deck::getCard).toList();
        outputCheckCards[0][2] = Stream.of(10, 19, 44).map(deck::getCard).toList();
        outputCheckCards[0][3] = Stream.of(27, 41, 50).map(deck::getCard).toList();
        outputCheckCards[1] = new List[PLAYER_NUMBER];
        outputCheckCards[1][0] = Stream.of(5, 38).map(deck::getCard).toList();
        outputCheckCards[1][1] = Stream.of(26, 35).map(deck::getCard).toList();
        outputCheckCards[1][2] = Stream.of(10, 19, 44).map(deck::getCard).toList();
        outputCheckCards[1][3] = Stream.of(27, 41, 50).map(deck::getCard).toList();
        outputCheckCards[2] = new List[PLAYER_NUMBER];
        outputCheckCards[2][0] = Stream.of(5, 38).map(deck::getCard).toList();
        outputCheckCards[2][1] = Stream.of(26, 35).map(deck::getCard).toList();
        outputCheckCards[2][2] = Stream.of(10, 44).map(deck::getCard).toList();
        outputCheckCards[2][3] = Stream.of(27, 41, 50).map(deck::getCard).toList();
        outputCheckCards[3] = new List[PLAYER_NUMBER];
        outputCheckCards[3][0] = Stream.of(5, 38).map(deck::getCard).toList();
        outputCheckCards[3][1] = Stream.of(26, 35).map(deck::getCard).toList();
        outputCheckCards[3][2] = Stream.of(10, 44).map(deck::getCard).toList();
        outputCheckCards[3][3] = Stream.of(41, 50).map(deck::getCard).toList();
        outputCheckCards[4] = new List[PLAYER_NUMBER];
        outputCheckCards[4][0] = Stream.of(5, 38).map(deck::getCard).toList();
        outputCheckCards[4][1] = Stream.of(26, 35).map(deck::getCard).toList();
        outputCheckCards[4][2] = Stream.of(10, 44).map(deck::getCard).toList();
        outputCheckCards[4][3] = Stream.of(41).map(deck::getCard).toList();
        outputCheckCards[5] = new List[PLAYER_NUMBER];
        outputCheckCards[5][0] = Stream.of(5, 38).map(deck::getCard).toList();
        outputCheckCards[5][1] = Stream.of(26, 35).map(deck::getCard).toList();
        outputCheckCards[5][2] = Stream.of(10, 44).map(deck::getCard).toList();
        outputCheckCards[5][3] = Stream.of(41).map(deck::getCard).toList();
        outputCheckCards[6] = new List[PLAYER_NUMBER];
        outputCheckCards[6][0] = Stream.of(5, 38).map(deck::getCard).toList();
        outputCheckCards[6][1] = Stream.of(26, 35).map(deck::getCard).toList();
        outputCheckCards[6][2] = Stream.of(10, 44).map(deck::getCard).toList();
        outputCheckCards[6][3] = Stream.of(41).map(deck::getCard).toList();
        outputCheckCards[7] = new List[PLAYER_NUMBER];
        outputCheckCards[7][0] = Stream.of(5).map(deck::getCard).toList();
        outputCheckCards[7][1] = Stream.of(26, 35).map(deck::getCard).toList();
        outputCheckCards[7][2] = Stream.of(10, 44).map(deck::getCard).toList();
        outputCheckCards[7][3] = Stream.of(41).map(deck::getCard).toList();
        outputCheckCards[8] = new List[PLAYER_NUMBER];
        outputCheckCards[8][0] = Stream.of(5).map(deck::getCard).toList();
        outputCheckCards[8][1] = Stream.of(35).map(deck::getCard).toList();
        outputCheckCards[8][2] = Stream.of(10, 44).map(deck::getCard).toList();
        outputCheckCards[8][3] = Stream.of(41).map(deck::getCard).toList();
        outputCheckCards[9] = new List[PLAYER_NUMBER];
        outputCheckCards[9][0] = Stream.of(5).map(deck::getCard).toList();
        outputCheckCards[9][1] = Stream.of(35).map(deck::getCard).toList();
        outputCheckCards[9][2] = Stream.of(10, 44).map(deck::getCard).toList();
        outputCheckCards[9][3] = Stream.of(41).map(deck::getCard).toList();
        outputCheckCards[10] = new List[PLAYER_NUMBER];
        outputCheckCards[10][0] = Stream.of(5).map(deck::getCard).toList();
        outputCheckCards[10][1] = Stream.of(35).map(deck::getCard).toList();
        outputCheckCards[10][2] = Stream.of(10).map(deck::getCard).toList();
        outputCheckCards[10][3] = Stream.of(41).map(deck::getCard).toList();
        outputCheckCards[11] = new List[PLAYER_NUMBER];
        outputCheckCards[11][0] = new LinkedList<>();
        outputCheckCards[11][1] = Stream.of(35).map(deck::getCard).toList();
        outputCheckCards[11][2] = Stream.of(10).map(deck::getCard).toList();
        outputCheckCards[11][3] = Stream.of(41).map(deck::getCard).toList();
        outputCheckCards[12] = new List[PLAYER_NUMBER];
        outputCheckCards[12][0] = new LinkedList<>();
        outputCheckCards[12][1] = new LinkedList<>();
        outputCheckCards[12][2] = Stream.of(10).map(deck::getCard).toList();
        outputCheckCards[12][3] = Stream.of(41).map(deck::getCard).toList();
        outputCheckCards[13] = new List[PLAYER_NUMBER];
        outputCheckCards[13][0] = new LinkedList<>();
        outputCheckCards[13][1] = new LinkedList<>();
        outputCheckCards[13][2] = new LinkedList<>();
        outputCheckCards[13][3] = Stream.of(41).map(deck::getCard).toList();
        outputCheckCards[14] = new List[PLAYER_NUMBER];
        outputCheckCards[14][0] = new LinkedList<>();
        outputCheckCards[14][1] = new LinkedList<>();
        outputCheckCards[14][2] = new LinkedList<>();
        outputCheckCards[14][3] = new LinkedList<>();
        int[][] outputCheckPoints = new int[size][];
        outputCheckPoints[0] = new int[]{0, 0};
        outputCheckPoints[1] = new int[]{0, 0};
        outputCheckPoints[2] = new int[]{0, 0};
        outputCheckPoints[3] = new int[]{0, 1};
        outputCheckPoints[4] = new int[]{0, 1};
        outputCheckPoints[5] = new int[]{0, 1};
        outputCheckPoints[6] = new int[]{0, 1};
        outputCheckPoints[7] = new int[]{0, 1};
        outputCheckPoints[8] = new int[]{0, 1};
        outputCheckPoints[9] = new int[]{0, 1};
        outputCheckPoints[10] = new int[]{1, 1};
        outputCheckPoints[11] = new int[]{1, 1};
        outputCheckPoints[12] = new int[]{1, 1};
        outputCheckPoints[13] = new int[]{1, 1};
        outputCheckPoints[14] = new int[]{1, 2};
        Player[] outputCheckCurrentPlayer = new Player[]{E, S, W, W, N, N, N, E, S, S, N, E, S, W, E};
        Player[] outputCheckStartingPlayer = new Player[]{N, N, N, W, W, W, W, W, W, W, N, N, N, N, E};
        List<Card>[] outputCheckPlayedCards = new List[size];
        outputCheckPlayedCards[0] = Stream.of(15).map(deck::getCard).toList();
        outputCheckPlayedCards[1] = Stream.of(15, 14).map(deck::getCard).toList();
        outputCheckPlayedCards[2] = Stream.of(15, 14, 19).map(deck::getCard).toList();
        outputCheckPlayedCards[3] = new LinkedList<>();
        outputCheckPlayedCards[4] = Stream.of(50).map(deck::getCard).toList();
        outputCheckPlayedCards[5] = Stream.of(50).map(deck::getCard).toList();
        outputCheckPlayedCards[6] = Stream.of(50).map(deck::getCard).toList();
        outputCheckPlayedCards[7] = Stream.of(50, 38).map(deck::getCard).toList();
        outputCheckPlayedCards[8] = Stream.of(50, 38, 26).map(deck::getCard).toList();
        outputCheckPlayedCards[9] = Stream.of(50, 38, 26).map(deck::getCard).toList();
        outputCheckPlayedCards[10] = new LinkedList<>();
        outputCheckPlayedCards[11] = Stream.of(5).map(deck::getCard).toList();
        outputCheckPlayedCards[12] = Stream.of(5, 35).map(deck::getCard).toList();
        outputCheckPlayedCards[13] = Stream.of(5, 35, 10).map(deck::getCard).toList();
        outputCheckPlayedCards[14] = new LinkedList<>();

        for(int i = 0; i < size; i++) {
            //when
            inputGame.playCard(inputCard[i]);
            //then
            for(int j = 0; j < PLAYER_NUMBER; j++) {
                for(int k = 0; k < inputGame.getCards()[j].size(); k++) {
                    assertSame(outputCheckCards[i][j].get(k), inputGame.getCards()[j].get(k));
                }
            }
            for(int j = 0; j < PLAYER_NUMBER / 2; j++) {
                assertEquals(outputCheckPoints[i][j], inputGame.getPoints()[j]);
            }
            assertEquals(outputCheckCurrentPlayer[i], inputGame.getCurrentPlayer());
            assertEquals(outputCheckStartingPlayer[i], inputGame.getStartingPlayer());
            assertEquals(outputCheckPlayedCards[i].size(), inputGame.getPlayedCards().size());
            for(int j = 0; j < outputCheckPlayedCards[i].size(); j++) {
                assertSame(outputCheckPlayedCards[i].get(j), inputGame.getPlayedCards().get(j));
            }
        }
    }

}

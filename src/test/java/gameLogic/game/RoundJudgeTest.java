package gameLogic.game;

import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.card.Deck;
import gameLogic.player.Player;
import org.junit.Test;

import java.util.List;

import static gameLogic.card.Color.*;
import static gameLogic.player.Player.*;
import static org.junit.Assert.assertEquals;

public class RoundJudgeTest {

    @Test
    public void choose_winning_player_test() {
        //given
        int size = 8;
        Deck deck = Deck.getDeck();
        List<Integer>[] inputPlayedCardsIds = new List[size];
        inputPlayedCardsIds[0] = List.of(0, 9, 3, 6);
        inputPlayedCardsIds[1] = List.of(1, 38, 29, 4);
        inputPlayedCardsIds[2] = List.of(1, 10, 5, 8);
        inputPlayedCardsIds[3] = List.of(2, 29, 51, 3);
        inputPlayedCardsIds[4] = List.of(1, 9, 26, 51);
        inputPlayedCardsIds[5] = List.of(1, 29, 6, 30);
        inputPlayedCardsIds[6] = List.of(4, 25, 37, 1);
        inputPlayedCardsIds[7] = List.of(14, 22, 19, 13);
        List<Card>[] inputPlayedCards = new List[size];
        for (int i = 0; i < size; i++) {
            inputPlayedCards[i] = inputPlayedCardsIds[i].stream().map(deck::getCard).toList();
        }
        Player[] inputStartingPlayers = new Player[]{N, N, N, N, N, N, N, E};
        Color[] inputAtu = new Color[]{HEART, SPADE, CLUB, CLUB, HEART, HEART, CLUB, DIAMOND};
        RoundJudge roundJudge = new RoundJudge();
        Player[] outputCheck = new Player[]{E, W, E, W, S, W, N, S};
        Player[] output = new Player[size];
        //when
        for (int i = 0; i < size; i++) {
            output[i] = roundJudge.chooseWinningPlayer(inputPlayedCards[i], inputStartingPlayers[i], inputAtu[i]);
        }
        //then
        for (int i = 0; i < size; i++) {
            assertEquals(outputCheck[i], output[i]);
        }
    }

}

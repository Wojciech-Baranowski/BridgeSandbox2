package gameLogic.card;

import lombok.Getter;

@Getter
public class Card {

    private Figure figure;
    private Color color;

    Card(int id) {

    }

    Card(Figure figure, Color color) {

    }

    public int getId() {
        return 0;
    }

}

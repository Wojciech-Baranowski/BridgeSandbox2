package gameLogic.card;

import lombok.Getter;

@Getter
public class Card {

    private final Figure figure;
    private final Color color;

    Card(int id) {
        this.figure = Figure.values()[id % 13];
        this.color = Color.values()[id / 13];
    }

    Card(Figure figure, Color color) {
        this.figure = figure;
        this.color = color;
    }

    public int getId() {
        return figure.ordinal() + color.ordinal() * 13;
    }

}

package gameLogic.card;

import lombok.Getter;

import static gameLogic.game.GameConstants.FIGURE_NUMBER;

@Getter
public class Card {

    private final Figure figure;
    private final Color color;

    public Card(Card card) {
        this.figure = card.getFigure();
        this.color = card.getColor();
    }

    public Card(int id) {
        this.figure = Figure.values()[id % FIGURE_NUMBER];
        this.color = Color.values()[id / FIGURE_NUMBER];
    }

    public Card(Figure figure, Color color) {
        this.figure = figure;
        this.color = color;
    }

    public int getId() {
        return figure.ordinal() + color.ordinal() * FIGURE_NUMBER;
    }

}

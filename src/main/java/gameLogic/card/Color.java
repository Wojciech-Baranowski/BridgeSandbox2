package gameLogic.card;

import lombok.Getter;

public enum Color {

    CLUB('\u2667'),
    DIAMOND('\u2666'),
    HEART('\u2665'),
    SPADE('\u2664'),
    NOATU('\u2668');

    @Getter
    private final char symbol;

    Color(char symbol) {
        this.symbol = symbol;
    }

}

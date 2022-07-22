package gameLogic.card;

import lombok.Getter;

public enum Color {

    NOATU('\u2668'),
    CLUB('\u2667'),
    DIAMOND('\u2666'),
    HEART('\u2665'),
    SPADE('\u2664');

    @Getter
    private final char ascii;

    Color(char ascii) {
        this.ascii = ascii;
    }

}

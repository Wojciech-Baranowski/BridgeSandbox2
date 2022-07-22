package gameLogic;

import lombok.Getter;

public enum Player {

    N('N'),
    E('E'),
    S('S'),
    W('W');

    @Getter
    private final char symbol;

    Player(char symbol) {
        this.symbol = symbol;
    }

}

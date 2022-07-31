package gameLogic.player;

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

    public Player nextPlayer() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }

    public String getSymbolString() {
        return String.valueOf(symbol);
    }

}

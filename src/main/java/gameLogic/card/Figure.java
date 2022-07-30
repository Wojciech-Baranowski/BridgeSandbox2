package gameLogic.card;

import lombok.Getter;

public enum Figure {

    _2('2'),
    _3('3'),
    _4('4'),
    _5('5'),
    _6('6'),
    _7('7'),
    _8('8'),
    _9('9'),
    _10('\u2669'),
    J('J'),
    Q('Q'),
    K('K'),
    A('A');

    @Getter
    private final char symbol;

    Figure(char symbol) {
        this.symbol = symbol;
    }

    public String getSymbolString() {
        return String.valueOf(symbol);
    }

}

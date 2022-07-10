package assets.font;

import common.Rasterable;

public interface Font {

    Rasterable getSymbolRasterable(char symbol);

    static String getExtendedAlphabet() {
        return "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u2664\u2665\u2666\u2667\u2468\u2469";
    }

}

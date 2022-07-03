package assets.font;

import common.Rasterable;

import java.util.HashMap;
import java.util.Map;

public class RasterFont implements Font{

    private Map<Character, Rasterable> symbols;

    RasterFont(char[] symbols, Rasterable[] rasterables) {
        this.symbols = new HashMap<>();
        for(int i = 0; i < symbols.length; i++){
            this.symbols.put(symbols[i], rasterables[i]);
        }
    }

    @Override
    public Rasterable getSymbolRasterable(char symbol) {
        return symbols.get(symbol);
    }
}
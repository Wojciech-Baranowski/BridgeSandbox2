package display.text;

import assets.color.Color;
import assets.font.Font;
import common.Rasterable;
import display.Drawable;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

import static assets.Assets.getTransparentColorValue;
import static java.lang.Math.max;

public class Text implements Drawable {

    private final Font font;
    private final Color color;
    private String text;
    @Getter private int[] p;
    @Getter @Setter private int x;
    @Getter @Setter private int y;
    @Getter @Setter private int z;
    @Getter private int w;
    @Getter private int h;


    Text(String text, int x, int y, int z, Font font, Color color) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.z = z;
        this.font = font;
        this.color = color;
        this.w = recalculateWidth();
        this.h = recalculateHeight();
        this.p = recalculatePixels();
    }

    public void setText(String text) {
        this.text = text;
        this.w = recalculateWidth();
        this.h = recalculateHeight();
        this.p = recalculatePixels();
    }

    private int recalculateWidth() {
        int maxW = 0;
        int currentW = 0;
        int spacing = font.getSymbolRasterable(text.charAt(0)).getH() / 8;
        for(char symbol : text.toCharArray()){
            if(symbol == '\n'){
                maxW = max(maxW, currentW);
                currentW = 0;
                continue;
            }
            if(symbol == ' '){
                currentW += spacing * 2 + 2;
                continue;
            }
            currentW += font.getSymbolRasterable(symbol).getW() + spacing;
        }
       return max(maxW, currentW);
    }

    private int recalculateHeight() {
        int numberOfLines = 1;
        int symbolHeight = font.getSymbolRasterable('0').getH();
        int spacing = symbolHeight / 8;
        for(char symbol : text.toCharArray()){
            if(symbol == '\n'){
                numberOfLines++;
            }
        }
        return (symbolHeight + spacing) * numberOfLines;
    }

    private int[] recalculatePixels() {
        int[] pixels = new int[w * h];
        Arrays.fill(pixels, getTransparentColorValue());
        int currentX = 0;
        int currentY = 0;
        int symbolsHeight = font.getSymbolRasterable('0').getH();
        int spacing = symbolsHeight / 8;
        for(char symbol : text.toCharArray()){
            if(symbol == ' '){
                currentX += spacing * 2 + 2;
                continue;
            }
            if(symbol == '\n'){
                currentX = 0;
                currentY += symbolsHeight + spacing;
                continue;
            }
            Rasterable symbolRasterable = font.getSymbolRasterable(symbol);
            mergeSymbolIntoPixelArray(pixels, symbolRasterable, currentX, currentY);
            currentX += symbolRasterable.getW() + spacing;
        }
        return pixels;
    }

    private void mergeSymbolIntoPixelArray(int[] pixels, Rasterable symbol, int currentX, int currentY) {

        int[] symbolPixels = symbol.getP();
        for(int x = 0; x < symbol.getW(); x++){
            for(int y = 0; y < symbol.getH(); y++){
                int pixel = symbolPixels[x + y * symbol.getW()];
                pixels[currentX + x + (currentY + y) * w] = (pixel != getTransparentColorValue() ? color.getValue() : pixel);
            }
        }
    }

}

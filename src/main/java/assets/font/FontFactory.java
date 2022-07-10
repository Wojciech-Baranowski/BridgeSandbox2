package assets.font;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static assets.color.Color.getTransparentColorValue;

public class FontFactory {

    public Font makeRasterFont(String path, String symbols) {
        RasterFontSymbol[] fontRasterables = getFontRasterables(path, symbols.length());
        return new RasterFont(symbols, fontRasterables);
    }

    private RasterFontSymbol[] getFontRasterables(String path, int symbolsNumber) {
        RasterFontSymbol[] rasterFontSymbols = new RasterFontSymbol[symbolsNumber];
        int[] fontPixels = getFontPixels(path);
        int w = getFontWidth(path);
        int h = fontPixels.length / w;
        int[] symbolLengths = getSymbolsLengths(fontPixels, w, h, symbolsNumber);
        int currentX = 0;
        for(int i = 0; i < symbolsNumber; i++){
            int[] symbolPixels = getSymbolPixels(fontPixels, symbolLengths[i], currentX, w, h);
            rasterFontSymbols[i] = new RasterFontSymbol(symbolPixels, symbolLengths[i], h);
            currentX += symbolLengths[i];
        }
        return rasterFontSymbols;
    }

    private int[] getSymbolPixels(int[] fontPixels, int symbolLength, int currentX, int w, int h) {
        int[] symbolPixels = new int[symbolLength * h];
        for(int x = 0; x < symbolLength; x++){
            for(int y = 0; y < h; y++){
                symbolPixels[x + y * symbolLength] = fontPixels[currentX + x + y * w];
            }
        }
        return symbolPixels;
    }

    private int[] getFontPixels(String path) {
        try {
            InputStream imageStream = FontFactory.class.getResourceAsStream(path);
            BufferedImage image = ImageIO.read(Objects.requireNonNull(imageStream));
            int w = image.getWidth();
            int h = image.getHeight();
            return image.getRGB(0, 0, w, h, null, 0, w);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int[] getSymbolsLengths(int[] fontPixels, int w, int h, int symbolsNumber) {
        int[] symbolLengths = new int[symbolsNumber];
        int lastX = 0;
        int currentSymbolNumber = 0;
        boolean currentColumnEmpty;
        boolean lastColumnEmpty = true;
        for(int x = 1; x < w && currentSymbolNumber < symbolsNumber; x++){
            currentColumnEmpty = isColumnTransparent(fontPixels, x, w, h);
            if(currentColumnEmpty && !lastColumnEmpty){
                symbolLengths[currentSymbolNumber++] = x - lastX + 1;
            }
            else if(!currentColumnEmpty && lastColumnEmpty){
                lastX = x - 1;
            }
            lastColumnEmpty = currentColumnEmpty;
        }
        return symbolLengths;
    }

    private boolean isColumnTransparent(int[] fontPixels, int x, int w, int h) {
        for(int y = 0; y < h; y++){
            if(fontPixels[x + y * w] != getTransparentColorValue()){
                return false;
            }
        }
        return true;
    }

    private int getFontWidth(String path) {
        try {
            InputStream imageStream = FontFactory.class.getResourceAsStream(path);
            BufferedImage image = ImageIO.read(Objects.requireNonNull(imageStream));
            return image.getWidth();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

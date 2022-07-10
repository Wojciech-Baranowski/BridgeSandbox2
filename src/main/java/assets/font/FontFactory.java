package assets.font;

import assets.color.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class FontFactory {

    public Font makeRasterFont(String path, char[] symbols) {
        RasterFontSymbol[] fontRasterables = getFontRasterables(path, symbols.length);
        return new RasterFont(symbols, fontRasterables);
    }

    private RasterFontSymbol[] getFontRasterables(String path, int symbolsNumber) {
        int[] fontPixels = getFontPixels(path);
        int w = getFontWidth(path);
        int h = fontPixels.length / w;
        int[] symbolLengths = getSymbolsLengths(fontPixels, w, h, symbolsNumber);
        RasterFontSymbol[] rasterFontSymbols = new RasterFontSymbol[symbolsNumber];
        int currentX = 0;
        for(int i = 0; i < symbolsNumber; i++){
            int[] symbolPixels = new int[symbolLengths[i] * h];
            for(int x = 0; x < symbolLengths[i]; x++){
                for(int y = 0; y < h; y++){
                    symbolPixels[x + y * symbolLengths[i]] = fontPixels[currentX + x + y * w];
                }
            }
            currentX += symbolLengths[i];
            rasterFontSymbols[i] = new RasterFontSymbol(symbolPixels, symbolLengths[i], h);
        }
        return rasterFontSymbols;
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
        for(int x = 1; x < w; x++){
            currentColumnEmpty = true;
            for(int y = 0; y < h; y++){
                if(fontPixels[x + y * w] != Color.getTransparentColor()){
                    currentColumnEmpty = false;
                    break;
                }
            }
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

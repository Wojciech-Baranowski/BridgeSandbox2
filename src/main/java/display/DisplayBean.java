package display;

import assets.color.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collection;
import java.util.EventListener;
import java.util.LinkedList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class DisplayBean implements Display {

    private static DisplayBean display;
    private final Window window;
    @Getter
    private final DrawableFactory drawableFactory;
    private final int[] pixels;
    @Setter
    private Collection<Drawable> objectsToDraw;
    private boolean isTransparent;

    private DisplayBean() {
        window = new Window();
        drawableFactory = new DrawableFactory();
        pixels = new int[Window.w * Window.h];
        Arrays.fill(pixels, 0);
        objectsToDraw = new LinkedList<>();
    }

    public static Display getDisplay() {
        if (display == null) {
            display = new DisplayBean();
        }
        return display;
    }

    @Override
    public void addWindowListener(EventListener listener) {
        window.addListener(listener);
    }

    @Override
    public void draw() {
        for (Drawable drawable : objectsToDraw) {
            int startX = max(drawable.getX(), 0);
            int startY = max(drawable.getY(), 0);
            int pWidth = drawable.getW();
            int width = min(drawable.getX() + drawable.getW(), Window.w - 1) - startX;
            int height = min(drawable.getY() + drawable.getH(), Window.h - 1) - startY;
            boolean isTransparent = Color.isTransparent(drawable.getP()[0]);
            if (isTransparent) {
                mergeTransparent(drawable.getP(), startX, startY, width, height, pWidth);
            } else {
                mergeNonTransparent(drawable.getP(), startX, startY, width, height, pWidth);
            }
        }
        window.draw(pixels);
    }

    private void mergeNonTransparent(int[] p, int startX, int startY, int width, int height, int pWidth) {
        int transparentColorValue = Color.getTransparentColorValue();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixelId = x + y * pWidth;
                if (p[pixelId] != transparentColorValue) {
                    pixels[startX + x + (startY + y) * Window.w] = p[pixelId];
                }
            }
        }
    }

    private void mergeTransparent(int[] p, int startX, int startY, int width, int height, int pWidth) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixelId = startX + x + (startY + y) * Window.w;
                int newColor = Color.blend(pixels[pixelId], p[x + y * pWidth]);
                pixels[pixelId] = newColor;
            }
        }
    }
}

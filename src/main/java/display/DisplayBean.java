package display;

import assets.color.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collection;
import java.util.EventListener;
import java.util.LinkedList;

import static assets.color.Color.getTransparentColorValue;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class DisplayBean implements Display {

    private static DisplayBean display;
    private final Window window;
    @Getter
    private final DrawableFactory drawableFactory;
    private final int[] pixels;
    @Setter
    private Collection<Visual> objectsToDraw;

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
        if (objectsToDraw == null) {
            return;
        }
        for (Visual visual : objectsToDraw) {
            Drawable drawable = visual.getDrawable();
            int startX = max(drawable.getX(), 0);
            int startY = max(drawable.getY(), 0);
            int pWidth = drawable.getW();
            int width = min(drawable.getX() + drawable.getW(), Window.w - 1) - startX;
            int height = min(drawable.getY() + drawable.getH(), Window.h - 1) - startY;
            merge(drawable.getP(), startX, startY, width, height, pWidth);
        }
        window.draw(pixels);
    }

    private void merge(int[] p, int startX, int startY, int width, int height, int pWidth) {
        int transparentColorValue = getTransparentColorValue();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixelId = startX + x + (startY + y) * Window.w;
                if (p[x + y * pWidth] != transparentColorValue) {
                    int newColor = (Color.isTransparent(p[x + y * pWidth])
                            ? Color.blend(pixels[pixelId], p[x + y * pWidth])
                            : p[x + y * pWidth]);
                    pixels[pixelId] = newColor;
                }
            }
        }
    }
}

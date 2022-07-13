package display.image;

import assets.font.FontFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ImageFactory {

    public Image makeImage(String path, int x, int y) {
        BufferedImage image = getBufferedImage(path);
        int w = image.getWidth();
        int h = image.getHeight();
        return new Image(image, x, y, w, h);
    }

    private BufferedImage getBufferedImage(String path) {
        try {
            InputStream imageStream = FontFactory.class.getResourceAsStream(path);
            return ImageIO.read(Objects.requireNonNull(imageStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

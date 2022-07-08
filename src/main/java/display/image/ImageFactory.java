package display.image;

import assets.Assets;
import assets.AssetsBean;

public class ImageFactory {

    private final Assets assets;

    public ImageFactory() {
        this.assets = AssetsBean.getAssets();
    }

    public Image makeImage(String path, int x, int y, int z) {
        return null;
    }

}

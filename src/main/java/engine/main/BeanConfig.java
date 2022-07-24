package engine.main;

import engine.assets.AssetsBean;
import engine.display.DisplayBean;
import engine.input.InputBean;
import engine.scene.SceneBean;

public class BeanConfig {

    BeanConfig() {
    }

    void buildBeans() {
        AssetsBean.getAssets();
        DisplayBean.getDisplay();
        InputBean.getInput();
        SceneBean.getScene();
    }

}

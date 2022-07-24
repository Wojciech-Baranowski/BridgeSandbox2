package engine.main;

import engine.assets.AssetsBean;
import engine.display.DisplayBean;
import engine.input.InputBean;

public class BeanConfig {

    BeanConfig() {
    }

    void buildBeans() {
        AssetsBean.getAssets();
        DisplayBean.getDisplay();
        InputBean.getInput();
    }

}

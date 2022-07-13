package main;

import assets.Assets;
import assets.AssetsBean;
import display.Display;
import display.DisplayBean;
import input.Input;
import input.InputBean;
import org.junit.Test;
import scene.Scene;
import scene.SceneBean;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class BeanConfigTest {

    @Test
    public void build_beans_test() {
        //given
        Assets assets;
        Display display;
        Input input;
        Scene scene;
        BeanConfig beanConfig = new BeanConfig();
        //when
        beanConfig.buildBeans();
        scene = SceneBean.getScene();
        display = DisplayBean.getDisplay();
        assets = AssetsBean.getAssets();
        input = InputBean.getInput();
        //then
        assertNotNull(assets);
        assertNotNull(display);
        assertNotNull(input);
        assertNotNull(scene);
        assertSame(assets, AssetsBean.getAssets());
        assertSame(display, DisplayBean.getDisplay());
        assertSame(input, InputBean.getInput());
        assertSame(scene, SceneBean.getScene());
    }

}

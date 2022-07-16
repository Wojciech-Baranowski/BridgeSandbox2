package button.checkbox;

import assets.Assets;
import assets.AssetsBean;
import button.SimpleButton;
import display.Display;
import display.DisplayBean;
import display.DrawableFactory;
import display.rectangle.Rectangle;
import input.Input;
import input.InputBean;
import input.inputCombination.InputCombinationFactory;
import scene.Scene;
import scene.SceneBean;

import java.util.List;

public class CheckboxBundleTest {

    public static void main(String[] args) {
        new CheckboxBundleTest().manual_get_bundle_state_test();
    }

    public void manual_get_bundle_state_test() {
        //given
        Assets assets = AssetsBean.getAssets();
        assets.addColor("n1", 0xFFFF0000);
        assets.addColor("n2", 0xFF0000FF);
        Display display = DisplayBean.getDisplay();
        Input input = InputBean.getInput();
        InputCombinationFactory inputCombinationFactory = input.getInputCombinationFactory();
        DrawableFactory drawableFactory = display.getDrawableFactory();
        Rectangle inputButtonRectangle = drawableFactory.makeRectangle(210, 10, 100, 100, "n1");
        Rectangle inputOffRectangle1 = drawableFactory.makeRectangle(10, 10, 100, 100, "n1");
        Rectangle inputOnRectangle1 = drawableFactory.makeRectangle(10, 10, 100, 100, "n2");
        Rectangle inputOffRectangle2 = drawableFactory.makeRectangle(10, 210, 100, 100, "n1");
        Rectangle inputOnRectangle2 = drawableFactory.makeRectangle(10, 210, 100, 100, "n2");
        Rectangle inputOffRectangle3 = drawableFactory.makeRectangle(10, 410, 100, 100, "n1");
        Rectangle inputOnRectangle3 = drawableFactory.makeRectangle(10, 410, 100, 100, "n2");
        Checkbox checkbox1 = new Checkbox(inputOffRectangle1, inputOnRectangle1, inputCombinationFactory.makeLmbCombination());
        Checkbox checkbox2 = new Checkbox(inputOffRectangle2, inputOnRectangle2, inputCombinationFactory.makeLmbCombination());
        Checkbox checkbox3 = new Checkbox(inputOffRectangle3, inputOnRectangle3, inputCombinationFactory.makeLmbCombination());
        Scene scene = SceneBean.getScene();
        scene.addCollection("c1");
        scene.switchCollection("c1");
        scene.addOnHighest(checkbox1);
        scene.addOnHighest(checkbox2);
        scene.addOnHighest(checkbox3);
        CheckboxBundle checkboxBundle = new CheckboxBundle(List.of(checkbox1, checkbox2));
        checkboxBundle.addCheckbox(checkbox3);
        checkboxBundle.removeCheckbox(checkbox1);
        checkboxBundle.removeCheckbox(checkbox1);
        checkboxBundle.addCheckbox(checkbox1);
        checkboxBundle.addCheckbox(checkbox1);
        SimpleButton button = new SimpleButton(inputButtonRectangle, inputCombinationFactory.makeLmbCombination(), () -> {
            for(Boolean state : checkboxBundle.getBundleState()) {
                System.out.println(state ? "T" : "F" + " ");
            }
        });
        scene.addOnHighest(button);
        //when
        scene.update();

    }

}

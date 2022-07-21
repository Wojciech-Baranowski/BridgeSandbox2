package engine.button.checkbox;

import engine.assets.Assets;
import engine.assets.AssetsBean;
import engine.button.SimpleButton;
import engine.display.Display;
import engine.display.DisplayBean;
import engine.display.DrawableFactory;
import engine.display.rectangle.Rectangle;
import engine.input.Input;
import engine.input.InputBean;
import engine.input.inputCombination.InputCombinationFactory;
import engine.scene.Scene;
import engine.scene.SceneBean;

import java.util.LinkedList;
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
        List<Checkbox> inputCheckboxList = new LinkedList<>();
        inputCheckboxList.add(checkbox1);
        inputCheckboxList.add(checkbox2);
        CheckboxBundle checkboxBundle = new CheckboxBundle(inputCheckboxList);
        checkboxBundle.addCheckbox(checkbox3);
        checkboxBundle.removeCheckbox(checkbox3);
        checkboxBundle.removeCheckbox(checkbox3);
        checkboxBundle.addCheckbox(checkbox3);
        checkboxBundle.addCheckbox(checkbox3);
        SimpleButton button = new SimpleButton(inputButtonRectangle, inputCombinationFactory.makeLmbCombination(), () -> {
            for(Boolean state : checkboxBundle.getBundleState()) {
                System.out.print((state ? "T" : "F") + " ");
            }
            System.out.println();
        });
        scene.addOnHighest(button);
        //when
        InputBean.getInput().initializeListeners();
        SceneBean.getScene().initializeListeners();
        scene.update();
        //then
        //whenever some combination of bundle's checkboxes in chosen, pressing right side button should
        //show current checkboxes states in console logs (T - is selected, F - is not selected)
    }

}

package input.inputCombination;

import input.KeyboardListener;
import input.MouseListener;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import static input.inputCombination.ActionType.DOWN;
import static java.lang.Math.min;

public class InputCombinationFactory {

    private final MouseListener mouseListener;
    private final KeyboardListener keyboardListener;

    public InputCombinationFactory(MouseListener mouseListener, KeyboardListener keyboardListener) {
        this.mouseListener = mouseListener;
        this.keyboardListener = keyboardListener;
    }

    public InputCombination makeComplexInputCombination(ActionType[] actionTypes, InputEvent[] inputEvents) {
        Set<InputElement> inputElements = new HashSet<>();
        for(int i = 0; i < min(actionTypes.length, inputEvents.length); i++) {
            InputElement inputElement = new InputElement(actionTypes[i], inputEvents[i]);
            inputElements.add(inputElement);
        }
        return new ComplexInputCombination(keyboardListener, mouseListener, inputElements);
    }

    public InputCombination makeSimpleInputCombination(ActionType actionType, InputEvent inputEvent) {
        InputElement inputElement = new InputElement(actionType, inputEvent);
        return new SimpleInputCombination(keyboardListener, mouseListener, inputElement);
    }

    public InputCombination makeLMBCombination() {
        int keyCode = MouseEvent.BUTTON1;
        Component dummyComponent = new Button();
        InputEvent inputEvent = new MouseEvent(
                dummyComponent, 0, 0, 0, 0, 0, 0, false, keyCode);
        InputElement inputElement = new InputElement(DOWN, inputEvent);
        return new SimpleInputCombination(keyboardListener, mouseListener, inputElement);
    }

}

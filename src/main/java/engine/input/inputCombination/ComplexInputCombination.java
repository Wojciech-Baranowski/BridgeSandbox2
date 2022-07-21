package engine.input.inputCombination;

import engine.input.KeyboardListener;
import engine.input.MouseListener;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Set;

public class ComplexInputCombination implements InputCombination {

    private final KeyboardListener keyboardListener;
    private final MouseListener mouseListener;
    private final Set<InputElement> elements;

    public ComplexInputCombination(
            KeyboardListener keyboardListener, MouseListener mouseListener, Set<InputElement> elements) {
        this.keyboardListener = keyboardListener;
        this.mouseListener = mouseListener;
        this.elements = elements;
    }

    @Override
    public boolean isActive() {
        boolean result = true;
        for (InputElement element : elements) {
            if (element.getInputEvent() instanceof MouseEvent) {
                result &= mouseListener.isActivated(element);
            }
            if (element.getInputEvent() instanceof KeyEvent) {
                result &= keyboardListener.isActivated(element);
            }
        }
        return result;
    }
}

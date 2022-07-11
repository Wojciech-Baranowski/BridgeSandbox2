package input.inputCombination;

import input.KeyboardListener;
import input.MouseListener;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SimpleInputCombination implements InputCombination{

    private final KeyboardListener keyboardListener;
    private final MouseListener mouseListener;
    private final InputElement element;

    public SimpleInputCombination(
            KeyboardListener keyboardListener, MouseListener mouseListener, InputElement element) {
        this.keyboardListener = keyboardListener;
        this.mouseListener = mouseListener;
        this.element = element;
    }

    @Override
    public boolean isActive() {
        if(element.getInputEvent() instanceof MouseEvent) {
            return mouseListener.isActivated(element);
        }
        if(element.getInputEvent() instanceof KeyEvent) {
            return keyboardListener.isActivated(element);
        }
        return false;
    }
}

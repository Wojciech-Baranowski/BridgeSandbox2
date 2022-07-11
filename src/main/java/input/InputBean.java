package input;

import common.Observer;
import input.inputCombination.ComplexInputCombination;
import input.inputCombination.InputCombination;
import input.inputCombination.InputCombinationFactory;
import input.inputCombination.InputElement;

import java.util.HashSet;
import java.util.Set;

public class InputBean implements Input{

    private static InputBean input;
    private final MouseListener mouseListener;
    private final KeyboardListener keyboardListener;
    private final InputCombinationFactory inputCombinationFactory;

    private InputBean() {
        this.mouseListener = new MouseListener();
        this.keyboardListener = new KeyboardListener();
        this.inputCombinationFactory = new InputCombinationFactory(mouseListener, keyboardListener);
    }

    public static Input getInput() {
        if(input == null){
            input = new InputBean();
        }
        return input;
    }

    @Override
    public void addMouseListener(Observer observer) {
        mouseListener.attach(observer);
    }

    @Override
    public void addKeyboardListener(Observer observer) {
        keyboardListener.attach(observer);
    }

    @Override
    public void removeMouseListener(Observer observer) {
        mouseListener.detach(observer);
    }

    @Override
    public void removeKeyboardListener(Observer observer) {
        keyboardListener.detach(observer);
    }

    @Override
    public int getMouseX() {
        return mouseListener.getX();
    }

    @Override
    public int getMouseY() {
        return mouseListener.getY();
    }

    @Override
    public InputCombination getCurrentInputCombination() {
        Set<InputElement> inputElements = mouseListener.getActivatedInputElements();
        inputElements.addAll(keyboardListener.getActivatedInputElements());
        return new ComplexInputCombination(keyboardListener, mouseListener, inputElements);
    }

    @Override
    public InputCombinationFactory getInputCombinationFactory() {
        return inputCombinationFactory;
    }
}

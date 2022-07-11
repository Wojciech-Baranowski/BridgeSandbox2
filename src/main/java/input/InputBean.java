package input;

import common.Observer;
import input.inputCombination.InputCombination;
import input.inputCombination.InputCombinationFactory;

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
        /*if(input == null){
            input = new InputBean();
        }
        return input;*/
        return null;
    }

    @Override
    public void addMouseListener(Observer observer) {

    }

    @Override
    public void addKeyboardListener(Observer observer) {

    }

    @Override
    public void removeMouseListener(Observer observer) {

    }

    @Override
    public void removeKeyboardListener(Observer observer) {

    }

    @Override
    public int getMouseX() {
        return 0;
    }

    @Override
    public int getMouseY() {
        return 0;
    }

    @Override
    public InputCombination getCurrentInputCombination() {
        return null;
    }

    @Override
    public InputCombinationFactory getInputCombinationFactory() {
        return null;
    }
}

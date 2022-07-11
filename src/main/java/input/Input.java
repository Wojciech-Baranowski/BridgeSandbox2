package input;

import common.Observer;
import input.inputCombination.InputCombination;
import input.inputCombination.InputCombinationFactory;

public interface Input {

    void addMMouseListener(Observer observer);

    void addKeyboardListener(Observer observer);

    void removeMouseListener(Observer observer);

    void removeKeyboardListener(Observer observer);

    int getMouseX();

    int getMouseY();

    InputCombination getCurrentInputCombination();

    InputCombinationFactory getInputCombinationFactory();

}

package engine.input;

import engine.common.Observer;
import engine.input.inputCombination.InputCombination;
import engine.input.inputCombination.InputCombinationFactory;

public interface Input {

    void addMouseListener(Observer observer);

    void addKeyboardListener(Observer observer);

    void removeMouseListener(Observer observer);

    void removeKeyboardListener(Observer observer);

    int getMouseX();

    int getMouseY();

    InputCombination getCurrentInputCombination();

    InputCombinationFactory getInputCombinationFactory();

    void initializeListeners();

}

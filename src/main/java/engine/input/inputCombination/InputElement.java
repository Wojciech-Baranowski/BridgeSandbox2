package engine.input.inputCombination;

import lombok.Getter;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class InputElement {

    @Getter
    private final ActionType actionType;
    @Getter
    private final InputEvent inputEvent;

    public InputElement(ActionType actionType, InputEvent inputEvent) {
        this.actionType = actionType;
        this.inputEvent = inputEvent;
    }

    public static InputEvent getMouseInputEventByKeycode(int keyCode) {
        Component dummyComponent = new Button();
        return new MouseEvent(
                dummyComponent, 0, 0, 0, 0, 0, 0, false, keyCode);
    }

    public static InputEvent getKeyboardInputEventByKeycode(int keyCode) {
        Component dummyComponent = new Button();
        return new KeyEvent(dummyComponent, 0, 0, 0, keyCode, '0', 0);
    }

}

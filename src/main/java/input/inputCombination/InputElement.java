package input.inputCombination;

import lombok.Getter;

import java.awt.event.InputEvent;

public class InputElement {

    @Getter private final ActionType actionType;
    @Getter private final InputEvent inputEvent;

    public InputElement(ActionType actionType, InputEvent inputEvent) {
        this.actionType = actionType;
        this.inputEvent = inputEvent;
    }

}

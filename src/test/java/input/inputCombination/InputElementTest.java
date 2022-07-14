package input.inputCombination;

import org.junit.Test;

import static input.inputCombination.ActionType.*;
import static org.junit.Assert.assertEquals;

public class InputElementTest {

    @Test
    public void accessors_test() {
        //given
        ActionType[] inputActions = new ActionType[]{UP, UP, DOWN, DOWN, HELD, FREE};
        InputElement[] output = new InputElement[inputActions.length];
        //when
        for (int i = 0; i < inputActions.length; i++) {
            inputActions[i] = UP;
            output[i] = new InputElement(inputActions[i], null);
        }
        //then
        for (int i = 0; i < inputActions.length; i++) {
            assertEquals(inputActions[i], output[i].getActionType());
        }
    }

}

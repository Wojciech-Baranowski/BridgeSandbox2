package input;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputBeanTest {

    @Test
    public void get_input_test() {
        //given
        Input input1 = null;
        InputBean input2 = null;
        Input input3;
        InputBean input4;
        Input input5;
        InputBean input6;
        //when
        input3 = InputBean.getInput();
        input4 = (InputBean) InputBean.getInput();
        input5 = InputBean.getInput();
        input6 = (InputBean) InputBean.getInput();
        //then
        assertNull(input1);
        assertNull(input2);
        assertNotNull(input3);
        assertNotNull(input4);
        assertEquals(input3, input5);
        assertEquals(input4, input6);
    }

}

package common;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class MeasurableTest {

    @Test
    public void compare_to_test(){
        //given
        Measurable mockedMeasurable = mock(Measurable.class);
        int[] z1 = {-7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7};
        int[] z2 = {-6, -6, 2, -3, -5, -2, -1, 3, 4, -5, 3, -5, 5, 6, 9};
        int[] outputCheck = {-1, 0, -1, -1, 1, 0, 0, -1, -1, 1, 0, 1, 0, 0, -1};
        int[] output = new int[z1.length];

        for(int i = 0; i < z1.length; i++){
            //when
            when(mockedMeasurable.getZ()).thenReturn(z1[i]);
            output[i] = mockedMeasurable.compareTo(z2[i]);
            //then
            assertTrue((output[i] > 0 && outputCheck[i] > 0)
                            || (output[i] == 0 && outputCheck[i] == 0)
                            || (output[i] < 0 && outputCheck[i] < 0));
        }
    }

}

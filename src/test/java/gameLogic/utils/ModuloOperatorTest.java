package gameLogic.utils;

import org.junit.Test;

import static gameLogic.utils.ModuloOperator.modAdd;
import static gameLogic.utils.ModuloOperator.modSub;
import static org.junit.Assert.assertEquals;

public class ModuloOperatorTest {

    @Test
    public void mod_add_test() {
        //given
        int[] input1 = {2, 5, 12, 6, 2, 4, 8, 4, 69};
        int[] input2 = {4, 78, 2, 4, 12, 8, 4, 9, 2};
        int[] inputMod = {3, 7, 2, 9, 5, 23, 7, 2, 4};
        int[] outputCheck = {0, 6, 0, 1, 4, 12, 5, 1, 3};
        int[] output = new int[input1.length];
        //when
        for(int i = 0; i < input1.length; i++) {
            output[i] = modAdd(input1[i], input2[i], inputMod[i]);
        }
        //then
        for(int i = 0; i < input1.length; i++) {
            assertEquals(outputCheck[i], output[i]);
        }
    }

    @Test
    public void mod_sub_test() {
        //given
        int[] input1 = {2, 5, 12, 6, 2, 4, 8, 4, 69};
        int[] input2 = {4, 78, 2, 4, 12, 8, 4, 9, 2};
        int[] inputMod = {3, 7, 2, 9, 5, 23, 7, 2, 4};
        int[] outputCheck = {1, 4, 0, 2, 0, 19, 4, 1, 3};
        int[] output = new int[input1.length];
        //when
        for(int i = 0; i < input1.length; i++) {
            output[i] = modSub(input1[i], input2[i], inputMod[i]);
        }
        //then
        for(int i = 0; i < input1.length; i++) {
            assertEquals(outputCheck[i], output[i]);
        }
    }

}

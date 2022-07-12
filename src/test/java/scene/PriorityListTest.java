package scene;

import org.junit.Test;

import java.util.List;

import static java.lang.Math.max;
import static org.junit.Assert.assertEquals;

public class PriorityListTest {

    @Test
    public void set_lowest_test() {
        //given
        Integer[] input = new Integer[]{2, 5, 4, 6, 1, 8, 2};
        //2
        //5 2
        //4 5 2
        //6 4 5 2
        //1 6 4 5 2
        //8 1 6 4 5 2
        //8 1 6 4 5 2
        List<Integer> outputCheck = List.of(8, 1, 6, 4, 5, 2);
        PriorityList priorityList = new PriorityList();
        List<Comparable<Integer>> output;
        //when
        for (int i = 0; i < input.length; i++) {
            priorityList.setOnLowest(input[i]);
        }
        output = (List<Comparable<Integer>>) priorityList.getObjectCollection();
        //then
        for (int i = 0; i < max(output.size(), outputCheck.size()); i++) {
            Integer outputObject = (Integer) output.get(i);
            assertEquals(outputCheck.get(i), outputObject);
        }
    }

    @Test
    public void set_highest_test() {
        //given
        Integer[] input = new Integer[]{2, 5, 4, 6, 1, 8, 2};
        //2
        //2 5
        //2 5 4
        //2 5 4 6
        //2 5 4 6 1
        //2 5 4 6 1 8
        //2 5 4 6 1 8
        List<Integer> outputCheck = List.of(2, 5, 4, 6, 1, 8);
        PriorityList priorityList = new PriorityList();
        List<Comparable<Integer>> output;
        //when
        for (int i = 0; i < input.length; i++) {
            priorityList.setOnHighest(input[i]);
        }
        output = (List<Comparable<Integer>>) priorityList.getObjectCollection();
        //then
        for (int i = 0; i < max(output.size(), outputCheck.size()); i++) {
            Integer outputObject = (Integer) output.get(i);
            assertEquals(outputCheck.get(i), outputObject);
        }
    }

    @Test
    public void set_lower_than_test() {
        //given
        Integer[] inputContained = new Integer[]{2, 5, 2, 5, 6, 5};
        Integer[] inputInserted = new Integer[]{5, 4, 6, 1, 8, 2};
        //2
        //5 2
        //4 5 2
        //4 5 6 2
        //4 1 5 6 2
        //4 1 5 8 6 2
        //4 1 5 8 6 2
        List<Integer> outputCheck = List.of(4, 1, 5, 8, 6, 2);
        PriorityList priorityList = new PriorityList();
        List<Comparable<Integer>> output;
        //when
        priorityList.setOnLowest(2);
        for (int i = 0; i < inputContained.length; i++) {
            priorityList.setLowerThan(inputInserted[i], inputContained[i]);
        }
        output = (List<Comparable<Integer>>) priorityList.getObjectCollection();
        //then
        for (int i = 0; i < max(output.size(), outputCheck.size()); i++) {
            Integer outputObject = (Integer) output.get(i);
            assertEquals(outputCheck.get(i), outputObject);
        }
    }

    @Test
    public void set_higher_than_test() {
        //given
        Integer[] inputContained = new Integer[]{2, 5, 2, 5, 6, 5};
        Integer[] inputInserted = new Integer[]{5, 4, 6, 1, 8, 2};
        //2
        //2 5
        //2 5 4
        //2 6 5 4
        //2 6 5 1 4
        //2 6 8 5 1 4
        //2 6 8 5 1 4
        List<Integer> outputCheck = List.of(2, 6, 8, 5, 1, 4);
        PriorityList priorityList = new PriorityList();
        List<Comparable<Integer>> output;
        //when
        priorityList.setOnHighest(2);
        for (int i = 0; i < inputContained.length; i++) {
            priorityList.setHigherThan(inputInserted[i], inputContained[i]);
        }
        output = (List<Comparable<Integer>>) priorityList.getObjectCollection();
        //then
        for (int i = 0; i < max(output.size(), outputCheck.size()); i++) {
            Integer outputObject = (Integer) output.get(i);
            assertEquals(outputCheck.get(i), outputObject);
        }
    }

    @Test
    public void remove_test() {
        //given
        Integer[] inputContained = new Integer[]{2, 5, 4, 6, 1, 8};
        Integer[] inputRemoved = new Integer[]{4, 6, 1, 5, 8, 4, 2, 2};
        PriorityList priorityList = new PriorityList();
        for (int i = 0; i < inputContained.length; i++) {
            priorityList.setOnHighest(inputContained[i]);
        }
        //2 5 4 6 1 8
        //2 5 6 1 8
        //2 5 1 8
        //2 5 8
        //2 8
        //2
        //2
        //
        //
        Integer[][] outputCheck = new Integer[inputRemoved.length][];
        outputCheck[0] = new Integer[]{2, 5, 6, 1, 8};
        outputCheck[1] = new Integer[]{2, 5, 1, 8};
        outputCheck[2] = new Integer[]{2, 5, 8};
        outputCheck[3] = new Integer[]{2, 8};
        outputCheck[4] = new Integer[]{2};
        outputCheck[5] = new Integer[]{2};
        outputCheck[6] = new Integer[]{};
        outputCheck[7] = new Integer[]{};
        List<Comparable<Integer>> output;
        for (int i = 0; i < inputRemoved.length; i++) {
            //when
            priorityList.remove(inputRemoved[i]);
            output = (List<Comparable<Integer>>) priorityList.getObjectCollection();
            //then
            for (int j = 0; j < max(output.size(), outputCheck[i].length); j++) {
                Integer outputObject = (Integer) output.get(j);
                assertEquals(outputCheck[i][j], outputObject);
            }
        }
    }

    @Test
    public void clear_test() {
        //given
        Integer[][] input = new Integer[3][];
        input[0] = new Integer[]{2, 5, 4, 6, 1, 8};
        input[1] = new Integer[]{3, 4, 5, 6};
        input[2] = new Integer[]{};
        PriorityList priorityList = new PriorityList();
        List<Comparable<Integer>> output;
        for (int i = 0; i < input.length; i++) {
            //when
            for (int j = 0; j < input[i].length; j++) {
                priorityList.setOnHighest(input[i][j]);
            }
            priorityList.clear();
            output = (List<Comparable<Integer>>) priorityList.getObjectCollection();
            //then
            assertEquals(0, output.size());
        }
    }

    @Test
    public void get_lowest_test() {
        //given
        Integer[] input = new Integer[]{2, 5, 4, 6, 1, 8};
        //2
        //5 2
        //4 5 2
        //6 4 5 2
        //1 6 4 5 2
        //8 1 6 4 5 2
        PriorityList priorityList = new PriorityList();
        Integer output;
        for (int i = 0; i < input.length; i++) {
            //when
            priorityList.setOnLowest(input[i]);
            output = (Integer) priorityList.getLowest();
            //then
            assertEquals(input[i], output);
        }
    }

    @Test
    public void get_highest_test() {
        //given
        Integer[] input = new Integer[]{2, 5, 4, 6, 1, 8};
        //2
        //2 5
        //2 5 4
        //2 5 4 6
        //2 5 4 6 1
        //2 5 4 6 1 8
        PriorityList priorityList = new PriorityList();
        Integer output;

        for (int i = 0; i < input.length; i++) {
            //when
            priorityList.setOnHighest(input[i]);
            output = (Integer) priorityList.getHighest();
            //then
            assertEquals(input[i], output);
        }
    }

}

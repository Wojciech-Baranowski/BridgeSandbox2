package scene;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PriorityList implements PriorityCollection{

    private final List<Comparable<Integer>> objects;

    PriorityList() {
        objects = new LinkedList<>();
    }

    @Override
    public void setLowerThan(Comparable<Integer> inserted, Comparable<Integer> contained) {

    }

    @Override
    public void setHigherThan(Comparable<Integer> inserted, Comparable<Integer> contained) {

    }

    @Override
    public void setOnLowest(Comparable<Integer> inserted) {

    }

    @Override
    public void setOnHighest(Comparable<Integer> inserted) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void remove(Comparable<Integer> removed) {

    }

    @Override
    public Collection<Comparable<Integer>> getObjectCollection() {
        return objects;
    }

    @Override
    public Comparable<Integer> getLowest() {
        return null;
    }

    @Override
    public Comparable<Integer> getHighest() {
        return null;
    }
}

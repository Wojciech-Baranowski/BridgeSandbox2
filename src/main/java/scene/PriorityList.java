package scene;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PriorityList implements PriorityCollection {

    private final List<Comparable<Integer>> objects;

    PriorityList() {
        objects = new LinkedList<>();
    }

    @Override
    public void setLowerThan(Comparable<Integer> inserted, Comparable<Integer> contained) {
        if(objects.contains(inserted)){
            return;
        }
        for(int i = 0; i < objects.size(); i++){
            if(objects.get(i).equals(contained)){
                objects.add(i, inserted);
                return;
            }
        }
    }

    @Override
    public void setHigherThan(Comparable<Integer> inserted, Comparable<Integer> contained) {
        if(objects.contains(inserted)){
            return;
        }
        for(int i = 0; i < objects.size(); i++){
            if(objects.get(i).equals(contained)){
                objects.add(i + 1, inserted);
                return;
            }
        }
    }

    @Override
    public void setOnLowest(Comparable<Integer> inserted) {
        if(objects.contains(inserted)){
            return;
        }
        objects.add(0, inserted);
    }

    @Override
    public void setOnHighest(Comparable<Integer> inserted) {
        if(objects.contains(inserted)){
            return;
        }
        objects.add(objects.size(), inserted);
    }

    @Override
    public void clear() {
        objects.clear();
    }

    @Override
    public void remove(Comparable<Integer> removed) {
        objects.remove(removed);
    }

    @Override
    public Collection<Comparable<Integer>> getObjectCollection() {
        return objects;
    }

    @Override
    public Comparable<Integer> getLowest() {
        return objects.get(0);
    }

    @Override
    public Comparable<Integer> getHighest() {
        return objects.get(objects.size() - 1);
    }
}

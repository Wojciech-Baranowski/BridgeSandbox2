package scene;

import common.Measurable;

import java.util.Collection;

public class MeasurablePriorityCollection implements PriorityCollection {

    private final PriorityCollection priorityCollection;

    MeasurablePriorityCollection(PriorityCollection priorityCollection) {
        this.priorityCollection = priorityCollection;
    }

    Measurable getTopObjectOnPosition(int x, int y) {
        Collection<Comparable<Integer>> objects = priorityCollection.getObjectCollection();
        Measurable result = null;
        for(Comparable<Integer> object : objects){
            Measurable measurable = (Measurable)object;
            if(measurable.inBorders(x, y)){
                result = measurable;
            }
        }
        return result;
    }

    @Override
    public void setLowerThan(Comparable<Integer> inserted, Comparable<Integer> contained) {
        priorityCollection.setLowerThan(inserted, contained);
    }

    @Override
    public void setHigherThan(Comparable<Integer> inserted, Comparable<Integer> contained) {
        priorityCollection.setHigherThan(inserted, contained);
    }

    @Override
    public void setOnLowest(Comparable<Integer> inserted) {
        priorityCollection.setOnLowest(inserted);
    }

    @Override
    public void setOnHighest(Comparable<Integer> inserted) {
        priorityCollection.setOnHighest(inserted);
    }

    @Override
    public void clear() {
        priorityCollection.clear();
    }

    @Override
    public void remove(Comparable<Integer> removed) {
        priorityCollection.remove(removed);
    }

    @Override
    public Collection<Comparable<Integer>> getObjectCollection() {
        return priorityCollection.getObjectCollection();
    }

    @Override
    public Comparable<Integer> getLowest() {
        return priorityCollection.getLowest();
    }

    @Override
    public Comparable<Integer> getHighest() {
        return priorityCollection.getHighest();
    }

}

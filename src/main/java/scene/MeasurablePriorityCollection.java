package scene;

import common.Measurable;

import java.util.Collection;

public class MeasurablePriorityCollection implements PriorityCollection {

    private final PriorityCollection priorityCollection;

    MeasurablePriorityCollection(PriorityCollection priorityCollection) {
        this.priorityCollection = priorityCollection;
    }

    Measurable getTopObjectOnPosition(int x, int y) {
        Collection<Object> objects = priorityCollection.getObjectCollection();
        Measurable result = null;
        for(Object object : objects){
            Measurable measurable = (Measurable)object;
            if(measurable.inBorders(x, y)){
                result = measurable;
            }
        }
        return result;
    }

    @Override
    public void setLowerThan(Object inserted, Object contained) {
        priorityCollection.setLowerThan(inserted, contained);
    }

    @Override
    public void setHigherThan(Object inserted, Object contained) {
        priorityCollection.setHigherThan(inserted, contained);
    }

    @Override
    public void setOnLowest(Object inserted) {
        priorityCollection.setOnLowest(inserted);
    }

    @Override
    public void setOnHighest(Object inserted) {
        priorityCollection.setOnHighest(inserted);
    }

    @Override
    public void clear() {
        priorityCollection.clear();
    }

    @Override
    public void remove(Object removed) {
        priorityCollection.remove(removed);
    }

    @Override
    public Collection<Object> getObjectCollection() {
        return priorityCollection.getObjectCollection();
    }

    @Override
    public Object getLowest() {
        return priorityCollection.getLowest();
    }

    @Override
    public Object getHighest() {
        return priorityCollection.getHighest();
    }

}

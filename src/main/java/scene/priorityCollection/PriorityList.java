package scene.priorityCollection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PriorityList implements PriorityCollection {

    private final List<Object> objects;

    public PriorityList() {
        objects = new LinkedList<>();
    }

    @Override
    public void setLowerThan(Object inserted, Object contained) {
        if (objects.contains(inserted)) {
            return;
        }
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).equals(contained)) {
                objects.add(i, inserted);
                return;
            }
        }
    }

    @Override
    public void setHigherThan(Object inserted, Object contained) {
        if (objects.contains(inserted)) {
            return;
        }
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).equals(contained)) {
                objects.add(i + 1, inserted);
                return;
            }
        }
    }

    @Override
    public void setOnLowest(Object inserted) {
        if (objects.contains(inserted)) {
            return;
        }
        objects.add(0, inserted);
    }

    @Override
    public void setOnHighest(Object inserted) {
        if (objects.contains(inserted)) {
            return;
        }
        objects.add(objects.size(), inserted);
    }

    @Override
    public void clear() {
        objects.clear();
    }

    @Override
    public void remove(Object removed) {
        objects.remove(removed);
    }

    @Override
    public Collection<Object> getObjectCollection() {
        return objects;
    }

    @Override
    public Object getLowest() {
        return objects.get(0);
    }

    @Override
    public Object getHighest() {
        return objects.get(objects.size() - 1);
    }
}

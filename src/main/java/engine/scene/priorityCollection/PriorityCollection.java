package engine.scene.priorityCollection;

import java.util.Collection;

public interface PriorityCollection {

    void setLowerThan(Object inserted, Object contained);

    void setHigherThan(Object inserted, Object contained);

    void setOnLowest(Object inserted);

    void setOnHighest(Object inserted);

    void clear();

    void remove(Object removed);

    Collection<Object> getObjectCollection();

    Object getLowest();

    Object getHighest();

}

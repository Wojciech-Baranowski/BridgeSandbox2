package scene;

import java.util.Collection;

public interface PriorityCollection {

    void setLowerThan(Comparable<Integer> inserted, Comparable<Integer> contained);

    void setHigherThan(Comparable<Integer> inserted, Comparable<Integer> contained);

    void setOnLowest(Comparable<Integer> inserted);

    void setOnHighest(Comparable<Integer> inserted);

    void clear();

    void remove(Comparable<Integer> removed);

    Collection<Comparable<Integer>> getObjectCollection();

    Comparable<Integer> getLowest();

    Comparable<Integer> getHighest();

}

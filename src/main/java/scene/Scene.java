package scene;

import common.Observer;
import common.Visual;

import java.util.Collection;

public interface Scene extends Observer {

    void update();

    void addObjectLowerThan(Visual inserted, Visual contained);

    void addObjectHigherThan(Visual inserted, Visual contained);

    void addOnHighest(Visual inserted);

    void addOnLowest(Visual inserted);

    void clear();

    void removeObject(Visual removed);

    Collection<Visual> getCurrentObjectCollection();

    Visual getTopObject();

    void switchCollection(String collectionName);

    void addCollection(String collectionName);

    void removeCollection(String collectionName);

    void initializeListeners();

}

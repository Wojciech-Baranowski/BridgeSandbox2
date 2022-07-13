package scene;

import common.Observer;
import display.Drawable;

import java.util.Collection;

public interface Scene extends Observer {

    void update();

    void addObjectLowerThan(Drawable inserted, Drawable contained);

    void addObjectHigherThan(Drawable inserted, Drawable contained);

    void addOnHighest(Drawable inserted);

    void addOnLowest(Drawable inserted);

    void clear();

    void removeObject(Drawable removed);

    Collection<Drawable> getCurrentObjectCollection();

    Drawable getTopObject();

    void switchCollection(String collectionName);

    void addCollection(String collectionName);

    void removeCollection(String collectionName);

}

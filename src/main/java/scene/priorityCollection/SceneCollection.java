package scene.priorityCollection;

import common.Interactive;
import common.Visual;
import display.Drawable;
import input.inputCombination.InputCombination;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SceneCollection {

    private final PriorityCollection priorityCollection;

    private final Map<InputCombination, Interactive> globallyActivatedObjects;

    public SceneCollection(PriorityCollection priorityCollection) {
        this.priorityCollection = priorityCollection;
        this.globallyActivatedObjects = new HashMap<>();
    }

    public Visual getTopObjectOnPosition(int x, int y) {
        Collection<Object> objects = priorityCollection.getObjectCollection();
        Visual result = null;
        for (Object object : objects) {
            Visual visual = (Visual) object;
            if (visual.getDrawable().inBorders(x, y) && !visual.getDrawable().isPixelOnPositionTransparent(x, y)) {
                result = visual;
            }
        }
        return result;
    }

    public void setLowerThan(Visual inserted, Visual contained) {
        priorityCollection.setLowerThan(inserted, contained);
    }

    public void setHigherThan(Visual inserted, Visual contained) {
        priorityCollection.setHigherThan(inserted, contained);
    }

    public void setOnLowest(Visual inserted) {
        priorityCollection.setOnLowest(inserted);
    }

    public void setOnHighest(Visual inserted) {
        priorityCollection.setOnHighest(inserted);
    }

    public void clear() {
        priorityCollection.clear();
    }

    public void remove(Visual removed) {
        priorityCollection.remove(removed);
    }

    public Collection<Visual> getObjectCollection() {
        return (Collection) priorityCollection.getObjectCollection();
    }

    public Visual getLowest() {
        return (Visual) priorityCollection.getLowest();
    }

    public Visual getHighest() {
        return (Visual) priorityCollection.getHighest();
    }

    public void updateGloballyActivatedObjects() {
        for(InputCombination activationCombination : globallyActivatedObjects.keySet()) {
            if(activationCombination.isActive()) {
                globallyActivatedObjects.get(activationCombination).update();
            }
        }
    }

    public void addGloballyActivatedObject(InputCombination activationCombination, Interactive object) {
        globallyActivatedObjects.put(activationCombination, object);
    }

    public void removeGloballyActivatedObject(Interactive object) {
        globallyActivatedObjects.values().remove(object);
    }

}

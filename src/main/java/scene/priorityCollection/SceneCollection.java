package scene.priorityCollection;

import display.Visual;

import java.util.Collection;

public class SceneCollection {

    private final PriorityCollection priorityCollection;

    public SceneCollection(PriorityCollection priorityCollection) {
        this.priorityCollection = priorityCollection;
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

}

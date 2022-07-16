package scene;

import common.Interactive;
import common.Visual;
import display.Display;
import display.DisplayBean;
import display.HoverMark;
import input.Input;
import input.InputBean;
import scene.priorityCollection.PriorityList;
import scene.priorityCollection.SceneCollection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SceneBean implements Scene {

    private static SceneBean scene;
    private final Display display;
    private final Input input;

    private SceneCollection currentObjectCollection;
    private final Map<String, SceneCollection> objectCollections;

    private SceneBean() {
        this.display = DisplayBean.getDisplay();
        this.input = InputBean.getInput();
        input.addMouseListener(this);
        input.addKeyboardListener(this);
        objectCollections = new HashMap<>();
    }

    public static Scene getScene() {
        if (scene == null) {
            scene = new SceneBean();
        }
        return scene;
    }

    @Override
    public void update() {
        if(currentObjectCollection != null) {
            currentObjectCollection.remove(HoverMark.getHoverMark());
        }
        Visual topObject = getTopObject();
        if (topObject instanceof Interactive) {
            ((Interactive) topObject).update();
            HoverMark.getHoverMark().fitHoverMarkToDrawable(topObject.getDrawable());
            currentObjectCollection.setHigherThan(HoverMark.getHoverMark(), topObject);
        }
        display.setObjectsToDraw(scene.getCurrentObjectCollection());
        display.draw();
    }

    @Override
    public void addObjectLowerThan(Visual inserted, Visual contained) {
        currentObjectCollection.setLowerThan(inserted, contained);
    }

    @Override
    public void addObjectHigherThan(Visual inserted, Visual contained) {
        currentObjectCollection.setHigherThan(inserted, contained);
    }

    @Override
    public void addOnHighest(Visual inserted) {
        currentObjectCollection.setOnHighest(inserted);
    }

    @Override
    public void addOnLowest(Visual inserted) {
        currentObjectCollection.setOnLowest(inserted);
    }

    @Override
    public void clear() {
        currentObjectCollection.clear();
    }

    @Override
    public void removeObject(Visual removed) {
        currentObjectCollection.remove(removed);
    }

    @Override
    public Collection<Visual> getCurrentObjectCollection() {
        if (currentObjectCollection == null) {
            return null;
        }
        return currentObjectCollection.getObjectCollection();
    }

    @Override
    public Visual getTopObject() {
        int x = input.getMouseX();
        int y = input.getMouseY();
        return currentObjectCollection.getTopObjectOnPosition(x, y);
    }

    @Override
    public void switchCollection(String collectionName) {
        currentObjectCollection = objectCollections.get(collectionName);
    }

    @Override
    public void addCollection(String collectionName) {
        objectCollections.put(collectionName, new SceneCollection(new PriorityList()));
    }

    @Override
    public void removeCollection(String collectionName) {
        if (!objectCollections.containsKey(collectionName)) {
            return;
        }
        if (currentObjectCollection != null &&
                currentObjectCollection.equals(objectCollections.get(collectionName))) {
            currentObjectCollection.clear();
            currentObjectCollection = null;
        }
        objectCollections.get(collectionName).clear();
        objectCollections.remove(collectionName);
    }

}

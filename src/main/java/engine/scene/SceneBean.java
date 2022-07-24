package engine.scene;

import engine.common.Interactive;
import engine.common.Visual;
import engine.display.Display;
import engine.display.DisplayBean;
import engine.display.HoverMark;
import engine.input.Input;
import engine.input.InputBean;
import engine.input.inputCombination.InputCombination;
import engine.scene.priorityCollection.PriorityList;
import engine.scene.priorityCollection.SceneCollection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SceneBean implements Scene {

    private static SceneBean scene;
    private final Display display;
    private final Input input;
    private final Map<String, SceneCollection> objectCollections;
    private SceneCollection currentObjectCollection;

    private SceneBean() {
        this.display = DisplayBean.getDisplay();
        this.input = InputBean.getInput();
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
            updateTopObject();
            currentObjectCollection.updateGloballyActivatedObjects();
            display.setObjectsToDraw(scene.getCurrentObjectCollection());
            display.draw();
        }
    }

    private void updateTopObject() {
        if(currentObjectCollection != null) {
            currentObjectCollection.remove(HoverMark.getHoverMark());
        }
        Visual topObject = getTopObject();
        if (topObject instanceof Interactive) {
            ((Interactive) topObject).update();
            HoverMark.getHoverMark().fitHoverMarkToDrawable(topObject.getDrawable());
            currentObjectCollection.setHigherThan(HoverMark.getHoverMark(), topObject);
        }
    }

    @Override
    public void initializeListeners() {
        input.addMouseListener(this);
        input.addKeyboardListener(this);
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
        if (currentObjectCollection != null) {
            return currentObjectCollection.getTopObjectOnPosition(x, y);
        }
        return null;
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
        if (currentObjectCollection != null && currentObjectCollection.equals(objectCollections.get(collectionName))) {
            currentObjectCollection.clear();
            currentObjectCollection = null;
        }
        objectCollections.get(collectionName).clear();
        objectCollections.remove(collectionName);
    }

    @Override
    public void addGloballyActivatedObject(InputCombination activationCombination, Interactive object) {
        currentObjectCollection.addGloballyActivatedObject(activationCombination, object);
    }

    @Override
    public void removeGloballyActivatedObject(Interactive object) {
        currentObjectCollection.removeGloballyActivatedObject(object);
    }

}

package scene;

import display.Display;
import display.DisplayBean;
import display.Drawable;
import input.Input;
import input.InputBean;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SceneBean implements Scene {

    private static SceneBean scene;
    private final Display display;
    private final Input input;

    private DrawablePriorityCollection currentObjectCollection;
    private final Map<String, DrawablePriorityCollection> objectCollections;

    private SceneBean() {
        this.display = DisplayBean.getDisplay();
        this.input = InputBean.getInput();
        objectCollections = new HashMap<>();
    }

    public static Scene getScene() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void addObjectLowerThan(Drawable inserted, Drawable contained) {

    }

    @Override
    public void addObjectHigherThan(Drawable inserted, Drawable contained) {

    }

    @Override
    public void addOnHighest(Drawable inserted) {

    }

    @Override
    public void addOnLowest(Drawable inserted) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void removeObject(Drawable removed) {

    }

    @Override
    public Collection<Drawable> getCurrentObjectCollection(){
        return null;
    }

    @Override
    public Drawable getTopObject() {
        return null;
    }

    @Override
    public void switchCollection(String collectionName) {

    }

    @Override
    public void addCollection(String collectionName) {

    }

    @Override
    public void removeCollection(String collectionName) {

    }

}

package scene.interactive;

import common.Command;
import display.Drawable;
import input.inputCombination.InputCombination;

import java.util.HashMap;
import java.util.Map;

public class InteractiveDrawable implements Drawable {

    private final Interactive parent;
    private final Drawable drawable;
    private final Map<InputCombination, Command> actions;

    public InteractiveDrawable(Drawable drawable, Interactive parent) {
        this.drawable = drawable;
        this.parent = parent;
        actions = new HashMap<>();
    }

    public void update() {
        for (InputCombination inputCombination : actions.keySet()) {
            if (inputCombination.isActive()) {
                parent.update(actions.get(inputCombination));
            }
        }
    }

    public void addAction(InputCombination inputCombination, Command command) {
        actions.put(inputCombination, command);
    }

    public void removeAction(InputCombination inputCombination) {
        actions.remove(inputCombination);
    }

    @Override
    public int[] getP() {
        return drawable.getP();
    }

    @Override
    public int getX() {
        return drawable.getX();
    }

    @Override
    public int getY() {
        return drawable.getY();
    }

    @Override
    public int getW() {
        return drawable.getW();
    }

    @Override
    public int getH() {
        return drawable.getH();
    }

    @Override
    public void setX(int x) {
        drawable.setX(x);
    }

    @Override
    public void setY(int y) {
        drawable.setY(y);
    }

}

package engine.button.checkbox;

import engine.common.Interactive;
import engine.common.Visual;
import engine.display.Drawable;
import engine.input.inputCombination.InputCombination;
import lombok.Getter;

public class Checkbox implements Visual, Interactive {

    protected final Drawable offDrawable;
    protected final Drawable onDrawable;
    protected final InputCombination activationCombination;
    @Getter
    protected boolean selected;

    public Checkbox(Drawable offDrawable, Drawable onDrawable, InputCombination activationCombination) {
        this.offDrawable = offDrawable;
        this.onDrawable = onDrawable;
        this.activationCombination = activationCombination;
        this.selected = false;
    }

    @Override
    public void update() {
        if (activationCombination == null || activationCombination.isActive()) {
            selected = !selected;
        }
    }

    @Override
    public Drawable getDrawable() {
        return selected ? onDrawable : offDrawable;
    }
}

package button;

import common.Interactive;
import common.Visual;
import display.Drawable;
import input.inputCombination.InputCombination;
import lombok.Getter;

public class Checkbox implements Visual, Interactive {

    private final Drawable offDrawable;
    private final Drawable onDrawable;
    private final InputCombination activationCombination;
    @Getter
    private boolean pressed;

    public Checkbox(Drawable offDrawable, Drawable onDrawable, InputCombination activationCombination) {
        this.offDrawable = offDrawable;
        this.onDrawable = onDrawable;
        this.activationCombination = activationCombination;
        this.pressed = false;
    }

    @Override
    public void update() {

    }

    @Override
    public Drawable getDrawable() {
        return null;
    }
}

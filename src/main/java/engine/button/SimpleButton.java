package engine.button;

import engine.common.Command;
import engine.common.Interactive;
import engine.common.Visual;
import engine.display.Drawable;
import engine.input.inputCombination.InputCombination;

public class SimpleButton implements Visual, Interactive {

    private final Drawable drawable;
    private final InputCombination activationCombination;
    private final Command action;

    public SimpleButton(Drawable drawable, InputCombination activationCombination, Command action) {
        this.drawable = drawable;
        this.activationCombination = activationCombination;
        this.action = action;
    }

    @Override
    public void update() {
        if (activationCombination == null || activationCombination.isActive()) {
            action.execute();
        }
    }

    @Override
    public Drawable getDrawable() {
        return drawable;
    }
}

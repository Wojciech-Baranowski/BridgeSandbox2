package button.radioButton;

import common.Interactive;
import common.Visual;
import display.Drawable;
import input.inputCombination.InputCombination;
import lombok.Setter;

public class RadioButton implements Visual, Interactive {

    private final Drawable offDrawable;
    private final Drawable onDrawable;
    private final InputCombination activationCombination;
    @Setter
    private RadioButtonBundle radioButtonBundle;

    public RadioButton(Drawable offDrawable, Drawable onDrawable, InputCombination activationCombination) {
        this.offDrawable = offDrawable;
        this.onDrawable = onDrawable;
        this.activationCombination = activationCombination;
    }


    @Override
    public void update() {

    }

    @Override
    public Drawable getDrawable() {
        return null;
    }
}

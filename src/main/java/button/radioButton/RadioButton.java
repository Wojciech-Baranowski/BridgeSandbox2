package button.radioButton;

import common.Interactive;
import common.Visual;
import display.Drawable;
import input.inputCombination.InputCombination;
import lombok.Getter;
import lombok.Setter;

public class RadioButton implements Visual, Interactive {

    private final Drawable offDrawable;
    private final Drawable onDrawable;
    private final InputCombination activationCombination;
    @Setter
    private RadioButtonBundle radioButtonBundle;
    @Getter
    @Setter
    private boolean selected;

    public RadioButton(Drawable offDrawable, Drawable onDrawable, InputCombination activationCombination) {
        this.offDrawable = offDrawable;
        this.onDrawable = onDrawable;
        this.activationCombination = activationCombination;
        this.selected = false;
    }


    @Override
    public void update() {
        if (activationCombination == null || activationCombination.isActive()) {
            radioButtonBundle.update(this);
        }
    }

    @Override
    public Drawable getDrawable() {
        return selected ? onDrawable : offDrawable;
    }
}

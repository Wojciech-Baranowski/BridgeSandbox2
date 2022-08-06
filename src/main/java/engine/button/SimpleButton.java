package engine.button;

import engine.common.Command;
import engine.display.Drawable;
import engine.input.inputCombination.InputCombination;

public class SimpleButton extends ComplexButton {

    public SimpleButton(Drawable drawable, InputCombination activationCombination, Command action) {
        super(drawable, new InputCombination[]{activationCombination}, new Command[]{action});
    }

    @Override
    public void update() {
        InputCombination activationCombination = actions.keySet()
                .stream()
                .findFirst()
                .orElse(null);
        if (activationCombination == null || activationCombination.isActive()) {
            actions.get(activationCombination).execute();
        }
    }

    @Override
    public Drawable getDrawable() {
        return drawable;
    }
}

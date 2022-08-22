package engine.button.checkbox;

import engine.common.Command;
import engine.display.Drawable;
import engine.input.inputCombination.InputCombination;
import lombok.Getter;

public class CommandCheckbox extends Checkbox {

    @Getter
    private final Command onCommand;
    @Getter 
    private final Command offCommand;

    public CommandCheckbox(Drawable offDrawable,
                           Drawable onDrawable,
                           InputCombination activationCombination,
                           Command onCommand,
                           Command offCommand) {
        super(offDrawable, onDrawable, activationCombination);
        this.onCommand = onCommand;
        this.offCommand = offCommand;
    }

    @Override
    public void update() {
        if (activationCombination == null || activationCombination.isActive()) {
            if (!selected) {
                selected = true;
                if (onCommand != null) {
                    onCommand.execute();
                }
            } else {
                selected = false;
                if (offCommand != null) {
                    offCommand.execute();
                }
            }
        }
    }
}

package engine.button;

import engine.common.Command;
import engine.common.Interactive;
import engine.common.Visual;
import engine.display.Drawable;
import engine.input.inputCombination.InputCombination;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;

public class ComplexButton implements Visual, Interactive {

    private final Drawable drawable;
    private final Map<InputCombination, Command> actions;

    public ComplexButton(Drawable drawable, InputCombination[] activationCombinations, Command[] actions) {
        this.drawable = drawable;
        this.actions = new HashMap<>();
        for (int i = 0; i < min(activationCombinations.length, actions.length); i++) {
            this.actions.put(activationCombinations[i], actions[i]);
        }
    }


    @Override
    public void update() {
        for (InputCombination inputCombination : actions.keySet()) {
            if (inputCombination.isActive()) {
                actions.get(inputCombination).execute();
            }
        }
    }

    @Override
    public Drawable getDrawable() {
        return drawable;
    }
}

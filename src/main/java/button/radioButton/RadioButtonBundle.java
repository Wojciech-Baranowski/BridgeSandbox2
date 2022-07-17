package button.radioButton;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class RadioButtonBundle {

    private final List<RadioButton> radioButtons;
    @Getter
    private int selectedRadioButtonIndex;

    public RadioButtonBundle() {
        this.radioButtons = new LinkedList<>();
    }

    public RadioButtonBundle(List<RadioButton> radioButtons) {
        this.radioButtons = radioButtons;
        for(RadioButton radioButton : radioButtons) {
            radioButton.setRadioButtonBundle(this);
        }
        selectedRadioButtonIndex = -1;
    }

    public List<Boolean> getBundleState() {
        return null;
    }

    public void addRadioButton(RadioButton radioButton) {

    }

    public void removeRadioButton(RadioButton radioButton) {

    }

}

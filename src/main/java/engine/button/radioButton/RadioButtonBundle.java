package engine.button.radioButton;

import java.util.LinkedList;
import java.util.List;

public class RadioButtonBundle {

    private final List<RadioButton> radioButtons;
    private RadioButton selectedRadioButton;

    public RadioButtonBundle() {
        this.radioButtons = new LinkedList<>();
    }

    public RadioButtonBundle(List<RadioButton> radioButtons) {
        this.radioButtons = radioButtons;
        for (RadioButton radioButton : radioButtons) {
            radioButton.setRadioButtonBundle(this);
        }
    }

    public void update(RadioButton currentlySelected) {
        if(selectedRadioButton != null && selectedRadioButton != currentlySelected) {
            selectedRadioButton.setSelected(false);
        }
        if(currentlySelected.isSelected()) {
            currentlySelected.setSelected(false);
            selectedRadioButton = null;
        } else {
            currentlySelected.setSelected(true);
            selectedRadioButton = currentlySelected;
        }
    }

    public void unset() {
        if(selectedRadioButton != null) {
            selectedRadioButton.setSelected(false);
            selectedRadioButton = null;
        }
    }

    public List<Boolean> getBundleState() {
        return radioButtons.stream()
                .map(RadioButton::isSelected)
                .toList();
    }

    public int getSelectedRadioButtonIndex() {
        for (int i = 0; i < radioButtons.size(); i++) {
            if (radioButtons.get(i).equals(selectedRadioButton)) {
                return i;
            }
        }
        return -1;
    }

    public void addRadioButton(RadioButton radioButton) {
        if (radioButtons.contains(radioButton)) {
            return;
        }
        radioButton.setRadioButtonBundle(this);
        radioButtons.add(radioButton);
    }

    public void removeRadioButton(RadioButton radioButton) {
        radioButtons.remove(radioButton);
    }

}

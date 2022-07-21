package engine.button.checkbox;

import java.util.LinkedList;
import java.util.List;

public class CheckboxBundle {

    private final List<Checkbox> checkboxes;

    public CheckboxBundle() {
        checkboxes = new LinkedList<>();
    }

    public CheckboxBundle(List<Checkbox> checkboxes) {
        this.checkboxes = checkboxes;
    }

    public List<Boolean> getBundleState() {
        return checkboxes.stream()
                .map(Checkbox::isSelected)
                .toList();
    }

    public void addCheckbox(Checkbox checkbox) {
        if (checkboxes.contains(checkbox)) {
            return;
        }
        checkboxes.add(checkbox);
    }

    public void removeCheckbox(Checkbox checkbox) {
        checkboxes.remove(checkbox);
    }

}

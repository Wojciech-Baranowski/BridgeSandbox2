package button.checkbox;

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
        return null;
    }

    public void addCheckbox(Checkbox checkbox) {

    }

    public void removeCheckbox(Checkbox checkbox) {

    }

}

package controllers.buttonController.cardOrderChangeButton;

import engine.common.Command;

import static controllers.cardController.CardController.getCardController;
import static controllers.main.assets.CardComparer.getCardComparer;

public class CardOrderChangeCommand implements Command {

    @Override
    public void execute() {
        if(getCardController().getCardOrder() == getCardComparer().getAscendingComparator()) {
            getCardController().setCardOrder(getCardComparer().getDescendingComparator());
        } else {
            getCardController().setCardOrder(getCardComparer().getAscendingComparator());
        }
        getCardController().reinitialize();
    }
}

package controllers.probabilityModeControllers.cardController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static controllers.probabilityModeControllers.backgroundController.ProbabilityModeBackgroundController.getProbabilityModeBackgroundController;
import static engine.display.DisplayBean.getDisplay;

public class ProbabilityModeCardController {

    private static ProbabilityModeCardController probabilityModeCardController;
    @Getter
    private final ChooseCards chooseCards;

    private ProbabilityModeCardController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable[] handSlots = getProbabilityModeBackgroundController().getCardSlots().getCardSlots();
        chooseCards = new ChooseCards(drawableFactory, handSlots);
    }

    public static ProbabilityModeCardController getProbabilityModeCardController() {
        if (probabilityModeCardController == null) {
            probabilityModeCardController = new ProbabilityModeCardController();
        }
        return probabilityModeCardController;
    }

}

package controllers.probabilityModeControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;
import lombok.Getter;

import static engine.scene.SceneBean.getScene;

public class LostTricks {

    @Getter
    private final Text lostTricks;

    LostTricks(DrawableFactory drawableFactory, Drawable playedCardSymbolSlot) {
        lostTricks = drawableFactory.makeText(
                "-0",
                990 + playedCardSymbolSlot.getX(),
                21 + playedCardSymbolSlot.getY(),
                "HBE48",
                "black");
        getScene().addObjectHigherThan(lostTricks, playedCardSymbolSlot);
    }

    public void updateLostTricks(int lostTricksNumber) {
        this.lostTricks.setText("-" + String.valueOf(lostTricksNumber));
    }

}

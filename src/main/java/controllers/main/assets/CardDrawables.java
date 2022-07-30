package controllers.main.assets;

import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import gameLogic.card.Color;
import gameLogic.card.Figure;

import static engine.display.DisplayBean.getDisplay;
import static gameLogic.game.GameConstants.DECK_SIZE;
import static gameLogic.game.GameConstants.FIGURE_NUMBER;

public class CardDrawables {

    private final static DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
    private static CardDrawables cardDrawables;
    private final Drawable[] cards;

    private CardDrawables() {
        cards = new Drawable[DECK_SIZE];
        for(int i = 0; i < DECK_SIZE; i++) {
            initializeDrawable(i);
        }
    }

    public static CardDrawables getCardDrawables() {
        if (cardDrawables == null) {
            cardDrawables = new CardDrawables();
        }
        return cardDrawables;
    }

    public static Drawable getCardDrawable(int cardId) {
        if (cardDrawables == null) {
            cardDrawables = new CardDrawables();
        }
        return cardDrawables.cards[cardId];
    }

    public static Drawable getCardDrawable(Figure figure, Color color) {
        if (cardDrawables == null) {
            cardDrawables = new CardDrawables();
        }
        int cardId = cardDrawables.getCardId(figure, color);
        return cardDrawables.cards[cardId];
    }

    private void initializeDrawable(int cardId) {
        String figureSymbol = Figure.values()[cardId % FIGURE_NUMBER].getSymbolString();
        String colorSymbol = Color.values()[cardId / FIGURE_NUMBER].getSymbolString();
        String color = getCardColorNameFromCardId(cardId);

        cards[cardId] =
                drawableFactory.makeFramedRectangle(0, 0, 65, 91, 2, "white", color);
        Drawable upperLeftCardFigure = drawableFactory.makeText(figureSymbol,2, 4, "HBE24", color);
        Drawable upperLeftCardColor = drawableFactory.makeText(colorSymbol,2, 22, "HBE24",  color);
        Drawable bottomRightCardColor = drawableFactory.makeText(colorSymbol,49, 44, "HBE24",  color);
        Drawable bottomRightCardFigure = drawableFactory.makeText(figureSymbol,49, 65, "HBE24",  color);

        cards[cardId] = new DrawableComposition(cards[cardId], upperLeftCardFigure);
        cards[cardId] = new DrawableComposition(cards[cardId], upperLeftCardColor);
        cards[cardId] = new DrawableComposition(cards[cardId], bottomRightCardColor);
        cards[cardId] = new DrawableComposition(cards[cardId], bottomRightCardFigure);
    }

    private String getCardColorNameFromCardId(int cardId) {
        return cardId < FIGURE_NUMBER || cardId >= FIGURE_NUMBER * 3 ? "black" : "red";
    }

    private int getCardId(Figure figure, Color color) {
        return figure.ordinal() + color.ordinal() * FIGURE_NUMBER;
    }

}

package controllers.main.assets;

import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import gameLogic.card.Color;
import gameLogic.card.Figure;
import gameLogic.player.Player;

import static engine.display.DisplayBean.getDisplay;
import static gameLogic.game.GameConstants.*;

public class CardDrawables {

    private final static DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
    private static CardDrawables cardDrawables;
    private final Drawable[] gameCards;
    private final Drawable[][] gameEditCards;

    private CardDrawables() {
        gameCards = new Drawable[DECK_SIZE];
        gameEditCards = new Drawable[PLAYER_NUMBER][DECK_SIZE];
        for (int i = 0; i < DECK_SIZE; i++) {
            gameCards[i] = initializeDrawable(i);
            for (int j = 0; j < PLAYER_NUMBER; j++) {
                gameEditCards[j][i] = initializeDrawable(i);
            }
        }
    }

    public static Drawable getGameCardDrawable(int cardId) {
        if (cardDrawables == null) {
            cardDrawables = new CardDrawables();
        }
        return cardDrawables.gameCards[cardId];
    }

    public static Drawable getGameEditCardDrawable(Player player, int cardId) {
        if (cardDrawables == null) {
            cardDrawables = new CardDrawables();
        }
        return cardDrawables.gameEditCards[player.ordinal()][cardId];
    }

    private Drawable initializeDrawable(int cardId) {
        Drawable gameCard;
        String figureSymbol = Figure.values()[cardId % FIGURE_NUMBER].getSymbolString();
        String colorSymbol = Color.values()[cardId / FIGURE_NUMBER].getSymbolString();
        String color = (cardId < FIGURE_NUMBER || cardId >= FIGURE_NUMBER * 3) ? "black" : "red";

        Drawable upperLeftCardFigure = drawableFactory.makeText(figureSymbol, 2, 4, "HBE24", color);
        Drawable upperLeftCardColor = drawableFactory.makeText(colorSymbol, 2, 22, "HBE24", color);
        Drawable bottomRightCardColor = drawableFactory.makeText(colorSymbol, 49, 44, "HBE24", color);
        Drawable bottomRightCardFigure = drawableFactory.makeText(figureSymbol, 49, 65, "HBE24", color);

        gameCard = drawableFactory.makeFramedRectangle(0, 0, 65, 91, 2, "white", color);
        gameCard = new DrawableComposition(gameCard, upperLeftCardFigure);
        gameCard = new DrawableComposition(gameCard, upperLeftCardColor);
        gameCard = new DrawableComposition(gameCard, bottomRightCardColor);
        gameCard = new DrawableComposition(gameCard, bottomRightCardFigure);

        return gameCard;
    }

}

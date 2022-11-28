package solver.probabilitySolver;

import gameLogic.card.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CardProbability {

    private final Card card;
    private final double probability;

    public CardProbability(Card card, double probability) {
        this.card = card;
        this.probability = probability;
    }
}

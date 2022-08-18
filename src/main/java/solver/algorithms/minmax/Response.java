package solver.algorithms.minmax;

public class Response {

    public static byte allCardsNumber;
    public byte[] cards;
    public byte nsPoints;

    Response (byte card, byte points){
        cards = new byte[allCardsNumber];
        cards[0] = card;
        this.nsPoints = points;
    }

    public void addData(byte card, byte point) {
        cards[cards.length - 1] = card;
        nsPoints += point;
    }

}

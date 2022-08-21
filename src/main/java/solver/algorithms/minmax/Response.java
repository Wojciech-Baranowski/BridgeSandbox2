package solver.algorithms.minmax;

public class Response {

    public static byte allCardsNumber;
    public byte[] cards;
    public byte nsPoints;

    Response(byte points) {
        cards = new byte[allCardsNumber];
        this.nsPoints = points;
    }

    public void addData(byte card, byte depth) {
        cards[depth] = card;
    }

}
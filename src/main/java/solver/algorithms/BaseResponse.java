package solver.algorithms;

public class BaseResponse {

    public static byte allCardsNumber;
    public byte[] cards;
    public byte nsPoints;

    public BaseResponse(byte points) {
        cards = new byte[allCardsNumber];
        this.nsPoints = points;
    }

}

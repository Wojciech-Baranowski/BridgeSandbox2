package solver.algorithms.minmax;

import solver.algorithms.BaseResponse;

public class Response extends BaseResponse {

    Response(byte points) {
        super(points);
    }

    public void addData(byte card, byte depth) {
        cards[depth] = card;
    }

}

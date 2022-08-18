package solver;

import gameLogic.game.Game;
import solver.result.Result;

public interface Algorithm {

    Result solve(Game game);

}

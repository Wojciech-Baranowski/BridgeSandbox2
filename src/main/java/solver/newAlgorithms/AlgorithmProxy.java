package solver.newAlgorithms;

import gameLogic.card.Card;
import gameLogic.game.Game;
import solver.Algorithm;
import solver.newAlgorithms.gameState.ArrayBasedGameStateFactory;
import solver.newAlgorithms.gameState.GameState;
import solver.newAlgorithms.gameState.GameStateFactory;
import solver.result.Result;

import java.util.Arrays;
import java.util.List;

public abstract class AlgorithmProxy implements Algorithm {

    private static final GameStateFactory gameStateFactory = new ArrayBasedGameStateFactory();

    public abstract int solve(GameState gameState);

    public Result solve(Game game) {
        GameState gameState = gameStateFactory.createGameState(game);
        int resultScore = solve(gameState);
        List<Card> resultCards = Arrays.stream(gameState.getResults()[resultScore]).boxed()
                .map(Card::new)
                .toList();
        return new Result(resultCards, game.getStartingPlayer(), game.getAtu(), gameState.getNumberOfGeneratedGameStates());
    }
}

package solver.newAlgorithms.gameState;

import gameLogic.game.Game;

public interface GameStateFactory {

    ArrayBasedGameState createGameState(Game game);
}

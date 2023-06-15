package solver.newAlgorithms.gameState;

import gameLogic.game.Game;

public interface GameStateFactory {

    GameState createGameState(Game game);
}

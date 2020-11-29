package mains;

import model.Game;
import model.exceptions.io.BattleshipIOException;
import model.io.IPlayer;
import model.io.IVisualiser;
import model.io.PlayerFactory;
import model.io.VisualiserFactory;
import model.ship.Board2D;

public class MainPruebas {

	public static void main(String[] args) throws BattleshipIOException {
		IPlayer player1 = PlayerFactory.createPlayer("Mary", "15");
		IPlayer player2 = PlayerFactory.createPlayer("Raul", "15");
		Board2D board1 = new Board2D(5);
		Board2D board2 = new Board2D(5);
		Game game = new Game(board1, board2, player1, player2);
		IVisualiser iv = VisualiserFactory.createVisualiser("Console", game);
		game.playGame(iv);
	}

}

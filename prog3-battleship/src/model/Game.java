package model;

import model.io.IPlayer;
import model.io.IVisualiser;

public class Game {
	private boolean gameStarted;
	private int nextToShoot;
	private int shootCounter;
	
	private IPlayer player1;
	private IPlayer player2;
	private Board board1;
	private Board board2;
	
	public Game(Board b1, Board b2, IPlayer p1, IPlayer p2) {
		this.player1=p1;
		this.player2=p2;
		this.board1=b1;
		this.board2=b2;
	}
	
	public IPlayer getPlayer1() { return this.player1; }
	
	public IPlayer getPlayer2() { return this.player2; }
	
	public IPlayer getPlayerLastShoot() {
		return null;
	}
	
	public Board getBoard1() { return this.board1; }

	public Board getBoard2() { return this.board2; }
	
	public void start() {
		
	}
	
	public boolean gameEnded() {
		return true;
	}
	
	public boolean playNext() {
		return true;
	}
	
	public void playGame(IVisualiser visualiser) {
		
	}
	
	public String toString() {
		return null;
	}
}

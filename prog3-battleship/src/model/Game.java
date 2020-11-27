package model;

import java.util.Objects;

import model.exceptions.*;
import model.exceptions.io.BattleshipIOException;
import model.io.IPlayer;
import model.io.IVisualiser;

public class Game {
	/** Indica si el juego a comenzado o no */
	private boolean gameStarted;
	/** indica el proximo jugador a disparar */
	private int nextToShoot;
	/** Contador de todos los disparos totales */
	private int shootCounter;
	
	private IPlayer player1;
	private IPlayer player2;
	private Board board1;
	private Board board2;
	
	/**
	 * Constructor de Game
	 * @param Board b1
	 * @param Board b2
	 * @param IPlayer p1
	 * @param IPlayer p2
	 */
	public Game(Board b1, Board b2, IPlayer p1, IPlayer p2) {
		Objects.requireNonNull(this.player1=p1);
		Objects.requireNonNull(this.player2=p2);
		Objects.requireNonNull(this.board1=b1);
		Objects.requireNonNull(this.board2=b2);
		this.gameStarted=false;
	}
	
	/**
	 * Getter de player1
	 * @return IPlayer player1
	 */
	public IPlayer getPlayer1() { return this.player1; }
	
	/**
	 * Getter de player2
	 * @return IPlayer player2
	 */
	public IPlayer getPlayer2() { return this.player2; }
	
	/**
	 * Getter de jugador que disparo por ultima vez.
	 * @return IPlayer lastPlayerShoot
	 */
	public IPlayer getPlayerLastShoot() { 
		//Si nadie ha disparado devuelvo null
		if(this.shootCounter == 0)
			return null;
		//Devuelvo el jugador al que no le toque disparar (porque acaba de hacerlo)
		if(this.nextToShoot==1) 
			return player1; 
		return player2; }
	
	/**
	 * Getter de Board1
	 * @return Board board1
	 */
	public Board getBoard1() { return this.board1; }

	/**
	 * Getter de Board2
	 * @return Board board2
	 */
	public Board getBoard2() { return this.board2; }
	
	/**
	 * Metodo start, inicia la partida colocando los navios.
	 */
	public void start() {
		// Inicializo las variables y coloco los navios
		try {
			this.player1.putCrafts(board1);
			this.player2.putCrafts(board2);
			this.gameStarted=true;
			this.shootCounter=0;
			this.nextToShoot=0;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Metodo gameEnded, comprueba si la partida ha terminado o no
	 * @return boolean
	 */
	public boolean gameEnded() {
		//Si el juego ha sido empezado y algun tablero tiene todo destruido, el juego ha acabado
		if(this.gameStarted) {
			if(this.board1.areAllCraftsDestroyed() || this.board2.areAllCraftsDestroyed())
				return true;
		}
		return false;
	}
	
	/**
	 * Metodo playNext, hace que el jugador al que le toque disparar haga su disparo
	 * @return true si dispara, falso si no llega a disparar
	 */
	public boolean playNext() {
		try{
			//Comprueba si el proximo a disparar es p1(0) o p2(1)
			if(this.nextToShoot == 0) {
				if(this.player1.nextShoot(board2) == null) 
					return false; //Si ya no tiene mas disparos devuelve false
				this.nextToShoot = 1;
			} else {
				if( this.player2.nextShoot(board1) == null)
					return false; //Si ya no tiene mas disparos devuelve false
				this.nextToShoot = 0;
			}
			//Actualiza el counter y devuelve true porque ha disparado
			this.shootCounter++;
			return true;
			
		} catch (BattleshipIOException | InvalidCoordinateException e) {
			throw new RuntimeException(e); //Convierto esas excepciones en RuntimeException
			
		} catch (CoordinateAlreadyHitException e) { 
			//Este error se origina al disparar a una posicion de un navio ya disparada, al agua no pasa nada
			//Si aparece este error imprimo quien lo ha provocado y el error
			if(this.nextToShoot == 0)
				System.out.println("Action by "+this.player1.getName()+"..."+e);
			else
				System.out.println("Action by "+this.player2.getName()+"..."+e);
			return false; //Devuelvo false porque no se hizo el disparo
		}
	}
	
	/**
	 * Metodo playGame, juega la partida mostrandolo con el IVisualiser hasta que se termine
	 * @param IVisualiser visualiser
	 */
	public void playGame(IVisualiser visualiser) {
		//Inicia partida y muestra el estado inicial
		this.start();
		visualiser.show();
		
		//Juega hasta que termine la partida o los turnos
		while(!this.gameEnded() && this.playNext()) {
			visualiser.show();
		}
		
		visualiser.close();
	}
	
	public String toString() {
		String partida;
		//Comprueba el estado de la partida
		if(!this.gameStarted)
			partida = "=== GAME NOT STARTED ===\n";
		else {
			if(this.gameEnded())
				partida = "=== GAME ENDED ===\n";
			else
				partida = "=== ONGOING GAME ===\n";
		}
		//Añade el board del jugador 1
		partida += "==================================\n" + this.player1.getName() 
				+ "\n==================================\n";
		partida += this.board1.show(false)+"\n";
		//Añade el board del jugador 2
		partida += "==================================\n" + this.player2.getName() 
				+ "\n==================================\n";
		partida += this.board2.show(false)+"\n";
		//Añade el total de disparos
		partida += "==================================\nNumber of shots: " + this.shootCounter;
		//Si la partida ha acabado, añade el ganador
		if(this.gameEnded()) {
			if(board1.areAllCraftsDestroyed())
				partida += player2.getName() + " wins";
			else
				partida += player1.getName() + " wins";
		}
			
		return partida;
	}
}

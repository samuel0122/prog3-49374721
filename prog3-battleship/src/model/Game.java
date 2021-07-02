package model;

import java.util.Objects;
import model.exceptions.*;
import model.exceptions.io.BattleshipIOException;
import model.io.IPlayer;
import model.io.IVisualiser;
import model.score.CraftScore;
import model.score.HitScore;

/**
 * Representa una partida de battleship.
 */
public class Game {
	
	/**  Indica si el juego a comenzado o no. */
	private boolean gameStarted;
	
	/**  indica el proximo jugador a disparar. */
	private int nextToShoot;
	
	/**  Contador de todos los disparos totales. */
	private int shootCounter;
	
	/** Jugador 1. */
	private IPlayer player1;
	
	/** Jugador 2. */
	private IPlayer player2;
	
	/** Tablero del jugador 1. */
	private Board board1;
	
	/** Tablero del jugador 2. */
	private Board board2;
	
	/** Puntos por golpear del jugador 1. */
	private HitScore hitScore1;
	
	/** Puntos por golpear del jugador 2. */
	private HitScore hitScore2;
	
	/** Puntos por destruir navios del jugador 1. */
	private CraftScore craftScore1;
	
	/** Puntos por destruir navios del jugador 2. */
	private CraftScore craftScore2;
	
	/**
	 * Constructor de Game.
	 *
	 * @param b1 Tablero del jugador 1.
	 * @param b2 Tablero del jugador 2.
	 * @param p1 Jugador 1.
	 * @param p2 Jugador 2.
	 */
	public Game(Board b1, Board b2, IPlayer p1, IPlayer p2) {
		Objects.requireNonNull(this.player1=p1);
		Objects.requireNonNull(this.player2=p2);
		Objects.requireNonNull(this.board1=b1);
		Objects.requireNonNull(this.board2=b2);
		this.gameStarted=false;
		this.hitScore1 = new HitScore(this.player1);
		this.hitScore2 = new HitScore(this.player2);;
		this.craftScore1 = new CraftScore(this.player1);
		this.craftScore2 = new CraftScore(this.player2);
	}
	
	/**
	 * Getter de player1.
	 *
	 * @return Jugador 1.
	 */
	public IPlayer getPlayer1() { return this.player1; }
	
	/**
	 * Getter de player2.
	 *
	 * @return Jugador 2.
	 */
	public IPlayer getPlayer2() { return this.player2; }
	
	/**
	 * Getter de jugador que disparo por ultima vez.
	 * 
	 * @return Jugador que disparó en el ultimo turno.
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
	 * Getter de Board1.
	 *
	 * @return Tablero del jugador 1.
	 */
	public Board getBoard1() { return this.board1; }

	/**
	 * Getter de Board2.
	 *
	 * @return Tablero del jugador 2.
	 */
	public Board getBoard2() { return this.board2; }
	
	/**
	 * Inicia la partida colocando los navios.
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
	 * Comprueba si la partida ha terminado.
	 *
	 * @return true, si la partida ha finalizado.
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
	 * Hace que el jugador al que le toque disparar haga su disparo.
	 *
	 * @return true, si consigue disparar.
	 * @throws RuntimeException Si se produce un error con la coordenada a disparar o la lectura de PlayerFile.
	 */
	public boolean playNext() {
		//Si le toca al jugador 1 y la partida ha terminado, devuelve false sin hacer nada
		
		try{
			
			//Datos necesarios para actualizar el Score
			Coordinate coord;
			CellStatus cell;
			
			//Comprueba si el proximo a disparar es p1(0) al board rival (board2)
			if(this.nextToShoot == 0) {
				
				coord = this.player1.nextShoot(board2);
				if(coord == null) 	
					return false; //Si ya no tiene mas disparos devuelve false

				//Si dispara, actualiza el contador de celdas
				cell = player1.getLastShotStatus();
				this.hitScore1.score(cell);
				//Y si destruye un barco, actualiza el contador de crafts
				if(cell == CellStatus.DESTROYED) {
					this.craftScore1.score(this.getBoard2().getCraft(coord));
				}
			} else {
				
				coord = this.player2.nextShoot(board1);
				if( coord == null)
					return false; //Si ya no tiene mas disparos devuelve false
				
				//Si dispara, actualiza el contador de celdas
				cell = player2.getLastShotStatus();
				this.hitScore2.score(cell);
				//Y si lo destruye actualiza el contador de crafts
				if(cell == CellStatus.DESTROYED) {
					this.craftScore2.score(this.getBoard1().getCraft(coord));
				}
			}
			
			//Actualiza contador de disparos antes de devolver true
			this.shootCounter++;
			this.nextToShoot = (nextToShoot+1)%2;
			//Devuelve true porque ha disparado	
			return true;
			
		
		} catch (CoordinateAlreadyHitException e) { 
			//Este error se origina al disparar a una posicion de un navio ya disparada, al agua no pasa nada
			//Si aparece este error imprimo quien lo ha provocado y el error
			if(this.nextToShoot == 0)
				System.out.println("Action by "+this.player2.getName()+": "+e);
			else
				System.out.println("Action by "+this.player1.getName()+": "+e);
			this.shootCounter++;
			this.nextToShoot = (nextToShoot+1)%2;
			
			return true; //Devuelvo false porque no se hizo el disparo
		}
		 catch (BattleshipIOException | InvalidCoordinateException e) {
			throw new RuntimeException(e); //Convierto esas excepciones en RuntimeException
		}
	}
	
	/**
	 * Juega la partida mostrandolo con el IVisualiser hasta que se termine.
	 *
	 * @param visualiser Representador de la partida.
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
	
	/**
	 * Crea el texto para representar la puntuación del juego.
	 * 
	 * @return Información de la puntuacion de la partida.
	 */
	public String getScoreInfo() {
		String scoreInfo = "Player 1\nHitScore: "+this.player1.getName()+": "+this.hitScore1.getScore()
		+"\nCraftScore: "+this.player1.getName()+": "+this.craftScore1.getScore()+"\n--------------\n"
		+"Player 2\nHitScore: "+this.player2.getName()+": "+this.hitScore2.getScore()
		+"\nCraftScore: "+this.player2.getName()+": "+this.craftScore2.getScore();
		return scoreInfo;
	}
	
	/**
	 * Getter del score por hits de player1.
	 *
	 * @return Puntos por golpear del jugador 1.
	 */
	public HitScore getHitScorePlayer1() {
		return this.hitScore1;
	}
	
	/**
	 * Getter del score por hits de player2.
	 *
	 * @return Puntos por golpear del jugador 2.
	 */
	public HitScore getHitScorePlayer2() {
		return this.hitScore2;
	}
	
	/**
	 * Getter del score por destruir crafts de player1.
	 *
	 * @return Puntos por destruir navios del jugador 1.
	 */
	public CraftScore getCraftScorePlayer1() {
		return this.craftScore1;
	}
	
	/**
	 * Getter del score por destruir crafts de player2.
	 *
	 * @return Puntos por destruir navios del jugador 2.
	 */
	public CraftScore getCraftScorePlayer2() {
		return this.craftScore2;
	}
	
	/**
	 * Muestra el estado de la partida, las dos tablas con los nombres de cada jugador, los disparos totales y si acaba la partida el ganador
	 *
	 * @return Representación en texto de la partida.
	 */
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
			if(board1.areAllCraftsDestroyed() && !board2.areAllCraftsDestroyed())
				partida += "\n"+ player2.getName() + " wins";
			else
				partida += "\n"+ player1.getName() + " wins";
		}
			
		return partida;
	}
}

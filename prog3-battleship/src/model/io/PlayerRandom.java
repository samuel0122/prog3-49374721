/**
 * @author Samuel Oliva Bulpitt
 */
package model.io;

import java.util.Random;

import model.Board;
import model.CellStatus;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.aircraft.Board3D;
import model.exceptions.BattleshipException;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.ship.Board2D;

/**
 * Jugador que utiliza numeros aleatorios para jugar una partida.
 */
public class PlayerRandom implements IPlayer {

	/**	Generador de numeros aleatorios usando una semilla. */
	private Random random;
	/** Nombre del jugador. */
	private String name;
	/** Estado de la ultima celda golpeada */
	private CellStatus lastShotStatus;
	
	/**
	 * Constructor de PlayerRandom.
	 * 
	 * @param name Nombre del jugador.
	 * @param seed Semilla para generar numeros aleatorios.
	 */
	public PlayerRandom(String name, long seed) {
		this.random = new Random(seed);
		this.name = name;
	}
	
	/**
	 * Obtiene el nombre y tipo del jugador.
	 * 
	 * @return Nombre y tipo del jugador.
	 */
	@Override
	public String getName() { return this.name+" (PlayerRandom)";	}

	/**
	 * Coloca los navios del jugador en el tablero.
	 *
	 * @param b Tablero en donde colocar un navio.
	 */
	@Override
	public void putCrafts(Board b) {
		String [] ordenNavios = { "ship.Battleship", "ship.Carrier", "ship.Cruiser", "ship.Destroyer", "aircraft.Bomber", "aircraft.Fighter", "aircraft.Transport" };
		int cantidadCoordAleatorias;
		
		int cantidadNavios;
		if(b instanceof Board2D )
			cantidadNavios = 4;
		else
			cantidadNavios = ordenNavios.length;
		
		for(int i = 0; i < cantidadNavios ; i++) {
			cantidadCoordAleatorias = 0;
			int orient = random.nextInt(4);
			Orientation orientacion;
			//Obtiene orientacion aleatoria
			switch (orient) {
				case 0: orientacion = Orientation.NORTH;
					break;
				case 1: orientacion = Orientation.EAST;
					break;
				case 2: orientacion = Orientation.SOUTH;
					break;
				default: orientacion = Orientation.WEST;
			}
			
			//1. Crea barco con orientacion aleatoria
			Craft navio = CraftFactory.createCraft(ordenNavios[i], orientacion);
			
			//2. Añade barco al tablero con una coordenada aleatoria hasta que no de error
			boolean error = true;
			while(error && (cantidadCoordAleatorias < 100)) {
				try {
					cantidadCoordAleatorias++;
					Coordinate c = genRandomCoordinate( b, Craft.BOUNDING_SQUARE_SIZE);
					b.addCraft( navio, c);
					error = false;
				} catch (BattleshipException e) {}
			} //Si se superan los 100 intentos, no se añade ese navio
		}
	}

	/**
	 * Simula el siguiente disparo del jugador en el tablero introducido.
	 * 
	 * @param b Tablero al que disparar.
	 * @return Coordenada en donde se golpeó.
	 * @throws CoordinateAlreadyHitException Si se intenta disparar en una coordenada ya golpeada.
	 * @throws InvalidCoordinateException Si se intenta disparar en una coordenada fuera del tablero.
	 */
	@Override
	public Coordinate nextShoot(Board b) throws CoordinateAlreadyHitException, InvalidCoordinateException {
		Coordinate c = genRandomCoordinate( b, 0);
		this.lastShotStatus=b.hit(c);
		return c;
	}
	
	/**
	 * Obtiene un numero aleatorio entre dos numeros.
	 * 
	 * @param min Valor mínimo a generar.
	 * @param max Valor máximo a generar.
	 * @return Numero aleatorio entre min y max.
	 */
	private int genRandomInt(int min, int max) { 
	    return random.nextInt(max-min)+min;
	}
	
	/**
	 * Obtiene una coordenada aleatoria para el tablero introducido.
	 * 
	 * @param b Tablero 2D o 3D para el que se desea obtener una coordenada.
	 * @param offset Valor que permite que las coordenadas sean negativas restandolo al minimo.
	 * @return Coordenada aleatoria para el tablero introducido.
	 */
	private Coordinate genRandomCoordinate (Board b, int offset) {
		int x = genRandomInt(0-offset,  b.getSize());
		int y = genRandomInt(0-offset,  b.getSize());
		int z;
		if (b instanceof Board3D) { //Si nos localizamos en una tabla 3D creamos un coordinate 3D
			z = genRandomInt(0-offset, b.getSize());
			return CoordinateFactory.createCoordinate(x, y, z);
		} else  //Si es una tabla 2D, creamos coordinate 2D
			return CoordinateFactory.createCoordinate(x, y);
		
	}
	
	/**
	 * Obtiene el estado de la ultima coordenada golpeada.
	 * 
	 * @return Estado del ultimo golpe.
	 */
	@Override
	public CellStatus getLastShotStatus() {
		return this.lastShotStatus;
	}
}
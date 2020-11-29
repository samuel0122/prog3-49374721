/**
 * @author Samuel Oliva Bulpitt
 */
package model.io;

import java.util.Random;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.aircraft.Board3D;
import model.exceptions.BattleshipException;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.ship.Board2D;

/**
 * Clase PlayerRandom, utiliza numeros aleatorios para jugar una partida
 */
public class PlayerRandom implements IPlayer {

	/**	Generador de numeros aleatorios usando una semilla. */
	private Random random;
	/** String name del jugador. */
	private String name;
	
	/**
	 * Constructor de PlayerRandom
	 * 
	 * @param String name del jugador.
	 * @param long seed para inicializar random
	 */
	public PlayerRandom(String name, long seed) {
		this.random = new Random(seed);
		this.name = name;
	}
	
	/**
	 * Getter del nombre.
	 * 
	 * @return String nombre del jugador
	 */
	@Override
	public String getName() { return this.name+" (PlayerRandom)";	}

	/**
	 * putCrafts de PlayerRandom. 
	 * Genera una orientacion aleatoria y una coordenada aleatoria por cada navio.
	 * Hay un limite de 100 coordenadas aleatorias generadas, si este se supera se deja el board como esta
	 * sin terminar de añadir todos los Crafts.
	 * 
	 * @param Board b, puede ser 2D o 3D
	 */
	@Override
	public void putCrafts(Board b) {
		String [] ordenNavios = { "Battleship", "Carrier", "Cruiser", "Destroyer", "Bomber", "Fighter", "Transport" };
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
	 * nextShoot() de PlayerRandom.
	 * Genera una coordenada aleatoria en el que dispara al board.
	 * 
	 * @param Board b, puede ser 2D o 3D
	 * @throws CoordinateAlreadyHitException
	 * @throws InvalidCoordinateException
	 */
	@Override
	public Coordinate nextShoot(Board b) throws CoordinateAlreadyHitException, InvalidCoordinateException {
		Coordinate c = genRandomCoordinate( b, 0);
		b.hit(c);
		return c;
	}
	
	/**
	 * Metodo genRandomInt, devuelve un numero aleatorio entre dos numeros
	 * 
	 * @param int maximo
	 * @param int minimo
	 * @return int numero Entre Max Y Min
	 */
	private int genRandomInt(int min, int max) { 
	    return random.nextInt(max-min)+min;
	}
	/**
	 * Metodo genRandomCoordinate que crea un coordinate aleatorio dependiendo de
	 * en que tipo de tabla se pase
	 * 
	 * @param Board b, puede ser 3D o 2D
	 * @param int offset, permite que las coordenadas sean negativas restandolo al minimo
	 * @return Coordinate coordenada aleatorio
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
}
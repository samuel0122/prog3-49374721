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
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
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
	 * 
	 * 
	 */
	@Override
	public void putCrafts(Board b) {
		String [] ordenNavios = { "Battleship", "Carrier", "Cruiser", "Destroyer", "Bomber", "Fighter", "Transport" };
		int cantidadCoordAleatorias = 0;
		
		int cantidadNavios;
		if(b instanceof Board2D )
			cantidadNavios = 4;
		else
			cantidadNavios = ordenNavios.length;
		
		for(int i = 0; (i < cantidadNavios) && (cantidadCoordAleatorias <10); i++) {
			
			
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
					b.addCraft(navio, genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
					error = false;
				} catch (BattleshipException e) {}
			}
		}
		
		
	}

	@Override
	public Coordinate nextShoot(Board b) {
		
		return null;
	}
	
	/**
	 * Metodo genRandomInt, devuelve un numero aleatorio entre dos numeros
	 * 
	 * @param int maximo
	 * @param int minimo
	 * @return int numero Entre Max Y Min
	 */
	private int genRandomInt (int max, int min) { return random.nextInt(max-min)+min; }
	
	/**
	 * Metodo genRandomCoordinate que crea un coordinate aleatorio dependiendo de
	 * en que tipo de tabla se pase
	 * 
	 * @param Board b, puede ser 3D o 2D
	 * @param int offset, permite que las coordenadas sean negativas restandolo al minimo
	 * @return Coordinate coordenada aleatorio
	 */
	private Coordinate genRandomCoordinate (Board b, int offset) {
		if (b instanceof Board3D) //Si nos localizamos en una tabla 3D creamos un coordinate 3D
			return CoordinateFactory.createCoordinate(genRandomInt(0-offset, b.getSize()), genRandomInt(0-offset, b.getSize()), genRandomInt(0-offset, b.getSize()));
		else if (b instanceof Board2D ) //Si es una tabla 2D, creamos coordinate 2D
			return CoordinateFactory.createCoordinate(genRandomInt(0-offset, b.getSize()), genRandomInt(0-offset, b.getSize()));
		return null;
	}
}


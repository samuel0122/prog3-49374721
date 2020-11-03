package model;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import model.exceptions.*;

public abstract class Board {

	/** The Constant HIT_SYMBOL. */
	public static final char HIT_SYMBOL = '•';
	/** The Constant WATER_SYMBOL. */
	public static final char WATER_SYMBOL = ' ';
	/** The Constant NOTSEEN_SYMBOL. */
	public static final char NOTSEEN_SYMBOL = '?';
	/** The Constant MAX_BOARD_SIZE. */
	protected static final int MAX_BOARD_SIZE = 20;
	/** The Constant MIN_BOARD_SIZE. */
	protected static final int MIN_BOARD_SIZE = 5;
	
	public static final char BOARD_SEPARATOR = '|';
	
	/** The size. */
	private int size;
	/** The num crafts. */
	private int numCrafts;
	/** The destroyed crafts. */
	private int destroyedCrafts;
	/** The seen. */
	private Set<Coordinate> seen = new HashSet<Coordinate>();
	/** The board. */
	private Map<Coordinate, Craft> board;
	
	public Board(int size) {
		numCrafts = 0;
		destroyedCrafts = 0;
		
		
		if(size < MIN_BOARD_SIZE || size > MAX_BOARD_SIZE) {
			this.size=MIN_BOARD_SIZE;
			throw new IllegalArgumentException();
			
		} else {
			this.size=size;
		}
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() { return size; }

	public abstract boolean checkCoordinate(Coordinate c);
	
	public abstract String show (boolean unveil);
	
	/**
	 * Barco que ocupa. Método protected añadido que recibe una coordenada y devuelve el barco
	 * que tenga alguna posicion en esa coordenada, o nulo si no hay ningun barco ocupando ese espacio
	 *
	 * @param c the c
	 * @return the ship
	 */
	
	protected Craft barcoQueOcupa(Coordinate c) {
		if(numCrafts > 0) {
			Set <Coordinate> TodosCoord = board.keySet(); 
		
			for(Coordinate CoordBarco : TodosCoord) { //Recorro todas las coord en donde hay asociado un ship
				
				Craft nave = board.get(CoordBarco);
				//De cada ship, copio todas sus posiciones ocupadas
				Set <Coordinate> todosPositions = nave.getAbsolutePositions();
				
				
				for( Coordinate pos : todosPositions) {
					
					//Si alguna de esas posiciones es igual a la coordenada que preguntan devuelvo el ship
					if(pos.equals(c)) {
	
						return nave;
					}
				}
			}
		}
		
		return null; //Si no hubo ningun ship que ocupa esa coordenada, devuelvo null
	}

	/**
	 * Adds the ship.
	 *
	 * @param ship the ship
	 * @param coordinate the c
	 * @return true, if successful
	 * @throws InvalidCoordinateException 
	 * @throws OccupiedCoordinateException 
	 * @throws NextToAnotherCraftException 
	 */
	public boolean addCraft(Craft craft, Coordinate c) throws BattleshipException {
		Set <Coordinate> positions = craft.getAbsolutePositions(c);
		
		for(Coordinate pos : positions) {
			
			if(!checkCoordinate(pos)) { //Si la coord esta fuera del tablero manda su mensaje de error y no guarda
				throw new InvalidCoordinateException(pos);
			
			} else if(barcoQueOcupa(pos) != null){ //Si una posicion ya está ocupada manda mensaje de error y no guarda
				throw new OccupiedCoordinateException(pos);
				
			} else { 
				
				for(Coordinate posVecinos : pos.adjacentCoordinates()) {
					if(barcoQueOcupa(posVecinos) != null){ //Si una coord vecina a su posicion está ocupada manda error de vecino y no guarda
						throw new NextToAnotherCraftException(posVecinos);
					}
				}
				
			}
			
		}

		//Si no ha habido ningun problema, se añade la posicion al ship, este al tablero y se incrementa en 1 el numCrafts
		
		craft.setPosition(c);
		System.out.println(c);

		System.out.println(craft);
		board.put(c, craft);
		numCrafts ++;
		return true;
		
	}

	/**
	 * Gets the ship.
	 *
	 * @param c the c
	 * @return the ship
	 */
	public Craft getCraft(Coordinate c) { return board.get(c); }

	/**
	 * Checks if is seen. Devuelve true si la coordenada pasada ya es conocida por el rival o false
	 * si no lo es
	 *
	 * @param c the c
	 * @return true, if is seen
	 */
	public boolean isSeen(Coordinate c) {
		if(seen != null && seen.contains(c)) {
			return true;
		}
		return false;
	}

	/**
	 * Hit. Simula un disparo. Añade la coordenada a la lista seen para que el rival
	 * lo pueda ver y devuelve lo que ocurre en esa coordenada, si cae en agua, si golpea a un ship o si
	 * destruye un ship con ello
	 *
	 * @param c the c
	 * @return the cell status
	 * @throws CoordinateAlreadyHitException 
	 * @throws InvalidCoordinateException 
	 */
	public CellStatus hit (Coordinate c) throws CoordinateAlreadyHitException, InvalidCoordinateException {
		seen.add(c);
		
		if(!checkCoordinate(c)) { //Si el disparo se va fuera del tablero imprime error
			throw new InvalidCoordinateException(c);
		}
		
		Craft barco = barcoQueOcupa(c); 
		
		if(barco != null) { //Si hay algun ship en la coordenada en donde dispara
			if(barco.hit(c)) { //Y si consige golpearle
				if(barco.isShotDown()) { //Y lo destruye
					
					//Añado todos sus vecinos al seen y devuelve destroyed
					Set <Coordinate> vecinosDescubiertos = getNeighborhood(barco);
					for(Coordinate vecino:vecinosDescubiertos) {
						seen.add(vecino);
					}
					
					destroyedCrafts += 1;
					return CellStatus.DESTROYED;
				}
				//O devuelve hit si solo ha golpeado el ship
				return CellStatus.HIT;
			}
		}
		
		//O si no habia un ship devuelve WATER
		return CellStatus.WATER;
	
	}

	/**
	 * Are all craft destroyed. Devuelve true si todos los ships del tablero 
	 * fueron destruidos o false aun hay barcos con alguna posicion sin golpear
	 *
	 * @return true, if successful
	 */
	public boolean areAllCraftsDestroyed() {
		if(destroyedCrafts == numCrafts)
			return true;
		return false;
	}

	/**
	 * Gets the neighborhood. Devuelve una lista con todas 
	 * las coordenadas que envolverian a las posiciones del ship si lo colocara ahí.
	 * Las posiciones propias del ship no se añaden
	 *
	 * @param ship the ship
	 * @param position the position
	 * @return the neighborhood
	 */
	public Set<Coordinate> getNeighborhood(Craft ship, Coordinate position) {
		Objects.requireNonNull(position);
		Objects.requireNonNull(ship);
		Set<Coordinate> neighbors = new HashSet <Coordinate>();
		
		//Por cada coord del barco
		for(Coordinate posBarco : ship.getAbsolutePositions(position)) {
			
			//Añadir las coordenadas adyacentes si esa coordenada no se sale del tablero
			for(Coordinate coordAdyacentes : posBarco.adjacentCoordinates()) {
				
				if(checkCoordinate(coordAdyacentes)) {
					neighbors.add(coordAdyacentes);
				}
			}
		}
		
		//Quitamos las posiciones del barco de la lista de vecinos
		for(Coordinate posBarco : ship.getAbsolutePositions(position)) {
			neighbors.remove(posBarco);
		}
		
		return neighbors;
	}

	/**
	 * Gets the neighborhood. Método simplificado de getNeighborhood que recibe un ship y llama a la otra funcion
	 * getNeighborhood pasandole la posicion del ship.
	 *
	 * @param ship the ship
	 * @return the neighborhood
	 */
	public Set<Coordinate> getNeighborhood(Craft ship) { return getNeighborhood(ship, ship.getPosition()); }

	/**
	 * To string. Devuelve un string con la información básica del tablero
	 *
	 * @return the string
	 */
	public String toString() { return "Board " + size + "; crafts: "+ numCrafts +"; destroyed: " + destroyedCrafts; }

}
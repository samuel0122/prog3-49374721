/**
 * 	@author Samuel Oliva Bulpitt
 */
package model;

import java.util.*;

import model.exceptions.*;

/**
 * Tablero que almacena las coordenadas y los navios guardados.
 */
public abstract class Board {

	/** Constante HIT_SYMBOL. Representa el simbolo a dibujar en partes de un navio golpeado. */
	public static final char HIT_SYMBOL = '•';
	/** Constante WATER_SYMBOL. Representa el simbolo a dibujar en coordenadas donde no hay ningun navio. */
	public static final char WATER_SYMBOL = ' ';
	/** Constante NOTSEEN_SYMBOL. Representa las coordenadas no interaccionadas cuando se muestre el tablero en modo oculto. */
	public static final char NOTSEEN_SYMBOL = '?';
	/** Constante MAX_BOARD_SIZE. Indica el tamaño maximo que un tablero puede tener. */
	protected static final int MAX_BOARD_SIZE = 20;
	/** Constante MIN_BOARD_SIZE. Infica el tamaño minimo que un tablero puede tener. */
	protected static final int MIN_BOARD_SIZE = 5;
	/** Constante BOARD_SEPARATOR. Representa la barra que separa las distintas capas del board en Board3D. */
	public static final char BOARD_SEPARATOR = '|';
	
	/** Int size. Tamaño configurada para el tablero. */
	private int size;
	/** Int numCrafts. Cantidad de navios creados en el tablero. */
	private int numCrafts;
	/** Int destroyed crafts. Cantidad de navios destruidos en el tablero. */
	private int destroyedCrafts;
	/** Set seen. Lista de coordenadas con las que el rival ya ha interactuado. */
	private Set<Coordinate> seen = new HashSet<Coordinate>();
	/** Map board. Mapa board con key Coordinate y valor Craft. Almanacerá los Craft que se crean en el tablero en sus respectivas coordenadas. */
	private Map<Coordinate, Craft> board = new HashMap<Coordinate ,Craft>();
	
	/**
	 * Constructor de Board. Recibe el tamaño deseado y comprueba que sea correcto. 
	 *
	 * @param size Tamaño del tablero creado.
	 * @throws IllegalArgumentException si se introduce un tamaño incorrecto.
	 */
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
	 * Getter de size. 
	 * @return size del tablero.
	 */
	public int getSize() { return size; }

	/**
	 * Comprueba si la coordenada es correcta.
	 *
	 * @param c Coordenada a comprobar.
	 * @return true, si la coordenada es valida.
	 */
	public abstract boolean checkCoordinate(Coordinate c);
	
	/**
	 * Representa el tablero en un String.
	 *
	 * @param unveil Debe ser true si se quiere mostrar todos los elementos del tablero.
	 * @return Representación del tablero en String.
	 */
	public abstract String show (boolean unveil);
	
	/**
	 * Devuelve el navio que se encuentra en una coordenada.
	 *
	 * @param c Coordenada ocupada por el navio que se busca.
	 * @return El navio que ocupa la coordenada o null si no hay ninguno.
	 */
	
	public Craft getCraft(Coordinate c) {
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
	 * Añade un navio en el tablero si la posición es valida.
	 *
	 * @param craft Navio a añadir en el tablero.
	 * @param c Coordenada perteneciente a la esquina superior izquierda del navio.
	 * @return true, si se añade con éxito el navio.
	 * @throws InvalidCoordinateException si la coordenada es incorrecta.
	 * @throws OccupiedCoordinateException si se intenta añadir un navio en una posicion ya ocupada.
	 * @throws NextToAnotherCraftException si se intenta colocar al lado de otro navio.
	 */
	public boolean addCraft(Craft craft, Coordinate c) throws InvalidCoordinateException, OccupiedCoordinateException,  NextToAnotherCraftException {
		Set <Coordinate> positions = craft.getAbsolutePositions(c);
		
		for(Coordinate pos : positions) {
			
			if(!checkCoordinate(pos)) //Si la coord esta fuera del tablero manda su mensaje de error y no guarda
				throw new InvalidCoordinateException(c);
		}
		
		for(Coordinate pos : positions) {
			
			if(getCraft(pos) != null) //Si una posicion ya está ocupada manda mensaje de error y no guarda
				throw new OccupiedCoordinateException(c);
		}
		
		for(Coordinate pos : positions) {
			
			for(Coordinate posVecinos : pos.adjacentCoordinates()) {
				if(getCraft(posVecinos) != null)	//Si una coord vecina a su posicion está ocupada manda error de vecino y no guarda
					throw new NextToAnotherCraftException(c);
			}
		}

		//Si no ha habido ningun problema, se añade la posicion al ship, este al tablero y se incrementa en 1 el numCrafts
		
		craft.setPosition(c);
		
		
		board.put(c, craft);
		numCrafts ++;
		return true;
		
	}

	
	/**
	 * Comprueba si la coordenada ya fue interactuada.
	 *
	 * @param c Coordenada a comprobar.
	 * @return true, si la coordenada es conocida.
	 */
	public boolean isSeen(Coordinate c) {
		if(seen != null && seen.contains(c)) {
			return true;
		}
		return false;
	}

	/**
	 * Simula un disparo.
	 *
	 * @param c Coordenada en donde disparar.
	 * @return Estado resultante de la casilla.
	 * @throws CoordinateAlreadyHitException si se golpea a una coordenada ya golpeada.
	 * @throws InvalidCoordinateException si se golpea a una coordenada no valida.
	 */
	public CellStatus hit (Coordinate c) throws CoordinateAlreadyHitException, InvalidCoordinateException {
		
		
		if(!checkCoordinate(c)) { //Si el disparo se va fuera del tablero imprime error
			throw new InvalidCoordinateException(c);
		}
		seen.add(c.copy());
		
		Craft barco = getCraft(c); 
		
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
	 * Comprueba si todos los navios del tablero fueron destruidos.
	 *
	 * @return true, si todos los navios son destruidos.
	 */
	public boolean areAllCraftsDestroyed() {
		if(destroyedCrafts == numCrafts)
			return true;
		return false;
	}

	/**
	 * Gets the neighborhood. Devuelve una lista con todas las coordenadas vecinas al navio si lo colocara ahi.
	 * Las posiciones propias del ship no cuentan
	 *
	 * @param navio El navio del cual queremos obtener las coordenadas adyacentes.
	 * @param position Coordenada en la que está colocada en navio.
	 * @return Coordenadas adyacentes al navio.
	 * @throws NullPointerException si los parametros son nulos.
	 */
	public Set<Coordinate> getNeighborhood(Craft navio, Coordinate position) {
		Objects.requireNonNull(position);
		Objects.requireNonNull(navio);
		Set<Coordinate> neighbors = new HashSet <Coordinate>();
		
		//Por cada coord del barco
		for(Coordinate posNavio : navio.getAbsolutePositions(position)) {
			
			//Añadir las coordenadas adyacentes si esa coordenada no se sale del tablero
			for(Coordinate coordAdyacentes : posNavio.adjacentCoordinates()) {
				
				if(checkCoordinate(coordAdyacentes)) {
					neighbors.add(coordAdyacentes);
				}
			}
		}
		
		//Quitamos las posiciones del barco de la lista de vecinos
		for(Coordinate posBarco : navio.getAbsolutePositions(position)) {
			neighbors.remove(posBarco);
		}
		
		return neighbors;
	}

	/**
	 * Gets the neighborhood. Método simplificado de getNeighborhood que recibe un ship y llama a la otra funcion
	 * getNeighborhood pasandole la posicion del ship.
	 *
	 * @param navio the navio
	 * @return Set neighborhood, coordenadas vecinas
	 */
	public Set<Coordinate> getNeighborhood(Craft navio) { return getNeighborhood(navio, navio.getPosition()); }

	/**
	 * To string. Devuelve un string con la informacion basica del tablero
	 *
	 * @return String de informacion del Board
	 */
	public String toString() { return "Board " + size + "; crafts: "+ numCrafts +"; destroyed: " + destroyedCrafts; }

}
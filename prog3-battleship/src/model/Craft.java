/**
  	@author Samuel Oliva Bulpitt
 
 */

package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import model.Coordinate;
import model.ship.*;
import model.exceptions.*;

/**
 * Navios.
 */
public abstract class Craft {

	/** Constante BOUNDING_SQUARE_SIZE. Tamaño del shape de los navios. */
	public static final int BOUNDING_SQUARE_SIZE = 5;
	/** Constante HIT_VALUE. Valor en el shape que indica posicion golpeada en el navio. */
	private static final int HIT_VALUE = -1;
	/** Constante CRAFT_VALUE. Valor en el shape que indica posicion que el navio ocupa. */
	private static final int CRAFT_VALUE = 1;
	
	/** Orientation. Orientacion del navio, influira en el shape. */
	private Orientation orientation;
	/** Coordinate position. Posicion establecida para el navio. */
	private Coordinate position;
	
	/** Char symbol. Simbolo del navio para representarlo en el tablero. */
	private char symbol;
	/** String name. Nombre del navio. */
	private String name;
	/** int shape. Array de int que dibuja el navio. La primera posicion indica la orientacion, la segunda el dibujo del navio. */
	protected int shape[][];
	          
	/**
	 * Contructor de Craft. Inicializa el navio con posicion null.
	 *
	 * @param orient Orientación del navio.
	 * @param sym Símbolo para representar el navio.
	 * @param nom Nombre del navio.
	 */
	public Craft(Orientation orient, char sym, String nom) {
		orientation = orient;
		symbol = sym;
		name = nom;
	  	position = null;
	}
	
	/**
	 * Getter del valor del navio.
	 *
	 * @return Los puntos que se obtiene al destruir este tipo de navio.
	 */
	public abstract int getValue();
	
	/**
	 * Getter de la posicion.
	 *
	 * @return Coordenada de posicion del navio.
	 */
	public Coordinate getPosition() {
		if(position!=null) 
			return position.copy();
		
			
		return null;
	}

	/**
	 * Setter de position.
	 *
	 * @param posicion Coordenada en donde posicionar el navio.
	 */
	public void setPosition(Coordinate posicion) { this.position = posicion.copy(); }

	/**
	 * Getter del name.
	 *
	 * @return Nombre del navio.
	 */
	public String getName() { return name; }

	/**
	 * Getter de orientation.
	 *
	 * @return Orientacion del navio.
	 */
	public Orientation getOrientation() { return orientation; }

	/**
	 * Getter de symbol.
	 *
	 * @return Simbolo para representar el navio.
	 */
	public char getSymbol() { return symbol; }

	/**
	 * Getter de shape.
	 *
	 * @return Shape del navio.
	 */
	public int [][] getShape() { return shape; }

	/**
	 * Transforma una coordenada del tablero en la coordenada relativa del navio.
	 * 
	 * @param c Coordenada absoulta del tablero.
	 * @return La coordenada respecto al shape del navio.
	 */
	
	private Coordinate getRelativePosition(Coordinate c) {
		Coordinate cRel = new Coordinate2D (c.get(0) - position.get(0), c.get(1) - position.get(1));
		
		return cRel;
	}

	/**
	 * Recibe una coordenada del navio y devuelve el indice al que pertenece en shape.
	 *
	 * @param c Coordenada relativa del navio.
	 * @return Posición de la coordenada dentro del array de shape.
	 * @throws NullPointerException Si la coordenada es nula.
	 */
	public int getShapeIndex(Coordinate c) {
		Objects.requireNonNull(c);
		return (c.get(1)*(BOUNDING_SQUARE_SIZE))+c.get(0);
	
	}

	/**
	 * Devuelve una lista con todas las posiciones en el tablero del navio, sean o no alcanzadas. 
	 * 
	 * @param c Coordenada en el tablero del navio.
	 * @return Conjunto de coordenadas en el tablero de las posiciones del navio.
	 * @throws NullPointerException Si la coordenada es nula.
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate c) {
		Objects.requireNonNull(c);
		Set<Coordinate> positions = new HashSet<Coordinate>();
		for(int i=0; i < BOUNDING_SQUARE_SIZE*BOUNDING_SQUARE_SIZE ; i++) { //Recorre todos los valores de shape
				
			//Si una coordenada tiene el valor de craft o del hit, lo guarda
			if(shape[orientation.ordinal()][i] == CRAFT_VALUE || shape[orientation.ordinal()][i]==HIT_VALUE) { 
				
				positions.add(c.add(new Coordinate2D(i % BOUNDING_SQUARE_SIZE, i / BOUNDING_SQUARE_SIZE)));
			}
		}
		
		
		return positions;
	}

	/**
	 * Obtiene las coordenadas absolutas del navio utilizando la posicion ya establecida a este.
	 *
	 * @return Conjunto de coordenadas absolutas del navio en el tablero.
	 */
	public Set<Coordinate> getAbsolutePositions() { return getAbsolutePositions(position); }

	/**
	 * Simula un disparo en el shape del navio y comprueba si es una posición a golpear.
	 *
	 * @param c Coordenada del tablero a disparar.
	 * @return true, si se alcanza una posicion del navio.
	 * @throws CoordinateAlreadyHitException Si se golpea a una posicion ya alcanzada.
	 */
	public boolean hit(Coordinate c) throws CoordinateAlreadyHitException {
		
		int pos = getShapeIndex(getRelativePosition(c));
		
		if(shape[orientation.ordinal()][pos] == CRAFT_VALUE) {
			shape[orientation.ordinal()][pos] = HIT_VALUE;
			return true;
			
		} else if(shape[orientation.ordinal()][pos] == HIT_VALUE)
			throw new CoordinateAlreadyHitException(c);
		return false;
		
	}

	/**
	 * Comprueba si el navio ya tiene todas sus posiciones alcanzadas.
	 *
	 * @return true, si esta destruido.
	 */
	public boolean isShotDown() {
		
		for(int i=0; i < BOUNDING_SQUARE_SIZE*BOUNDING_SQUARE_SIZE ; i++) { //Recorre todos los valores de shape
			
			if(shape[orientation.ordinal()][i] == CRAFT_VALUE) { //Si una coordenada tiene el valor de barco, aun no ha caido
				return false;
			}
		}
		return true;
		
	}

	/**
	 * Comprueba si la coordenada contiene una posicion del navio golpeada.
	 *
	 * @param c Coordenada absoluta a comprobar.
	 * @return true, si la coordenada fue golpeada.
	 */
	public boolean isHit(Coordinate c) {
		
		int pos = getShapeIndex(getRelativePosition(c));
		if(shape[orientation.ordinal()][pos] == HIT_VALUE)
			return true;
		return false;
	}

	/**
	 * To string. Devuelve el nombre, la orientacion y el dibujo del navio.
	 *
	 * @return Representación del navio.
	 */
	public String toString() {
		String dibujo = name + " (" + getOrientation()+")\n ";
		for (int i=0; i < BOUNDING_SQUARE_SIZE; i++) {
			dibujo += "-";
		}
		
		dibujo += "\n";
		for(int i=0; i < BOUNDING_SQUARE_SIZE; i++) {
			dibujo += "|";
			for(int j=0; j < BOUNDING_SQUARE_SIZE; j++) {
				if(shape[orientation.ordinal()][i*BOUNDING_SQUARE_SIZE+j] == CRAFT_VALUE) {
					dibujo += symbol;
				} else if (shape[orientation.ordinal()][i*BOUNDING_SQUARE_SIZE+j] == HIT_VALUE) {
					dibujo += Board.HIT_SYMBOL;
				} else {
					dibujo += Board.WATER_SYMBOL;
				}
				
			}
			dibujo += "|\n";
		}
		
		dibujo += " ";
		
		for (int i=0; i < BOUNDING_SQUARE_SIZE; i++) {
			dibujo += "-";
		}
		
		return dibujo;
	}

}
package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import model.Coordinate;
import model.ship.*;
import model.exceptions.*;

public abstract class Craft {

	/** The Constant BOUNDING_SQUARE_SIZE. */
	private static final int BOUNDING_SQUARE_SIZE = 5;
	/** The Constant HIT_VALUE. */
	private static final int HIT_VALUE = -1;
	/** The Constant CRAFT_VALUE. */
	private static final int CRAFT_VALUE = 1;
	
	/** The orientation. */
	private Orientation orientation;
	/** The position. */
	private Coordinate position;
	
	/** The symbol. */
	private char symbol;
	/** The name. */
	private String name;
	/** The shape. */
	protected int shape[][];
	          
	public Craft(Orientation orient, char sym, String nom) {
		orientation = orient;
		symbol = sym;
		name = nom;
	  	position = null;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Coordinate getPosition() {
		if(position!=null) 
			return position.copy();
		
			
		return null;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Coordinate posicion) { this.position = posicion.copy(); }

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() { return name; }

	/**
	 * Gets the orientation.
	 *
	 * @return the orientation
	 */
	public Orientation getOrientation() { return orientation; }

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() { return symbol; }

	/**
	 * Gets the shape.
	 *
	 * @return the shape
	 */
	public int [][] getShape() { return shape; }

	/**
	 * Gets the relative position. Método privado añadido que recibe una coordenada absoluta y lo transforma
	 * en la coordenada relativa del ship usando su posicion
	 *
	 * @param c the c
	 * @return the relative position
	 */
	private Coordinate getRelativePosition(Coordinate c) {
		Coordinate cRel = new Coordinate2D (c.get(0) - position.get(0), c.get(1) - position.get(1));
		
		return cRel;
	}

	/**
	 * Gets the shape index.
	 *
	 * @param c the c
	 * @return the shape index
	 */
	public int getShapeIndex(Coordinate c) {
		Objects.requireNonNull(c);
		return (c.get(1)*(BOUNDING_SQUARE_SIZE))+c.get(0);
	
	}

	/**
	 * Gets the absolute positions. Devuelve una lista con todas las posiciones absolutas del ship, sean o no
	 * alcanzadas. Las coordenadas absolutas son las que se encuentran en el board
	 *
	 * @param c the c
	 * @return the absolute positions
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate c) {
		Objects.requireNonNull(c);
		Set<Coordinate> positions = new HashSet<Coordinate>();
		for(int i=0; i < BOUNDING_SQUARE_SIZE*BOUNDING_SQUARE_SIZE ; i++) { //Recorre todos los valores de shape
				
			//Si una coordenada tiene el valor de craft o del hit, lo guarda
			if(shape[orientation.ordinal()][i] == CRAFT_VALUE || shape[orientation.ordinal()][i]==HIT_VALUE) { 
				/*int coordX = c.get(0) + i % BOUNDING_SQUARE_SIZE;
				int coordY = c.get(1) + i / BOUNDING_SQUARE_SIZE;
				positions.add(new Coordinate2D(coordX, coordY));*/
				positions.add(c.add(new Coordinate2D(i % BOUNDING_SQUARE_SIZE, i / BOUNDING_SQUARE_SIZE)));
			}
		}
		
		
		return positions;
	}

	/**
	 * Gets the absolute positions.
	 *
	 * @return the absolute positions
	 */
	public Set<Coordinate> getAbsolutePositions() { return getAbsolutePositions(position); }

	/**
	 * Hit. Imita el choque de un disparo. Mira si hay la posicion del ship no está golpeada para simular el golpe y cambiarle el
	 * estado a hit. Devuelve true si ha habido un choque o false si no 
	 *
	 * @param c the c
	 * @return true, if successful
	 * @throws CoordinateAlreadyHitException 
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
	 * Checks if is shot down.
	 *
	 * @return true, if is shot down
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
	 * Checks if is hit. Comprueba si la coordenada contiene una posicion del ship
	 * golpeado o si no fue golpeado o no hay una posicion del ship ahí.
	 *
	 * @param c the c
	 * @return true, if is hit
	 */
	public boolean isHit(Coordinate c) {
		
		int pos = getShapeIndex(getRelativePosition(c));
		if(shape[orientation.ordinal()][pos] == HIT_VALUE)
			return true;
		return false;
	}

	/**
	 * To string. Devuelve el nombre, la orientacion y el dibujo del ship.
	 *
	 * @return the string
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
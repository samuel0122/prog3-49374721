/**
 * @author Samuel Oliva Bulpitt
 */

package model.exceptions;

import model.Coordinate;

/**
 * Clase BattleshipException.
 */
@SuppressWarnings("serial")
public abstract class BattleshipException extends java.lang.Exception {
	
	/** Coordinate c. */
	private Coordinate c;
	
	/**
	 * Constructor de BattleshipExceptions que guarda la coordenada.
	 *
	 * @param c the c
	 */
	public BattleshipException(Coordinate c) {
		this.c = c;
	}
	
	/**
	 * Get message que devuelve la coordenada guardada.
	 *
	 * @return String coordenadaDeError
	 */
	public String getMessage() {
		return "Error con la coordenada "+c.toString()+":";
	}
	
}

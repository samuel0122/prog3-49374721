/**
 * @author Samuel Oliva Bulpitt
 */

package model.exceptions;

import model.Coordinate;

/**
 * Clase CoordinateException, subclase de BattleshipException.
 */
@SuppressWarnings("serial")
public abstract class CoordinateException extends BattleshipException {
	
	/** Coordinate c. */
	private Coordinate c;
	
	/**
	 * Constructor de BattleshipExceptions que guarda la coordenada.
	 *
	 * @param c the c
	 */
	public CoordinateException(Coordinate c) {
		this.c = c;
	}
	
	/**
	 * Get message que devuelve la coordenada guardada.
	 *
	 * @return String coordenadaDeError
	 */
	public String getMessage() {
		return "Error con la coordenada "+c.toString()+": ";
	}
	
}

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
	
	/** Coordenada que provoca el error. */
	private Coordinate c;
	
	/**
	 * Constructor de BattleshipExceptions que guarda la coordenada.
	 *
	 * @param c Coordenada que provoca el error.
	 */
	public CoordinateException(Coordinate c) {
		this.c = c;
	}
	
	/**
	 * Obtiene la coordenada guardada.
	 *
	 * @return Coordenada de error.
	 */
	public String getMessage() {
		return "Error con la coordenada "+c.toString()+": ";
	}
	
}

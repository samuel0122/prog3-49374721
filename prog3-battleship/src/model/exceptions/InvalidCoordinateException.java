/**
 * @author Samuel Oliva Bulpitt
 */

package model.exceptions;

import model.Coordinate;

/**
 * Clase InvalidCoordinateException, subclase de BattleshipException.
 */
@SuppressWarnings("serial")
public class InvalidCoordinateException extends BattleshipException {

	/**
	 * Constructor de InvalidCoordinateException que guarda la coordenada en su superclase.
	 *
	 * @param c the c
	 */
	public InvalidCoordinateException(Coordinate c) {
		super(c);
	}

	/**
	 * String de mensaje de error con la coordenada que lo provoca.
	 *
	 * @return String mensajeDeError
	 */
	public String getMessage() {
		return super.getMessage() +" coordenada no valida porque está fuera de los límites del tablero.";
	}
	
}

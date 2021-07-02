/**
 * @author Samuel Oliva Bulpitt
 */

package model.exceptions;

import model.Coordinate;

/**
 * Clase InvalidCoordinateException, subclase de CoordinateException.
 */
@SuppressWarnings("serial")
public class InvalidCoordinateException extends CoordinateException {

	/**
	 * Constructor de InvalidCoordinateException que guarda la coordenada.
	 *
	 * @param c Coordenada que provoca el error.
	 */
	public InvalidCoordinateException(Coordinate c) {
		super(c);
	}

	/**
	 * Obtiene el mensaje de error con la coordenada que lo provoca.
	 *
	 * @return Mensaje de error.
	 */
	public String getMessage() {
		return super.getMessage() +"coordenada no valida porque está fuera de los límites del tablero.";
	}
	
}

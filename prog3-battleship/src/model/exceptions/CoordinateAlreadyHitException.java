/**
 * @author Samuel Oliva Bulpitt
 */

package model.exceptions;


import model.Coordinate;

/**
 * Clase CoordinateAlreadyHitException, subclase de CoordinateException.
 */
@SuppressWarnings("serial")
public class CoordinateAlreadyHitException extends CoordinateException {

	/**
	 * Constructor de CoordinateAlreadyHitException que guarda la coordenada.
	 *
	 * @param c Coordenada de error.
	 */
	public CoordinateAlreadyHitException(Coordinate c) {
		super(c);
	}
	
	/**
	 * Obtiene el mensaje de error con la coordenada que lo provoca.
	 *
	 * @return Mensaje de error.
	 */
	public String getMessage() {
		return super.getMessage() +"coordenada no valida porque ya fue golpeada previamente.";
	}

}

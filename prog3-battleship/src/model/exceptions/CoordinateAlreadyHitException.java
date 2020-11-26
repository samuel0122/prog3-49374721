/**
 * @author Samuel Oliva Bulpitt
 */

package model.exceptions;


import model.Coordinate;

/**
 * Clase CoordinateAlreadyHitException, subclase de BattleshipException.
 */
@SuppressWarnings("serial")
public class CoordinateAlreadyHitException extends CoordinateException {

	/**
	 * Constructor de CoordinateAlreadyHitException que guarda la coordenada en su superclase.
	 *
	 * @param c the c
	 */
	public CoordinateAlreadyHitException(Coordinate c) {
		super(c);
	}
	
	/**
	 * String de mensaje de error con la coordenada que lo provoca.
	 *
	 * @return String mensajeDeError
	 */
	public String getMessage() {
		return super.getMessage() +"coordenada no valida porque ya fue golpeada previamente.";
	}

}

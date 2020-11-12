/**
 * @author Samuel Oliva Bulpitt
 */

package model.exceptions;

import model.Coordinate;

/**
 * Clase OccupiedCoordinateException, subclase de BattleshipException.
 */
@SuppressWarnings("serial")
public class OccupiedCoordinateException extends BattleshipException {

	/**
	 * Constructor de OccupiedCoordinateException que guarda la coordenada en su superclase.
	 *
	 * @param c the c
	 */
	public OccupiedCoordinateException(Coordinate c) {
		super(c);
	}
	
	/**
	 * String de mensaje de error con la coordenada que lo provoca.
	 *
	 * @return String mensajeDeError
	 */
	public String getMessage() {
		return super.getMessage() +" coordenada no valida porque tiene una posición ya ocupada.";
	}

}

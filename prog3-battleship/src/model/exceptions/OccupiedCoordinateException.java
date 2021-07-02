/**
 * @author Samuel Oliva Bulpitt
 */

package model.exceptions;

import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * Clase OccupiedCoordinateException, subclase de CoordinateException.
 */
@SuppressWarnings("serial")
public class OccupiedCoordinateException extends CoordinateException {

	/**
	 * Constructor de OccupiedCoordinateException que guarda la coordenada.
	 *
	 * @param c Coordenada que provoca el error.
	 */
	public OccupiedCoordinateException(Coordinate c) {
		super(c);
	}
	
	/**
	 * Obtiene el mensaje de error con la coordenada que lo provoca.
	 *
	 * @return Mensaje de error.
	 */
	public String getMessage() {
		return super.getMessage() +"coordenada no valida porque tiene una posición ya ocupada.";
	}

}

/**
 * @author Samuel Oliva Bulpitt
 */

package model.exceptions;

import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * Clase NextToAnotherCraftException, subclase de CoordinateException.
 */
@SuppressWarnings("serial")
public class NextToAnotherCraftException extends CoordinateException {
	
	/**
	 * Constructor de NextToAnotherCraftException que guarda la coordenada.
	 *
	 * @param c Coordenada que provoca el error.
	 */
	public NextToAnotherCraftException (Coordinate c) {
		super(c);
	}
	
	/**
	 * Obtiene el mensaje de error con la coordenada que lo provoca.
	 *
	 * @return Mensaje de error.
	 */
	public String getMessage() {
		return super.getMessage() +"coordenada no valida porque no respeta la vecindad de otro navio.";
	}
	
}

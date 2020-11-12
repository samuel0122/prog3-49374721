/**
 * @author Samuel Oliva Bulpitt
 */

package model.exceptions;

import model.Coordinate;

/**
 * Clase NextToAnotherCraftException, subclase de BattleshipException.
 */
@SuppressWarnings("serial")
public class NextToAnotherCraftException extends BattleshipException {
	
	/**
	 * Constructor de NextToAnotherCraftException que guarda la coordenada en su superclase.
	 *
	 * @param c the c
	 */
	public NextToAnotherCraftException (Coordinate c) {
		super(c);
	}
	
	/**
	 * String de mensaje de error con la coordenada que lo provoca.
	 *
	 * @return String mensajeDeError
	 */
	public String getMessage() {
		return super.getMessage() +" coordenada no valida porque no respeta la vecindad de otro navio.";
	}
	
}

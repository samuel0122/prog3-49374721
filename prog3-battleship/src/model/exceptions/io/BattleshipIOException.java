/**
 * @author Samuel Oliva Bulpitt
 */
package model.exceptions.io;

import model.exceptions.BattleshipException;

/**
 * Clase BattleshipIOException, subclase de BattleshipException.
 */
@SuppressWarnings("serial")
public class BattleshipIOException extends BattleshipException {
	
	/** 
	 * Mensaje de error.
	 */
	private String mesage;
	
	/**
	 * Constructor BattleshipIOException.
	 * 
	 * @param m Mensaje de error a almacenar.
	 */
	public BattleshipIOException(String m) {
		this.mesage=m;
	}
	
	/**
	 * Obtiene el mensaje de error.
	 * 
	 * @return Mensaje de error.
	 */
	public String getMessage() { return this.mesage; }
}

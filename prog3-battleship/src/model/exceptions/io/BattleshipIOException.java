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
	 * String mensage, almacena el mensaje pasado previamente.
	 */
	private String mesage;
	
	/**
	 * Constructor BattleshipIOException, recibe el mensaje a guardar.
	 * @param String mensaje
	 */
	public BattleshipIOException(String m) {
		this.mesage=m;
	}
	
	/**
	 * Getter de BattleshipIOException.
	 * @return String mensaje
	 */
	public String getMessage() { return this.mesage; }
}

/**
 * @author Samuel Oliva Bulpitt
 */
package model.exceptions.score;

import model.exceptions.BattleshipException;

/**
 * Clase EmptyRankingException, subclase de BattleshipException.
 */
public class EmptyRankingException extends BattleshipException {
	
	/**
	 * Contructor de EmptyRankingException.
	 */
	public EmptyRankingException() {}

	/**
	 * Obtiene el mensaje de error.
	 * 
	 * @return Mensaje de error.
	 */
	public String getMessage() { return "Error: el conjunto del ranking está vacío."; }
}

/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.aircraft;

import model.Orientation;

/**
 * Clase Aircraft, subclase de Craft
 */
public abstract class Aircraft extends model.Craft {
	
	/**
	 * Constructor de la clase Aircraft. Llama al constructor de Craft con los parametros pasados.
	 *
	 * @param o the o
	 * @param s the s
	 * @param n the n
	 */
	public Aircraft(Orientation o, char s, String n) {
		super(o, s, n);
	}

}

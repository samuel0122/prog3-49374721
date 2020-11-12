/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.ship;

import model.Orientation;

/**
 * Clase Ship, subclase de Craft.
 */
public abstract class Ship extends model.Craft {
	
	/**
	 * Constructor de Ship. Llama al constructor de Craft con los parametros pasados.
	 *
	 * @param o the o
	 * @param s the s
	 * @param n the n
	 */
	public Ship(Orientation o, char s, String n) {
		super(o, s, n);
	}
}

/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.aircraft;

import model.Orientation;

/**
 * Clase de las aviones.
 */
public abstract class Aircraft extends model.Craft {
	
	/**
	 * Constructor de la clase Aircraft.
	 *
	 * @param o Orientacion del avion.
	 * @param s Simbolo del avion.
	 * @param n Nombre del avion.
	 */
	public Aircraft(Orientation o, char s, String n) {
		super(o, s, n);
	}

}

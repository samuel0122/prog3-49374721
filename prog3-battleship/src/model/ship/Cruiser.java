/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.ship;

import model.Orientation;

/**
 * Clase Cruiser, subclase de Ship. Tipo de barco con 3 posiciones.
 */
public class Cruiser extends Ship {

	/**
	 * Constructor de la clase Cruiser. Llama al constructor de Ship con los datos y define su shape
	 *
	 * @param o the o
	 */
	public Cruiser(Orientation o) {
		super(o, 'Ø', "Cruiser");
		
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,	
			0, 1, 1, 1, 0,	
			0, 0, 0, 0, 0,
			0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
			0, 0, 1, 0, 0,	
			0, 0, 1, 0, 0,	
			0, 0, 1, 0, 0,
			0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,	
			0, 1, 1, 1, 0,	
			0, 0, 0, 0, 0,
			0, 0, 0, 0, 0}}; 
	}

}

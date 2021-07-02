/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.ship;

import model.Orientation;

/**
 * Barco con 5 posiciones.
 */
public class Carrier extends Ship {

	/**
	 * Constructor de la clase Carrier. Llama al constructor de Ship con los datos y define su shape
	 *
	 * @param o Orientacion del barco.
	 */
	public Carrier(Orientation o) {
		super(o, '®', "Carrier");
		
		shape = new int[][] {
		      { 0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
		        0, 0, 0, 0, 0,	
			1, 1, 1, 1, 1,	
			0, 0, 0, 0, 0,
			0, 0, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
			0, 0, 1, 0, 0,	
			0, 0, 1, 0, 0,	
			0, 0, 1, 0, 0,
			0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,	
			1, 1, 1, 1, 1,	
			0, 0, 0, 0, 0,
			0, 0, 0, 0, 0}}; 
	}

	/**
	 * Getter del valor del carrier.
	 * 
	 * @return Valor por destruir el Carrier.
	 */
	@Override
	public int getValue() {
		return 8;
	}
}

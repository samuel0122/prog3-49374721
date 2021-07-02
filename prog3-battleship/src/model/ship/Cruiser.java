/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.ship;

import model.Orientation;

/**
 * Barco con 3 posiciones.
 */
public class Cruiser extends Ship {

	/**
	 * Constructor de la clase Cruiser. Llama al constructor de Ship con los datos y define su shape
	 *
	 * @param o Orientacion del barco.
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

	/**
	 * Getter del valor del cruiser.
	 * 
	 * @return Valor por destruir el Cruiser.
	 */
	@Override
	public int getValue() {
		return 5;
	}
}

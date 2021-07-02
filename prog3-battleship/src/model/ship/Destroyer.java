/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.ship;

import model.Orientation;

/**
 * Barco con 2 posiciones.
 */
public class Destroyer extends Ship {

	/**
	 * Constructor de la clase Destroyer. 
	 *
	 * @param o Orientacion del barco.
	 */
	public Destroyer(Orientation o) {
		super(o, 'Ω', "Destroyer");
		
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,	
			0, 1, 1, 0, 0,	
			0, 0, 0, 0, 0,
			0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
			0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,	
			0, 1, 1, 0, 0,	
			0, 0, 0, 0, 0,
			0, 0, 0, 0, 0}};
	}
	
	/**
	 * Getter del valor del destroyer.
	 * 
	 * @return Valor por destruir el Destroyer.
	 */
	@Override
	public int getValue() {
		return 3;
	}

}

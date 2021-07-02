/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.aircraft;

import model.Orientation;


/**
 * Clase Bomber, subclase de Aircraft.
 */
public class Bomber extends Aircraft {

	/**
	 * Constructor de la clase Bomber.
	 *
	 * @param o Orientacion del avion.
	 */
	
	public Bomber (Orientation o) {
		super(o, '⇶', "Bomber");
		
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	1, 1, 1, 1, 1,	
		    	1, 0, 1, 0, 1,
		    	0, 0, 1, 0, 0},
		      { 	0, 1, 1, 0, 0,
		    		0, 0, 1, 0, 0,	
		    		1, 1, 1, 1, 0,	
		    		0, 0, 1, 0, 0,
		    		0, 1, 1, 0, 0},
		      { 0, 0, 1, 0, 0,
		    	1, 0, 1, 0, 1,	
		    	1, 1, 1, 1, 1,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 1, 1, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 1,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 1, 0}};
	}

	/**
	 * Getter del valor del navio.
	 * 
	 * @return Puntos por destruir el Bomber.
	 */
	@Override
	public int getValue() {
		return 15;
	}

}

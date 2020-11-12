/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.aircraft;

import model.Orientation;


/**
 * Clase Bomber, subclase de Aircraft
 */
public class Bomber extends Aircraft {

	/**
	 * Constructor de la clase Bomber. Llama al constructor de Aircraft con los datos y define su shape
	 *
	 * @param o the o
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

}

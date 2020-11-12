/**
  	@author Samuel Oliva Bulpitt
 
 */


package model.aircraft;

import model.Orientation;

/**
 * Clase Fighter, subclase de Aircraft.
 */
public class Fighter extends Aircraft {

	/**
	 * Constructor de la clase Fighter. Llama al constructor de Aircraft con los datos y define su shape
	 *
	 * @param o the o
	 */
	public Fighter (Orientation o) {
		super(o, '⇄', "Fighter");
		
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
			0, 0, 1, 0, 0,	
			1, 1, 1, 1, 0,	
			0, 0, 1, 0, 0,
			0, 0, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
			0, 0, 1, 0, 0,	
			0, 1, 1, 1, 0,	
			0, 0, 1, 0, 0,
			0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
			0, 0, 1, 0, 0,	
			0, 1, 1, 1, 1,	
			0, 0, 1, 0, 0,
			0, 0, 0, 0, 0}};
	}


}

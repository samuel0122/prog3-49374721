/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.ship;

import model.Orientation;

/**
 * Clase Battleship, subclase de Ship. Tipo de barco con 4 posiciones.
 */
public class Battleship extends Ship {

	/**
	 * Constructor de la clase Battleship. Llama al constructor de Ship con los datos y define su shape
	 *
	 * @param o the o
	 */
	public Battleship(Orientation o) {
		super(o, 'O', "Battleship");
		
		shape = new int[][] {
		    { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		   	{ 0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0,	
		    	0, 1, 1, 1, 1,	
		    	0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0},
		   	{ 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		    { 0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0,	
		    	0, 1, 1, 1, 1,	
		    	0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0}}; 
	}
	
	
}

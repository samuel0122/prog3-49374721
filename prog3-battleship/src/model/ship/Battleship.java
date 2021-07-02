/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.ship;

import model.Orientation;

/**
 * Barco con 4 posiciones.
 */
public class Battleship extends Ship {

	/**
	 * Constructor de la clase Battleship. Llama al constructor de Ship con los datos y define su shape
	 *
	 * @param o Orientacion del barco.
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
	
	/**
	 * Getter del valor del battleship.
	 *
	 * @return Valor por destruir el Battleship.
	 */
	@Override
	public int getValue() {
		return 6;
	}
	
}

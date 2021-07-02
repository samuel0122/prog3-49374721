/**
  	@author Samuel Oliva Bulpitt
 
 */


package model.aircraft;

import model.Orientation;

/**
 * Clase Transport, subclase de Aircraft.
 */
public class Transport extends Aircraft {

	/**
	 * Constructor de la clase Transport.
	 * 
	 * @param o Orientacion del avion.
	 */
	public Transport (Orientation o) {
		super(o, '⇋', "Transport");
		
		shape = new int[][] {
		      { 0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 0,	
		    	1, 0, 1, 0, 1,
		    	0, 0, 1, 0, 0},
		      { 0, 1, 0, 0, 0,
			0, 0, 1, 0, 0,	
			1, 1, 1, 1, 1,	
			0, 0, 1, 0, 0,
			0, 1, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
			1, 0, 1, 0, 1,	
			0, 1, 1, 1, 0,	
			0, 0, 1, 0, 0,
			0, 0, 1, 0, 0},
		      { 0, 0, 0, 1, 0,
			0, 0, 1, 0, 0,	
			1, 1, 1, 1, 1,	
			0, 0, 1, 0, 0,
			0, 0, 0, 1, 0}}; 
	}
	
	/**
	 * Getter del valor del navio.
	 * 
	 * @return Puntos por destruir el Transport.
	 */
	@Override
	public int getValue() {
		return 18;
	}

}

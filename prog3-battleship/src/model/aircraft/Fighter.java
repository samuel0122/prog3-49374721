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
	 * Constructor de la clase Fighter. 
	 * 
	 * @param o Orientacion del avion.
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

	/**
	 * Getter del valor del navio.
	 * 
	 * @return Puntos por destruir el Fighter.
	 */
	@Override
	public int getValue() {
		return 10;
	}


}

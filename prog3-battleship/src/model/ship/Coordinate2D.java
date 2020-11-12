/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.ship;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;

/**
 * Clase Coordinate2D, subclase de Coordinate
 */
public class Coordinate2D extends model.Coordinate {

	/**
	 * Constructor de la clase Coordinate2D. Inicialica un Coordinate con dimension 2 y establece los componentes
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Coordinate2D(int x, int y) {
		super(2);
		super.set(0, x);
		super.set(1, y);
	}
	
	/**
	 * Constructor copia de Coordinate2D.
	 *
	 * @param c the c
	 */
	public Coordinate2D(Coordinate2D c) {
		super(c);
	}

	/**
	 * adjacentCoordinates de Coordinate2D. Crea una lista de las coordenadas 2D adjacentes. Es la implementacion del metodo abstract de su superclase Coordinate
	 * @return Set Coordinate2D 8coordenadasAdjacentes
	 */
	@Override
	public Set<Coordinate> adjacentCoordinates() {
		Set<Coordinate> adyacentes = new HashSet<Coordinate>();
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) { 
				if(i!=1 || j!=1) {
					
					int newCoord []= {get(0)-1+i,get(1)-1+j};
					
					adyacentes.add(CoordinateFactory.createCoordinate(newCoord));
				}
			}
		}
		return adyacentes;
	}

	/**
	 * copy de Coordinate2D. Devuelve una copia de la Coordenada 2D. Es la implementacion del metodo abstract de su superclase Coordinate
	 * @return Coordinate2D copiaCoord
	 */
	@Override
	public Coordinate copy() {
		return new Coordinate2D(this.get(0), this.get(1));
	}
	
	/**
	 * toString de Coordinate2D. Crea un string con los componentes de la coordenada
	 * @return String coordenada
	 */
	public String toString() {
		 return "("+get(0)+", "+get(1)+")";
	};

}

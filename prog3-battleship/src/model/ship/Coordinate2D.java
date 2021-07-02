/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.ship;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;

/**
 * Coordenadas 2D.
 */
public class Coordinate2D extends model.Coordinate {

	/**
	 * Constructor de Coordinate2D.
	 *
	 * @param x Dimension x.
	 * @param y Dimension y.
	 */
	public Coordinate2D(int x, int y) {
		super(2);
		super.set(0, x);
		super.set(1, y);
	}
	
	/**
	 * Constructor copia de Coordinate2D.
	 *
	 * @param c Coordenada2D.
	 */
	public Coordinate2D(Coordinate2D c) {
		super(c);
	}

	/**
	 * Obtiene las coordenadas 2D adyacentes.
	 * 
	 * @return Conjunto de coordenadas 2D adyacentes.
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
	 * Obtiene una copia de la coordenada 2D.
	 * 
	 * @return Copia de la coordenada 2D actual.
	 */
	@Override
	public Coordinate copy() {
		return new Coordinate2D(this.get(0), this.get(1));
	}
	
	/**
	 * Obtiene los componentes de la coordenada 2D.
	 * 
	 * @return Componentes de la coordenada 2D.
	 */
	public String toString() {
		 return "("+get(0)+", "+get(1)+")";
	};

}

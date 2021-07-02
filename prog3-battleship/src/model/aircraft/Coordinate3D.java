/**
  	@author Samuel Oliva Bulpitt

 */

package model.aircraft;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;

/**
	Coordenadas 3D.
 */
public class Coordinate3D extends model.Coordinate {

	/**
	 * Constructor de Coordinate3D.
	 *
	 * @param x Dimension x.
	 * @param y Dimension y.
	 * @param z Dimension z.
	 */
	public Coordinate3D(int x, int y, int z) {
		super(3);
		super.set(0, x);
		super.set(1, y);
		super.set(2, z);
	}
	
	/**
	 * Constructor copia de Coordinate3D.
	 *
	 * @param c Coordenada3D.
	 */
	public Coordinate3D(Coordinate c) {
		
		super(c);
	}

	/**
	 * Obtiene las coordenadas 3D adyacentes.
	 * 
	 * @return Conjunto de coordenadas 3D adyacentes.
	 */
	@Override
	public Set<Coordinate> adjacentCoordinates() {
		Set<Coordinate> adyacentes = new HashSet<Coordinate>();
		
		for(int i=-1; i<2; i++) {
			for(int j=-1; j<2; j++) { 
				for(int k=-1; k<2; k++) {
					if(i!=0 || j!=0 || k!=0) {
						
						int newCoord[] = { get(0)+i, get(1)+j, get(2)+k};
						
						adyacentes.add(CoordinateFactory.createCoordinate(newCoord));
						
					}
				}
			}
		}
		return adyacentes;
	}

	/**
	 * Obtiene una copia la coordenada 3D.
	 * 
	 * @return Copia de la coordenada 3D actual.
	 */
	@Override
	public Coordinate copy() {
		return new Coordinate3D(this.get(0), this.get(1), this.get(2));
	}
	
	/**
	 * Obtiene los componentes de la coordenada 3D.
	 * 
	 * @return Componentes de la coordenada 3D.
	 */
	public String toString() {
		 return "("+get(0)+", "+get(1)+", "+get(2)+")";
	};

}

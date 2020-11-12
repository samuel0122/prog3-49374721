/**
  	@author Samuel Oliva Bulpitt

 */

package model.aircraft;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;

/**
	Clase Coordinate3D, subclase de Coordinate
 */
public class Coordinate3D extends model.Coordinate {

	/**
	 * Constructor de la clase Coordinate3D. Inicialica un Coordinate con dimension 3 y establece los componentes
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
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
	 * @param c the c
	 */
	public Coordinate3D(Coordinate c) {
		
		super(c);
	}

	/**
	 * adjacentCoordinates de Coordinate3D. Crea una lista de las coordenadas 3D adjacentes. Es la implementacion del metodo abstract de su superclase Coordinate
	 * @return Set Coordinate3D 26coordenadasAdjacentes
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
	 * copy de Coordinate3D. Devuelve una copia de la Coordenada 3D. Es la implementacion del metodo abstract de su superclase Coordinate
	 * @return Coordinate3D copiaCoord
	 */
	@Override
	public Coordinate copy() {
		return new Coordinate3D(this.get(0), this.get(1), this.get(2));
	}
	
	/**
	 * toString de Coordinate3D. Crea un string con los componentes de la coordenada
	 * @return String coordenada
	 */
	public String toString() {
		 return "("+get(0)+", "+get(1)+", "+get(2)+")";
	};

}

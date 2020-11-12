/**
  	@author Samuel Oliva Bulpitt
 
 */

package model;

import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;

/**
 * Un factory para crear objetos Coordinate.
 */
public class CoordinateFactory {
	
	/**
	 * Create Coordinate. Crea un Coordinate2D o Coordinate 3D dependiendo de cuantos enteros recibe como parametro.
	 *
	 * @param coords the coords
	 * @return Coordinate2D, Coordinate 3D
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public static Coordinate createCoordinate(int ... coords) {
		if(coords.length==2){
			return new Coordinate2D(coords[0], coords[1]);
		} else if (coords.length==3) {
			return new Coordinate3D(coords[0], coords[1], coords[2]);
		} else {
			throw new IllegalArgumentException( "ERROR: CoordinatFactory; incorrecta cantidad de argumentos.");
		}
	}
}

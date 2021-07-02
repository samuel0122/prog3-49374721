/**
  	@author Samuel Oliva Bulpitt
 
 */

package model;

import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;

/**
 * Factorias de coordenadas.
 */
public class CoordinateFactory {
	
	/**
	 * Crea una coordenada 2D o 3D cuando se introducen 2 o 3 argumentos, respectivamente.
	 *
	 * @param coords Componentes de la coordenada a crear.
	 * @return Coordenada de las dimensiones especificadas.
	 * @throws IllegalArgumentException si se intenta crear una coordenada de dimensiones no soportadas.
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

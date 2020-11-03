package model;

import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;

public class CoordinateFactory {
	public static Coordinate createCoordinate(int[] coords) {
		if(coords.length==2){
			//System.out.println("Es 2D.");
			return new Coordinate2D(coords[0], coords[1]);
		} else if (coords.length==3) {
			//System.out.println("Es 3D.");
			return new Coordinate3D(coords[0], coords[1], coords[2]);
		} else {
			throw new IllegalArgumentException( "ERROR: CoordinatFactory; incorrecta cantidad de argumentos.");
		}
	}
}

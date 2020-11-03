package model.ship;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;

public class Coordinate2D extends model.Coordinate {

	public Coordinate2D(int x, int y) {
		super(2);
		super.set(0, x);
		super.set(1, y);
	}
	
	public Coordinate2D(Coordinate2D c) {
		super(c);
	}

	@Override
	public Set<Coordinate> adjacentCoordinates() {
		Set<Coordinate> adyacentes = new HashSet<Coordinate>();
		int newCoord []= {0,0};
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) { 
				if(i!=1 || j!=1) {
					
					newCoord[0] = get(0)-1+j;
					newCoord[1] = get(1)-1+i;
					
					adyacentes.add(CoordinateFactory.createCoordinate(newCoord));
				}
			}
		}
		return adyacentes;
	}

	@Override
	public Coordinate copy() {
		return new Coordinate2D(this.get(0), this.get(1));
	}
	
	public String toString() {
		 return "("+get(0)+", "+get(1)+")";
	};

}

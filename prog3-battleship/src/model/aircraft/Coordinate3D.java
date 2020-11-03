package model.aircraft;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;

public class Coordinate3D extends model.Coordinate {

	public Coordinate3D(int x, int y, int z) {
		super(3);
		super.set(0, x);
		super.set(1, y);
		super.set(2, z);
	}
	
	public Coordinate3D(Coordinate c) {
		
		super(c);
	}

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

	@Override
	public Coordinate copy() {
		return new Coordinate3D(this.get(0), this.get(1), this.get(2));
	}
	
	public String toString() {
		 return "("+get(0)+", "+get(1)+", "+get(2)+")";
	};

}

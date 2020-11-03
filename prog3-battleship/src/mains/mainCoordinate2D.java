package mains;

import model.ship.*;
import model.*;

import java.util.Set;

import model.Coordinate;
import model.aircraft.Coordinate3D;
import model.exceptions.BattleshipException;

public class mainCoordinate2D {

	public static void main(String[] args) throws BattleshipException {
		Coordinate2D c1 = new Coordinate2D (1,2);
		System.out.println(c1.copy().toString());
		
		
		Coordinate3D c2 = new Coordinate3D (3, 4,2);
		System.out.println(c2.copy().toString());
		
		Coordinate3D c3 = new Coordinate3D (3, 4,2);
		System.out.println(c3.copy().toString());
		/*
		System.out.println("ADD y SUBTRACT");
		System.out.println(c3.add(c2));
		System.out.println(c3.add(c1));
		System.out.println(c3.subtract(c2));
		System.out.println(c3.subtract(c1));
		*/
		Set<Coordinate> cs = c3.adjacentCoordinates();
		int i = 1;
		System.out.println("Coordinate: "+c3);
		for(Coordinate cd : cs) {
			System.out.println(i+": "+cd.toString());
			i++;
		}
		
		Craft nave1 = new Battleship(Orientation.NORTH);
		Board tabla = new Board2D(19);
		tabla.addCraft(nave1, new Coordinate2D(0,0));

		/*System.out.println(nave1.toString());
		System.out.println(tabla.show(false));*/
		
	}

}

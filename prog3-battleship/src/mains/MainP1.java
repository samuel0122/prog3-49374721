package mains;

import java.util.ArrayList;

import model.Coordinate;

public class MainP1 {
	
	//Programa para probar la clase coordinate
	
	public static void main(String[] args) {
		
		//Creamos coordenadas
		
        Coordinate c1 = new Coordinate(0,0);
	    Coordinate c2 = new Coordinate(10,10);
	    Coordinate c3 = new Coordinate(4,3);
	    Coordinate c4 = new Coordinate(5,15);
	    Coordinate c5 = new Coordinate(c4);
	    Coordinate c6 = new Coordinate(2,5);

	    //Imprimimos las coordenadas
	    
	    System.out.println(c1);
	    System.out.println(c2);
	    System.out.println(c3);
	    System.out.println(c4);
	    System.out.println(c5);
	    System.out.println(c6);


	    //Imprimimos suma de coordenadas 3 y 6
	    
        Coordinate sumada = c3.add(c6);
	    System.out.println(c3+"+"+c6+"="+sumada);

	    //Creamos array de coordenadas
	    
        Coordinate[] v = new Coordinate[5];
	    for (int i=0; i<5; i++) {
	        v[i]= new Coordinate(i,4-i);
	    }
	    
	    //Imprimimos el array de coordenadas
	    
	    for (int i=0; i<5; i++) {
	        System.out.println(v[i].get(0)+","+v[i].get(1));
	    }

	    //Creamos una lista de coordenadas y lo imprimimos
	    
	    ArrayList<Coordinate> v2 = new ArrayList<Coordinate>();
	    for (int i=0; i<8; i++) {
	                v2.add(new Coordinate(i, i));
	                System.out.println(v2.get(i));
	    }
	}
}

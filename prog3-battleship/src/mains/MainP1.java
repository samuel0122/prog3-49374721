package mains;

import java.util.ArrayList;

import model.*;

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

	    Coordinate c7 = new Coordinate (c2);
	    Coordinate c8 = new Coordinate (c3);
	    Coordinate c9 = new Coordinate (c4);
	    //Imprimimos las coordenadas
	    		System.out.println("Impresion de todas las coordenada");
	    
	    System.out.println(c1);
	    System.out.println(c2);
	    System.out.println(c3);
	    System.out.println(c4);
	    System.out.println(c5);
	    System.out.println(c6);
	    System.out.println(c7);
	    System.out.println(c8);
	    System.out.println(c9);

	   //Imprimimos suma de coordenadas 3 y 6
	  			// System.out.println("Suma de 3 y 6");
	    
        
	    Coordinate sumada = c2.add(c4);
	    System.out.println(c2+"+"+c4+"="+sumada);
	    sumada = c3.add(c6.add(c2));
	    System.out.println(c3+"+"+c6+"+" +c2 +"="+sumada);
	    System.out.println( "Sumada es "+sumada+"; (c3, c6, c2)= (" +c3+", " +c6+", "+ c2+");");
	    
	    Coordinate restada = c3.subtract(c6);
	    System.out.println(c3+"-"+c6+"="+restada);
	    restada = c2.subtract(c4).subtract(c3);
	    System.out.println(c2+"-"+c4+"-" + c3 + "="+restada);
	    System.out.println( "Restada es "+restada+"; (c2, c4, c3)= (" +c2+", " +c4+", "+ c3+");");

	    
	    //
	    System.out.println("##############");
	    System.out.println("Coordenada c1" + c1);
	    for(int i=0; i< 2; i++) {
	    	System.out.print(c1.get(i));
	    }
	    //System.out.println("Coordenada c2 " +c2);
	    
	    //c1.add(c2);
	   //c1.set(0, 4); c1.set(1, 2);
	    
	    System.out.println("\nCoordenada c1" + c1);
	    for(int i=0; i< 2; i++) {
	    	System.out.print(c1.get(i) + "  ");
	    }
	    System.out.println("\n##############");
	    
	    //Creamos array de coordenadas
	    		//System.out.println("Array de coordenadas");
	    
        Coordinate[] v = new Coordinate[5];
	    for (int i=0; i<5; i++) {
	        v[i]= new Coordinate(i,4-i);
	    }
	    
	    //Imprimimos el array de coordenadas
	    
	    for (int i=0; i<5; i++) {
	        System.out.println(v[i].get(0)+","+v[i].get(1));
	    }

	    //Creamos una lista de coordenadas y lo imprimimos
	    		//System.out.println("lista de coordenadas");
	    
	    ArrayList<Coordinate> v2 = new ArrayList<Coordinate>();
	    for (int i=0; i<8; i++) {
	                v2.add(new Coordinate(i, i));
	                System.out.println(v2.get(i));
	    }
	    System.out.println("Adyacentes de "+c1+" :");
	    System.out.println(c1.adjacentCoordinates());
	    CellStatus c = CellStatus.WATER;
	    System.out.println(c);
	    Orientation o = Orientation.NORTH;
	    System.out.println(o);
	}
}

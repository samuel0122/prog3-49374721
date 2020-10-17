/* @author
 * Samuel Benjamin Oliva Bulpitt
 * 49374721
 * 25/09/2020
 */


package model;

import java.util.*;

public class Coordinate {
	
	private int[] components = new int[2];
	
	/* Constructor de la clase que recibe dos enteros para la coordenada:
	 * Almacenamos la coordenada 'x' en el componente 0 y la 'y' en el 1
	 */
	
	public Coordinate(int x, int y){
		components[0]=x;
		components[1]=y;
	};
	
	/* Copia de una coordenada que recibe un objeto coordenada:
	 * almacena en el components del Coordinate que llama a la funcion los valores del Coordinate c
	 */
	
	public Coordinate(Coordinate c){
		
		components = new int[components.length];
		
		for (int i=0; i<components.length;i++) //Utiliza un for para copiar los componentes de c en las coordenadas
			components[i] = (c.get(i));
		
	};
		
	/* Funcion set de coordenadas que recibe el componente (coordenada) y el valor:
	 * Establece la coordenada que se le pase al valor que se le diga, comprobando que la coordenada existe
	 */
	
	protected void set(int component,int value) {
		if (component>=0 && component<components.length) { /* Utiliza un if que comprueba que la componente sea valida
															*lo guarda en components si es correcta o manda error si no 
															*/
			components[component] = value;
		} else
			System.err.println("ERROR: Coordinate.set(); el component " + component + " no está en el rango.");
	} 
	
	/* Funcion get de coordenadas que recibe el componente (coordenada) deseado:
	 * Devuelve el valor de la coordenada que se desea en entero
	 */
	
	public int get(int component) {
		if (component>=0 && component<components.length) { /* Comprueba si la coordenada es correcta
															* devuelve el valor de components si lo es o error si no
															*/
			return components[component];
		} else
			System.err.println( "ERROR: Coordinate.get(); el component " + component + " no está en el rango." );

		return -1;
	};

	/*
	 * adjacentCoordinates
	 */
	public Set<Coordinate> adjacentCoordinates(){
		
		Set<Coordinate> adyacentes = new HashSet<Coordinate>();
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(i!=1 || j!=1)
					adyacentes.add(new Coordinate(get(0)-1+j,get(1)-1+i));
			}
		}
		return adyacentes;
	}
	
	/*
	 * copy()
	 */
	
	Coordinate copy() {
		Coordinate coord = new Coordinate(get(0),get(1));
		return coord;
	}
	
	
	/* Funcion resta de coordenadas que recibe un Coordinate c y lo resta al llamado 
	 * Devuelve el resultado en forma de Coordinates
	 */
	
	public Coordinate subtract(Coordinate c) {
		Coordinate cd = new Coordinate(0, 0); //Crea un Coordinate nuevo para no modificar los ya existentes en la resta
		
		for(int i=0; i<components.length; i++) //Resta los componentes de c al coordinate que lo llama
			cd.set(i, get(i)-c.get(i));
		
		return cd; //Devuelve el resultado de la resta
	};
	
	
	/* Funcion suma de coordenadas que recibe un Coordinate c y lo suma al llamado:
	 * Devuelve el resultado en forma de Coordinate
	 */
	
	public Coordinate add(Coordinate c) {
		Coordinate cd = new Coordinate(0,0); //Crea un Coordinate nuevo para no modificar los ya existentes en la suma
		
		for(int i=0; i<components.length; i++) { //Suma los componentes de ambas coordenadas en la nueva
			cd.set(i, c.get(i)+get(i));
		}
		return cd; //Devuelve la coordenada nueva con el resultado guardado
	};

	
	/* 
	 * Funcion toString que devuelve un String con las coordenadas en un formato correcto
	 */
	
	public String toString() {
		String cadena; //Crea un String en donde guardaremos las coordenadas con su formato
		 cadena = "(";
		 for (int i=0;i<components.length;i++) { //Añadir las coordenadas en el string con el for
			 cadena += components[i];
			 if (i<components.length-1) // Y comprobamos que no sea la ultima coordenada poner coma entre coordenadas
				 cadena += ", ";
		 }
		 cadena += ")";
		 return cadena; 
	};
	
	/* Funcion hashCode que devuelve el hashCode (identificador) del objeto
	 * Realiza unas operaciones para obtener un numero identificador
	 */
	
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + (components[0]);
		result = prime * result + (components[1]);
		if(result<0) //si el resultado es negativo, le cambia de signo positivo
			result*=(-1);
		return result;
		
	};
	
	/*
	 * Funcion equals que devuelve true si se compara un coordinate consigo mismo o con otro que tenga las mismas coordenadas
	 */
	
	public boolean equals( Object obj) {
		if (obj==null) return false; //Si es nulo devuelve falso
		  
		if (this==obj) return true; //Si es si mismo devuelve true
		  
		if (!(obj instanceof Coordinate) ) //Si no es un objeto Coordinate devuelve falso
			return false;
		    
		 Coordinate cd = (Coordinate) obj; //Creamos cd para comparar los componentes de ambos coordinates
		  
		 if ( (this.components[0]==cd.components[0]) && (this.components[1]==cd.components[1]) )
			 return true; //Si los componentes eran iguales devuelve true
		 else
			 return false;
	};
	
};

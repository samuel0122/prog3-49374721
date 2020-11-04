/*
 * @author Samuel Oliva Bulpitt, 49374721
 */


package model;

import java.util.*;

import model.ship.Coordinate2D;

// TODO: Auto-generated Javadoc
/**
 * The Class Coordinate.
 */
public abstract class Coordinate {
	
	/** The components. */
	private int[] components; //Componentes de una coordenada
	
	
	/* 
	 * Constructor de la clase que recibe dos enteros para la coordenada:
	 * Almacenamos la coordenada 'x' en el componente 0 y la 'y' en el 1
	 */
	protected Coordinate(int dim){
		components= new int[dim];
	}
	
	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param c the c
	 */
	/* 
	 * Copia de una coordenada que recibe un objeto coordenada:
	 * almacena en el components del Coordinate que llama a la funcion los valores del Coordinate c
	 */
	protected Coordinate(Coordinate c){
		
		components = new int[c.components.length];
		System.out.print(c.components.length);
		for (int i=0; i<components.length;i++) //Utiliza un for para copiar los componentes de c en las coordenadas
			components[i] = (c.get(i));
		
	};
		
	
	/**
	 * Sets the coordinate. Comprueba que la coordenada sea posible.
	 *
	 * @param component the component
	 * @param value the value
	 */
	/* 
	 * Funcion setter de coordenadas que recibe el componente (coordenada) y el valor:
	 * Establece la coordenada que se le pase al valor que se le diga, comprobando que la coordenada existe
	 */
	public void set(int component,int value) {
		if (component>=0 && component<components.length) { /* Utiliza un if que comprueba que la componente sea valida											*/
			components[component] = value;
		} else
			throw new IllegalArgumentException ("ERROR: Coordinate.set(); el component " + component + " no está en el rango.");
	} 
	
	
	/**
	 * Gets the coordinates. 
	 *
	 * @param component the component
	 * @return the int
	 */
	/* Funcion getter de coordenadas que recibe el componente (coordenada) deseado:
	 * Devuelve el valor de la coordenada que se desea en entero
	 */
	public int get(int component) {
		if (component>=0 && component<components.length) { /* Comprueba si la coordenada es correcta											*/
			return components[component];
		} else
			throw new IllegalArgumentException ( "ERROR: Coordinate.get(); el component " + component + " no está en el rango." );

	};

	
	/**
	 * Adjacent coordinates. Devuelve una lista con las coordenadas adyacentes (vecinas) a
	 * la coordenada que invoca la funcion.
	 *
	 * @return the sets the
	 */
	/*
	 * Método adjacentCoordinates que devuelve una lista con las coordenadas adyacentes (vecinas) a
	 * la coordenada que invoca la funcion
	 */
	public abstract Set<Coordinate> adjacentCoordinates();
	
	/*
	 * Método copy() que devuelve una copia defensiva de las coordenadas que invoca la funcion
	 */
	
	/**
	 * Copies the coordinate.
	 */
	public abstract Coordinate copy();
	
	
	/* 
	 * Funcion resta de coordenadas que recibe un Coordinate c y lo resta al Coordinate que invoca la funcion 
	 * Devuelve el resultado en forma de Coordinates
	 */
	
	/**
	 * Subtract. Resta de coordenadas que recibe un Coordinate c y lo resta al Coordinate que invoca la funcion 
	 * Devuelve el resultado en forma de Coordinates
	 *
	 * @param c the c
	 * @return the coordinate
	 */
	public Coordinate subtract(Coordinate c) {
		Objects.requireNonNull(c);
		Coordinate cd = this.copy(); //Crea un Coordinate nuevo para no modificar los ya existentes en la suma
		int limit;
		//Si alguno es 2D recorre 2 componentes
		if(c instanceof Coordinate2D || this instanceof Coordinate2D) {
			limit=2;
		} else //Si es 3D recorre 3 componentes
			limit=3;
		
		for(int i=0; i<limit; i++) { //Suma los componentes de ambas coordenadas en la nueva
			cd.set(i, this.get(i)-c.get(i));
		}
		return cd;  
	};
	
	
	/* Funcion suma de coordenadas que recibe un Coordinate c y lo suma al Coordinate que invoca la funcion:
	 * Devuelve el resultado en forma de Coordinate
	 */
	
	/**
	 * Adds. Suma de coordenadas que recibe un Coordinate c y lo suma al Coordinate que invoca la funcion:
	 * Devuelve el resultado en forma de Coordinate
	 *
	 * @param c the c
	 * @return the coordinate
	 */
	public Coordinate add(Coordinate c) {
		Objects.requireNonNull(c);
		Coordinate cd = this.copy(); //Crea un Coordinate nuevo para no modificar los ya existentes en la suma
		int limit;
		//Si alguno es 2D recorre 2 componentes
		if(c instanceof Coordinate2D || this instanceof Coordinate2D) {
			limit=2;
		} else //Si es 3D recorre 3 componentes
			limit=3;
		
		for(int i=0; i<limit; i++) { //Suma los componentes de ambas coordenadas en la nueva
			cd.set(i, this.get(i)+c.get(i));
		}
		return cd; 
	};

	

	
	/* 
	 * Funcion hashCode que devuelve el hashCode (identificador) del objeto
	 * Realiza unas operaciones para obtener un numero identificador
	 */
	
	/**
	 * Hash code. Devuelve un identificador del objeto.
	 *
	 * @return the int
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
	
	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true si se compara un coordinate consigo mismo o con otro que tenga las mismas coordenadas
	 */
	public boolean equals( Object obj) {
		if (obj==null) return false; //Si es nulo devuelve falso
		  
		if (this==obj) return true; //Si es si mismo devuelve true
		  
		if (!(obj instanceof Coordinate) ) //Si no es un objeto Coordinate devuelve falso
			return false;
		    
		 Coordinate cd = (Coordinate) obj; //Creamos cd para comparar los componentes de ambos coordinates
		  
		 boolean equals = true;
		 for(int i=0; i<this.components.length; i++) {
			 if(this.components[i]!=cd.components[i])
				 equals = false;
		}
		 
		return equals;
	};
	
};

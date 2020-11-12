/**
  	@author Samuel Oliva Bulpitt
 
 */


package model;

import java.util.*;

import model.ship.Coordinate2D;


/**
 * Clase Coordinate.
 */
public abstract class Coordinate {
	
	/** Array int components. Componentes de Coordinate. */
	private int[] components; //Componentes de una coordenada
	
	
	/**
	 * Constructor de la clase que inicializa el Coordinate con el tamaño pasado por parametro.
	 *
	 * @param dim the dim
	 */
	
	protected Coordinate(int dim){
		components= new int[dim];
	}
	
	
	/**
	 * Constructor de copia de Coordinate.
	 *
	 * @param c the c
	 */
	protected Coordinate(Coordinate c){
		
		components = new int[c.components.length];
		System.out.print(c.components.length);
		for (int i=0; i<components.length;i++) //Utiliza un for para copiar los componentes de c en las coordenadas
			components[i] = (c.get(i));
		
	};
		
	
	/**
	 * Setter de coordinate. Comprueba que el componente es correcto.
	 *
	 * @param component the component
	 * @param value the value
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	
	protected void set(int component,int value) {
		if (component>=0 && component<components.length) { /* Utiliza un if que comprueba que la componente sea valida											*/
			components[component] = value;
		} else
			throw new IllegalArgumentException ("ERROR: Coordinate.set(); el component " + component + " no está en el rango.");
	} 
	
	
	/**
	 * Getter de coordinates. Coge el valor del componente deseado.
	 *
	 * @param component the component
	 * @return Int valor del componente
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	
	public int get(int component) {
		if (component>=0 && component<components.length) { /* Comprueba si la coordenada es correcta											*/
			return components[component];
		} else
			throw new IllegalArgumentException ( "ERROR: Coordinate.get(); el component " + component + " no está en el rango." );

	};

	
	/**
	 * Metodo abstracto de adjacentCoordinates. Devolvera las coordenadas vecinas, pero se debe implementar en las subclases de Coordinate.
	 *
	 * @return the sets the
	 */

	public abstract Set<Coordinate> adjacentCoordinates();
	
	
	
	/**
	 * Metodo abstracto de copia del coordinate. Devolvera una copia del coordenate que lo invocao, pero de sebe implementar en las subclases de Coordinate.
	 *
	 * @return the coordinate
	 */
	public abstract Coordinate copy();
	
	
	/**
	 * Subtract. Resta de coordenadas que recibe un Coordinate c y lo resta al Coordinate que invoca la funcion 
	 * Devuelve el resultado en forma de Coordinates
	 *
	 * @param c the c
	 * @return Coordinate resta
	 */
	public Coordinate subtract(Coordinate c) {
		Objects.requireNonNull(c);
		Coordinate resta = this.copy(); //Crea un Coordinate nuevo para no modificar los ya existentes en la suma
		int limit;
		//Si alguno es 2D recorre 2 componentes
		if(c instanceof Coordinate2D || this instanceof Coordinate2D) {
			limit=2;
		} else //Si es 3D recorre 3 componentes
			limit=3;
		
		for(int i=0; i<limit; i++) { //Suma los componentes de ambas coordenadas en la nueva
			resta.set(i, this.get(i)-c.get(i));
		}
		return resta;  
	};
	
	
	/**
	 * Adds. Suma de coordenadas que recibe un Coordinate c y lo suma al Coordinate que invoca la funcion:
	 * Devuelve el resultado en forma de Coordinate
	 *
	 * @param c the c
	 * @return Coordinate suma
	 */
	public Coordinate add(Coordinate c) {
		Objects.requireNonNull(c);
		Coordinate suma = this.copy(); //Crea un Coordinate nuevo para no modificar los ya existentes en la suma
		int limit;
		//Si alguno es 2D recorre 2 componentes
		if(c instanceof Coordinate2D || this instanceof Coordinate2D) {
			limit=2;
		} else //Si es 3D recorre 3 componentes
			limit=3;
		
		for(int i=0; i<limit; i++) { //Suma los componentes de ambas coordenadas en la nueva
			suma.set(i, this.get(i)+c.get(i));
		}
		return suma; 
	};

	/**
	 * Hash code. Devuelve un identificador del objeto.
	 *
	 * @return int hashCode
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
	
	/**
	 * Equals. Comprobara si se compara un coordinate consigo mismo o con otro que tenga las mismas coordenadas.
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

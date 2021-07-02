/**
  	@author Samuel Oliva Bulpitt
 
 */

package model;

import java.util.*;

import model.ship.Coordinate2D;

/**
 * Las coordenadas de las casillas del tablero.
 */
public abstract class Coordinate {
	
	/** Componentes de Coordinate. */
	private int[] components; //Componentes de una coordenada
	
	
	/**
	 * Constructor de la clase que inicializa el Coordinate con las dimensiones introducidas.
	 *
	 * @param dim Dimensiones de las coordenadas.
	 */
	
	protected Coordinate(int dim){
		components= new int[dim];
	}
	
	
	/**
	 * Constructor de copia de Coordinate.
	 *
	 * @param c Coordenada a copiar.
	 */
	protected Coordinate(Coordinate c){
		
		components = new int[c.components.length];
		System.out.print(c.components.length);
		for (int i=0; i<components.length;i++) //Utiliza un for para copiar los componentes de c en las coordenadas
			components[i] = (c.get(i));
		
	};
		
	
	/**
	 * Establece un componente de la coordenada al valor introducido.
	 *
	 * @param component Posicion del componente a modificar.
	 * @param value Valor a establecer en el componente.
	 * @throws IllegalArgumentException si la posicion del componente está fuera de las dimensiones de la coordenada.
	 */
	
	protected void set(int component,int value) {
		if (component>=0 && component<components.length) { /* Utiliza un if que comprueba que la componente sea valida											*/
			components[component] = value;
		} else
			throw new IllegalArgumentException ("ERROR: Coordinate.set(); el component " + component + " no está en el rango.");
	} 
	
	
	/**
	 * Recoge el valor del componente deseado.
	 *
	 * @param component Componente de la coordenada.
	 * @return Valor del componente.
	 * @throws IllegalArgumentException si el componente solicitado está fuera de las dimensiones de la coordenada.
	 */
	
	public int get(int component) {
		if (component>=0 && component<components.length) { /* Comprueba si la coordenada es correcta											*/
			return components[component];
		} else
			throw new IllegalArgumentException ( "ERROR: Coordinate.get(); el component " + component + " no está en el rango." );

	};

	
	/**
	 * Obtiene las coordenadas adyacentes a la coordenada.
	 * 
	 * @return Conjunto de coordenadas adyacentes.
	 */

	public abstract Set<Coordinate> adjacentCoordinates();
	
	
	
	/**
	 * Realiza una copia de la coordenada.
	 * 
	 * @return Coordenada copia.
	 */
	public abstract Coordinate copy();
	
	
	/**
	 * Resta de coordenadas que recibe una coordenada y lo resta al actual.
	 *
	 * @param c Coordenada a restar.
	 * @return Coordenada actual restandole el recibido.
	 * @throws NullPointerException si la coordenada recibida es nulo.
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
	 * Suma de coordenadas que recibe una coordenada y lo suma al actual.
	 *
	 * @param c Coordenada a sumar
	 * @return Coordenada actual sumandole el recibido.
	 * @throws NullPointerException si la coordenada recibida es nulo.
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
	 * Identificador del objeto Coordenada.
	 *
	 * @return identificador de la coordenada.
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

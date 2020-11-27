/**
 * @author Samuel Oliva Bulpitt
 */
package model;

import model.ship.*;
import model.aircraft.*;

/**
 * Método para crear objetos Ship y Aircraft a partir del nombre
 */
public class CraftFactory {
	
	/**
	 * Crea el navio
	 *
	 * @param String tipoDeNavio
	 * @param Orientation orientacion
	 * @return Battleship navio
	 */
	public static Craft createCraft(String type, Orientation o) {
		switch (type) {
			case "Battleship": 
				return new Battleship(o);
				
			case "Carrier": 
				return new Carrier(o);
			
			case "Cruiser": 
				return new Cruiser(o);
			
			case "Destroyer": 
				return new Destroyer(o);
			
			case "Bomber": 
				return new Bomber(o);
			
			case "Fighter": 
				return new Fighter(o);
			
			case "Transport": 
				return new Transport(o);
			
			default: 
				return null;	
		}
	}
}

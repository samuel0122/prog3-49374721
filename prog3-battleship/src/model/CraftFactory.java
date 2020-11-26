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
		
		if(type == "Battleship") {
			return new Battleship(o);
			
		} else if (type == "Carrier")  {
			return new Carrier(o);
			
		} else if (type == "Cruiser")  {
			return new Cruiser(o);
			
		} else if (type == "Destroyer")  {
			return new Destroyer(o);
			
		} else if (type == "Bomber")  {
			return new Bomber(o);
			
		} else if (type == "Fighter")  {
			return new Fighter(o);
			
		} else if (type == "Transport")  {
			return new Transport(o);
			
		}
		
		return null;
	}
}

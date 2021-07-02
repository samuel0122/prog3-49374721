/**
 * @author Samuel Oliva Bulpitt
 */
package model;

import model.ship.*;

import java.lang.reflect.Constructor;

import model.aircraft.*;

/**
 * Factoria de navios.
 */
public class CraftFactory {
	
	/**
	 * Crea el navio con su tipo correspondiente.
	 *
	 * @param type Tipo de navio
	 * @param o Orientacion del navio
	 * @return Battleship navio
	 */
	public static Craft createCraft(String type, Orientation o) {
		
		try {
			return (Craft) Class.forName("model."+type).getConstructor(Orientation.class).newInstance(o);
		} catch (Throwable e) {
			return null;
		}
	}
}

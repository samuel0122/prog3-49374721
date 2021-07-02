/**
 * @author Samuel Oliva Bulpitt
 */
package model.io;

import model.Craft;
import model.Game;
import model.Orientation;

/**
 * Factoria que crea un VisualiserConsole o VisualiserGIF.
 */
public class VisualiserFactory {
	
	/**
	 * Crea el visualiser de la clase que se introduzca.
	 *
	 * @param name Nombre de la clase.
	 * @param g Partida a enlazar con el Visualiser.
	 * @return Visualizador de la clase que se introdujo en name.
	 */
	public static IVisualiser createVisualiser(String name, Game g) {
		try {
			return (IVisualiser) Class.forName("model.io.Visualiser"+name).getConstructor(Game.class).newInstance(g);
		} catch (Throwable e) {
			return null;
		}
	}
}
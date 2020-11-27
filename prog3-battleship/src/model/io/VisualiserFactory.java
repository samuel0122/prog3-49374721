/**
 * @author Samuel Oliva Bulpitt
 */
package model.io;

import java.util.Objects;

import model.Game;

/**
 * Clase VisualiserFactory, sirve para crear un VisualiserConsole o VisualiserGIF
 */
public class VisualiserFactory {
	
	/**
	 * Metodo createVisualiser, dependiendo del String que se pase devuelve VisualiserConsole,
	 * Visualiser GIF o null
	 * @param String name of item to create
	 * @param Game g, partida a la que enlazar
	 * @return VisualiserConsole/VisualiserGIF/null
	 */
	public static IVisualiser createVisualiser(String name, Game g) {
		
		if(name.equals("Console") )
			return new VisualiserConsole(g);
		else if (name.equals("GIF") ) 
			return new VisualiserGIF(g);
		return null;
	}
}
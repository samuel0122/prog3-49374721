/**
 * @author Samuel Oliva Bulpitt
 */
package model.io;

import java.util.Objects;

import model.Game;

 /**
  * Clase VisualiserConsole. Permite producir un GIF animado
  * que representa la partida
  */
public class VisualiserConsole implements IVisualiser {
	/** Game en donde guardamos la partida a representar */
	private Game game;

	/**
	 * Constructor VisualiserConsole que recibe un Game que no puede ser null
	 * 
	 * @param g
	 * @throws NullPointerException
	 */
	public VisualiserConsole (Game g) {
		Objects.requireNonNull(g);
		this.game=g;
	}
	
	/**
	 * Metodo show que imprime la partida
	 */
	@Override
	public void show() {
		System.out.println(game.toString());
	}

	/**
	 * Metodo close que no hace nada en esta clase
	 */
	@Override
	public void close() { }
	
}

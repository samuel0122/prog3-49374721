/**
 * @author Samuel Oliva Bulpitt
 */
package model.io;

import java.util.Objects;

import model.Game;

 /**
  * Clase que permite imprimir la partida por pantalla.
  */
public class VisualiserConsole implements IVisualiser {
	/** Partida a representar */
	private Game game;

	/**
	 * Constructor VisualiserConsole que recibe un Game que no puede ser null
	 * 
	 * @param g Partida a representar.
	 * @throws NullPointerException Si el game introducido es nulo.
	 */
	public VisualiserConsole (Game g) {
		Objects.requireNonNull(g);
		this.game=g;
	}
	
	/**
	 * Imprime la partida en la consola.
	 */
	@Override
	public void show() {
		System.out.println(game.toString());
	}

	/**
	 * No hace nada.
	 */
	@Override
	public void close() { }
	
}

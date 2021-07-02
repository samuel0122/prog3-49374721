/**
 * @author Samuel Oliva Bulpitt
 */
package model.io;

/**
 * Interface IVisualiser.
 */
public interface IVisualiser {
	
	/**
	 * Guarda la imagen del estado actual de la partida, mostrandolo por pantalla o en un GIF.
	 */
	public void show();
	
	/**
	 * Finaliza la representacion de la partida.
	 */
	public void close();
}

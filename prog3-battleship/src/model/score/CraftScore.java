/**
 * @author Samuel Oliva Bulpitt
 */
package model.score;

import model.Craft;
import model.io.IPlayer;

/**
 * Clase que contabiliza los puntos por navios destruidos.
 */
public class CraftScore extends Score<Craft> {

	/**
	 * Constructor de CraftScore.
	 *
	 * @param player Jugador al que enlazar los puntos.
	 */
	public CraftScore(IPlayer player) {
		super(player);
	}

	/**
	 * Añade la cantidad de puntos del navio destruido a los puntos actuales.
	 *
	 * @param sc Navio destruido cuyos puntos se han de añadir.
	 */
	@Override
	public void score(Craft sc) {
		this.score += sc.getValue();
		
	}

}

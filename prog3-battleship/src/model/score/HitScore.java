/**
 * @author Samuel Oliva Bulpitt
 */
package model.score;

import model.CellStatus;
import model.io.IPlayer;

/**
 * Clase que contabiliza los puntos por golpes.
 */
public class HitScore extends Score<CellStatus> {

	/**
	 * Constructor de HitScore.
	 *
	 * @param player Jugador al que enlazar los puntos.
	 */
	public HitScore(IPlayer player) {
		super(player);
	}

	/**
	 * Añade un punto si la ultima coordenada golpeada fue un choque con un navio.
	 *
	 * @param sc Estado de la ultima coordenada golpeada.
	 */
	@Override
	public void score(CellStatus sc) {
		if(sc == CellStatus.HIT || sc == CellStatus.DESTROYED)
			this.score++;
		
	}

}

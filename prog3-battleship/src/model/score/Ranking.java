/**
 * @author Samuel Oliva Bulpitt
 */
package model.score;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import model.exceptions.score.EmptyRankingException;

/**
 * Clase que organiza las posiciones de los jugadores por puntos.
 *
 * @param <ScoreType> Tipo de Score.
 */
public class Ranking<ScoreType extends Score<?>> {
	
	/**  Lista organizada para la posicion de los jugadores. */
	private SortedSet<ScoreType> scoreSet;
	
	
	/**
	 * Constructor de Ranking.
	 */
	public Ranking() {
		scoreSet = new TreeSet<>();
	}
	
	/**
	 * Añade un score al ranking.
	 *
	 * @param st Tipo de puntos que añadir al ranking.
	 */
	public void addScore(ScoreType st) {
		this.scoreSet.add(st);
	}
	
	/**
	 * Obtiene el ranking completo.
	 *
	 * @return Ranking por puntos.
	 */
	public SortedSet<ScoreType> getSortedRanking(){
		return this.scoreSet;
		
	}
	
	/**
	 * Obtiene el ganador del ranking.
	 *
	 * @return Ganador del ranking.
	 * @throws EmptyRankingException Si el ranking está vacio.
	 */
	public ScoreType getWinner() throws EmptyRankingException {
		if(scoreSet.isEmpty())
			throw new EmptyRankingException();
		return Objects.requireNonNull(scoreSet.first());
	}
}

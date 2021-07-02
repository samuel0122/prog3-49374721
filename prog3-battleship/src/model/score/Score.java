/**
 * @author Samuel Oliva Bulpitt
 */
package model.score;

import model.io.IPlayer;
import java.lang.Comparable;
import java.util.Objects;

/**
 * Clase que contabiliza los puntos de cada tipo.
 *
 * @param <T> tipo de puntos.
 */
public abstract class Score<T> implements Comparable<Score<T>>{ 
	
	/**  Jugador de quien se controlan los puntos. */
	private IPlayer player;
	
	/**  Contador de puntos. */
	protected int score;
	
	/**
	 * Constructor de Score.
	 *
	 * @param player Jugador al que se enlazan los puntos.
	 */
	public Score(IPlayer player) { 
		Objects.requireNonNull(this.player=player);
		this.score=0;
	}
	
	/**
	 * Obtiene los puntos.
	 *
	 * @return Puntos del jugador.
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Compara los puntos con los de otro jugador.
	 *
	 * @param other Puntos del otro jugador.
	 * @return Numero negativo si es mayor al del otro jugador.
	 */
	public int compareTo(Score<T> other) {
		if(this.score==other.getScore()) //Si ambos score son iguales comparamos nombres
			return this.toString().compareTo(other.toString());
		else if(this.score>other.getScore())
			return -1; //Si este score es mayor lo consideramos menor
		else
			return 1; //Si este score es menor lo consideramos mayor
	}
	
	/**
	 * Obtiene los puntos del jugador.
	 *
	 * @return Nombre del jugador junto a sus puntos.
	 */
	public String toString() {
		return this.player.getName()+": "+score;
	}
	
	/**
	 * Añade puntos al score.
	 *
	 * @param sc Elemento a tener en cuenta para añadir los puntos.
	 */
	public abstract void score(T sc);
}

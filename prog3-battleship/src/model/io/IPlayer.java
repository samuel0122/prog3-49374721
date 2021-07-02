/**
 * @author Samuel Oliva Bulpitt
 */
package model.io;

import model.Board;
import model.CellStatus;
import model.Coordinate;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;

/**
 * Interfaz IPlayer.
 */
public interface IPlayer {
	
	/**
	 * Obtiene el nombre y tipo del jugador.
	 * 
	 * @return Nombre y tipo del jugador.
	 */
	public String getName();
	
	/**
	 * Coloca los navios del jugador en el tablero.
	 *
	 * @param b Tablero en donde colocar un navio.
	 * 
	 * @throws BattleshipIOException Si es genera un error del jugador.
	 * @throws InvalidCoordinateException Si se intenta colocar un navio en una coordenada fuera del tablero.
	 * @throws OccupiedCoordinateException Si se intenta colocar un navio en una posicion ya ocupada.
	 * @throws NextToAnotherCraftException Si se intenta colocar un navio al lado de otro.
	 */
	public void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException;
	
	/**
	 * Simula el siguiente disparo del jugador en el tablero introducido.
	 *
	 * @param b Tablero al que disparar.
	 * 
	 * @return Coordenada en donde se golpeó.
	 * 
	 * @throws BattleshipIOException Si se genera un error del jugador.
	 * @throws CoordinateAlreadyHitException Si se intenta disparar en una coordenada ya golpeada.
	 * @throws InvalidCoordinateException Si se intenta disparar en una coordenada fuera del tablero.
	 */
	public Coordinate nextShoot(Board b) throws BattleshipIOException, CoordinateAlreadyHitException, InvalidCoordinateException;

	/**
	 * Obtiene el estado de la ultima coordenada golpeada.
	 * 
	 * @return Estado del ultimo golpe.
	 */
	public CellStatus getLastShotStatus();
}

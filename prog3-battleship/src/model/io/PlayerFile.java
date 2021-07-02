/**
 * @author Samuel Oliva Bulpitt
 */

package model.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import model.Board;
import model.CellStatus;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;

/**
 * Jugador que juega a partir de un fichero.
 *
 */
public class PlayerFile implements IPlayer {
	/** Buffer en donde se guarda el fichero leido pasado por el constructor. */
	private BufferedReader br;
	/** Nombre del jugador. */
	private String name;
	/** Estado de la ultima celda golpeada */
	private CellStatus lastShotStatus;
	
	/**
	 * Constructor de PlayerFile.
	 * @param reader Buffer de lectura del fichero en donde leer.
	 * @param name Nombre del jugador.
	 */
	public PlayerFile(String name,BufferedReader reader) {
		Objects.requireNonNull(reader);
		this.name=name;
		this.br=reader;
		this.lastShotStatus=null;
	}
	
	/**
	 * Obtiene el nombre y tipo del jugador.
	 * 
	 * @return Nombre y tipo del jugador.
	 */
	@Override
	public String getName() { return this.name+" (PlayerFile)"; }

	/**
	 * Coloca los navios del jugador en el tablero.
	 *
	 * @param b Tablero en donde colocar un navio.
	 * @throws BattleshipIOException Si es genera un error en la lectura del fichero.
	 * @throws InvalidCoordinateException Si se intenta colocar un navio en una coordenada fuera del tablero.
	 * @throws OccupiedCoordinateException Si se intenta colocar un navio en una posicion ya ocupada.
	 * @throws NextToAnotherCraftException Si se intenta colocar un navio al lado de otro.
	 */
	@Override
	public void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		Objects.requireNonNull(b);
		try {
			String lectura;
			lectura=br.readLine();
			while(lectura !=(null) && !lectura.equals("endput") && !lectura.equals("exit") ) {
				String [] tokens = lectura.split("\\s+");
				
				//Si el comando no es put, o tiene menos o mas de los argumentos esperados, lanzo exception
				if(!tokens[0].equals("put")) {
					throw new BattleshipIOException("Error en putCraft del jugador " + name + ": comando "+tokens[0]+" no valido.\n");
				}
				if(tokens.length<5 || tokens.length > 6)
					throw new BattleshipIOException("Error en putCraft del jugador " + name + ": cantidad de parametros recibidos ("+tokens.length+") no valido.\n");
					
				
				//Crea el navio con su orientacion
				Craft navio = null;
				if(tokens[2].equals("NORTH") ) {
					navio = CraftFactory.createCraft(tokens[1], Orientation.NORTH);
				} else if(tokens[2].equals("SOUTH") ) {
					navio = CraftFactory.createCraft(tokens[1], Orientation.SOUTH);
				} else if(tokens[2].equals("EAST") ) {
					navio = CraftFactory.createCraft(tokens[1], Orientation.EAST);
				} else if(tokens[2].equals("WEST") ) {
					navio = CraftFactory.createCraft(tokens[1], Orientation.WEST);
				} else {
					throw new BattleshipIOException("Error en putCraft del jugador " + name + ": Orientatation "+tokens[2]+" no valida.\n");
				}
				
				//Crea coordenada
				Coordinate coord;
				if(tokens.length == 5) {
					coord = CoordinateFactory.createCoordinate(Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
				} else {
					coord = CoordinateFactory.createCoordinate(Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
				}
				
				//Añade navio
				b.addCraft(navio, coord);
				lectura=br.readLine();
			}
		} catch (IOException e) {
			throw new BattleshipIOException("Error en putCraft con la lectura del buffer: "+e);
		} catch (NumberFormatException e) {
			throw new BattleshipIOException("Error en putCraft con las coordenadas: "+e);
		}
		
	}

	/**
	 * Simula el siguiente disparo del jugador en el tablero introducido.
	 * 
	 * @param b Tablero al que disparar.
	 * @return Coordenada en donde se golpeó.
	 * @throws BattleshipIOException Si se genera un error del jugador.
	 * @throws CoordinateAlreadyHitException Si se intenta disparar en una coordenada ya golpeada.
	 * @throws InvalidCoordinateException Si se intenta disparar en una coordenada fuera del tablero.
	 */
	@Override
	public Coordinate nextShoot(Board b) throws BattleshipIOException, CoordinateAlreadyHitException, InvalidCoordinateException {

		try {
			
			String lectura=br.readLine();
			
			if(lectura == null || lectura.equals("exit") ) {
				
				this.lastShotStatus = null;
				return null;
			} else {
				String [] tokens = lectura.split("\\s+");
				
				if (!tokens[0].equals("shoot") )
					throw new BattleshipIOException("Error en nextShoot: el comando "+tokens[0]+" no es valido.");
				if(tokens.length <3 || tokens.length > 4)
					throw new BattleshipIOException("Error en nextShoot: cantidad de parametros recibidos ("+tokens.length+") no valido.");
				
				Coordinate coord;
				if(tokens.length == 3) {
					coord = CoordinateFactory.createCoordinate(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
				} else {
					coord = CoordinateFactory.createCoordinate(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
				}
				
				this.lastShotStatus=b.hit(coord);
				return coord;
			}
			
		} catch (IOException e) {
			throw new BattleshipIOException("Error en nextShoot con la lectura del buffer: "+e);
		} catch (NumberFormatException e) {
			throw new BattleshipIOException("Error en nextShoot con las coordenadas: "+e);
		}
	}

	/**
	 * Obtiene el estado de la ultima coordenada golpeada.
	 * 
	 * @return Estado del ultimo golpe.
	 */
	@Override
	public CellStatus getLastShotStatus() {
		return this.lastShotStatus;
	}
}

/**
 * @author Samuel Oliva Bulpitt
 */

package model.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import model.Board;
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
 * Clase PlayerFile para jugar leyendo comandos de un archivo de texto.
 *
 */
public class PlayerFile implements IPlayer {
	/** BufferedReader br, buffer en donde se guarda el fichero leido pasado por el constructor. */
	private BufferedReader br;
	/** String name, nombre del jugador. */
	private String name;
	
	/**
	 * Constructor de PlayerFile
	 * @param BufferedReader reader, el texto leido.
	 * @param String name, nombre del jugador.
	 */
	public PlayerFile(BufferedReader reader, String name) {
		Objects.requireNonNull(reader);
		this.name=name;
		this.br=reader;
	}
	
	/**
	 * Metodo getName(), getter del nombre del jugador.
	 */
	@Override
	public String getName() { return this.name+" (PlayerFile)"; }

	/**
	 * Metodo putCrafts(), ejecuta los comando de colocar un navio guardados en el buffer.
	 * @param Board b, tabla del juego.
	 * @throws BattleshipIOException 
	 * @throws NextToAnotherCraftException 
	 * @throws OccupiedCoordinateException 
	 * @throws InvalidCoordinateException 
	 */
	@Override
	public void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		Objects.requireNonNull(b);
		try {
			String lectura;
			while( !( lectura=br.readLine() ).equals("endput") && !lectura.equals("exit") && !lectura.equals(null) ) {
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
			}
		} catch (IOException e) {
			throw new BattleshipIOException("Error en putCraft con la lectura del buffer: "+e);
		} catch (NumberFormatException e) {
			throw new BattleshipIOException("Error en putCraft con las coordenadas: "+e);
		}
		
	}

	/**
	 * Metodo nextShoot(), ejecuta los comandos de disparo guardados en el buffer.
	 * @param Board b, tabla del juego.
	 * @throws BattleshipIOException 
	 * @throws InvalidCoordinateException 
	 * @throws CoordinateAlreadyHitException 
	 */
	@Override
	public Coordinate nextShoot(Board b) throws BattleshipIOException, CoordinateAlreadyHitException, InvalidCoordinateException {

		try {
			String lectura=br.readLine();
			
			if(lectura.equals("exit") || lectura.equals(null) ) {
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
				
				b.hit(coord);
				return coord;
			}
			
		} catch (IOException e) {
			throw new BattleshipIOException("Error en nextShoot con la lectura del buffer: "+e);
		} catch (NumberFormatException e) {
			throw new BattleshipIOException("Error en nextShoot con las coordenadas: "+e);
		}
	}
}

/**
 * @author Samuel Oliva Bulpitt
 */
package model.io;

import java.io.*;
import model.exceptions.io.BattleshipIOException;

/**
 * Factoria que crea un PlayerFile o un PlayerRandom.
 */
public class PlayerFactory {
	
	/**
	 * Metodo createPlayer, devuelve el tipo de player que se pide.
	 *
	 * @param playerName Nombre del jugador.
	 * @param fileORseed Fichero o semilla con el generar los comandos del jugador.
	 * @return Jugador del tipo PlayerFile o PlayerRandom, si se introdujo un fichero o una semilla.
	 * @throws BattleshipIOException Si se produce un error buscando el fichero.
	 */
	public static IPlayer createPlayer(String playerName, String fileORseed) throws BattleshipIOException {
		try {
			//Mira si contiene los caracteres para ser un playerFile y lo crea
			if(fileORseed.contains(".") || fileORseed.contains("/") || fileORseed.contains("\\") ) 
				return new PlayerFile(playerName, new BufferedReader(new FileReader(fileORseed)));

			//Si no es playerFile, se intenta crear el playerRandom
			return new PlayerRandom( playerName, Long.parseLong(fileORseed));
		
		} catch (NumberFormatException e) { //Si no era un long, se devuelve null
			return null;
		}catch (FileNotFoundException e) { //Si se lanza excepcion, lo convertimos en BattleshipIOException
			throw new BattleshipIOException("Error en createrPlater buscando el archivo: "+e);
		}
	}
}

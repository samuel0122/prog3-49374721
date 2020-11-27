/**
 * @author Samuel Oliva Bulpitt
 */
package model.io;

import java.io.*;
import model.exceptions.io.BattleshipIOException;

/**
 * Clase Player Factory, que crea un PlayerFile o un PlayerRandom
 */
public class PlayerFactory {
	
	/**
	 * Metodo createPlayer, devuelve el tipo de player que se pide
	 * 
	 * @param String playerName
	 * @param String FileName or seed
	 * @return IPlayer playerType
	 * @throws BattleshipIOException
	 */
	public static IPlayer createPlayer(String playerName, String fileORseed) throws BattleshipIOException {
		try {
			//Mira si contiene los caracteres para ser un playerFile y lo crea
			if(fileORseed.contains(".") || fileORseed.contains("/") || fileORseed.contains("\\") ) 
				return new PlayerFile( new BufferedReader(new FileReader(fileORseed)), playerName);

			//Si no es playerFile, se intenta crear el playerRandom
			return new PlayerRandom( playerName, Long.parseLong(fileORseed));
		
		} catch (NumberFormatException e) { //Si no era un long, se devuelve null
			return null;
		}catch (FileNotFoundException e) { //Si se lanza excepcion, lo convertimos en BattleshipIOException
			throw new BattleshipIOException("Error en createrPlater buscando el archivo: "+e);
		}
	}
}

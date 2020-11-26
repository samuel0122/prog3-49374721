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
			//Mira si es playerFile
			boolean isPlayerFile=false;
			for(int i = 0; (i < fileORseed.length()) && !isPlayerFile; i++) {
				char c = fileORseed.charAt(i);
				if((c =='.') || (c =='/') || (c =='\\'))
					isPlayerFile=true;
			}
			//Si es playerFile lo crea
			if(isPlayerFile) {
				FileReader reader = new FileReader(fileORseed);
				return new PlayerFile( new BufferedReader(reader), playerName);
			}
			
			//Si no es playerFile, se intenta crear el playerRandom
			return new PlayerRandom( playerName, Long.parseLong(fileORseed));
		
		} catch (NumberFormatException e) { //Si no era un long, se devuelve null
			return null;
		}catch (FileNotFoundException e) { //Si se lanza excepcion, lo convertimos en BattleshipIOException
			throw new BattleshipIOException("Error en createrPlater buscando el archivo: "+e);
		}
	}
}

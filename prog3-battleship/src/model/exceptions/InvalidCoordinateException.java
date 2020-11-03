package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
public class InvalidCoordinateException extends BattleshipException {

	public InvalidCoordinateException(Coordinate c) {
		super(c);
	}

	public String getMessage() {
		return super.getMessage() +" coordenada no valida porque está fuera de los límites del tablero.";
	}
	
}

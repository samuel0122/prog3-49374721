package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
public class OccupiedCoordinateException extends BattleshipException {

	public OccupiedCoordinateException(Coordinate c) {
		super(c);
	}
	
	public String getMessage() {
		return super.getMessage() +" coordenada no valida porque tiene una posición ya ocupada.";
	}

}

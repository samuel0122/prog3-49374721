package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
public class CoordinateAlreadyHitException extends BattleshipException {

	public CoordinateAlreadyHitException(Coordinate c) {
		super(c);
	}
	
	public String getMessage() {
		return super.getMessage() +" coordenada no valida porque ya fue golpeada previamente.";
	}

}

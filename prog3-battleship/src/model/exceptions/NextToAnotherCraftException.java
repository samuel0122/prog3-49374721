package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
public class NextToAnotherCraftException extends BattleshipException {
	
	public NextToAnotherCraftException (Coordinate c) {
		super(c);
	}
	
	public String getMessage() {
		return super.getMessage() +" coordenada no valida porque está al lado de otro barco.";
	}
	
}

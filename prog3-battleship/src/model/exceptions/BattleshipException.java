package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
public abstract class BattleshipException extends java.lang.Exception {
	
	private Coordinate c;
	
	public BattleshipException(Coordinate c) {
		this.c = c;
	}
	
	public String getMessage() {
		return "Error en "+c.toString()+":";
	}
	
}

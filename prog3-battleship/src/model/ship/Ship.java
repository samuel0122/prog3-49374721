package model.ship;

import model.Orientation;

public abstract class Ship extends model.Craft {
	public Ship(Orientation o, char s, String n) {
		super(o, s, n);
	}
}

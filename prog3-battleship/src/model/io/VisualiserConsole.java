package model.io;

import model.Game;

public class VisualiserConsole implements IVisualiser {

	private Game game;
	
	public VisualiserConsole (Game g) {
		this.game=g;
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void close() {
		
	}
	
}

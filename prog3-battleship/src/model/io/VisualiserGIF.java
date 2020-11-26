package model.io;

import model.Game;
import model.io.gif.AnimatedGIF;

public class VisualiserGIF implements IVisualiser {
	
	private Game game;
	private AnimatedGIF agif;
	
	public VisualiserGIF(Game g) {
		this.game=g;
	}
	
	@Override
	public void show() {
		
	}
	
	@Override
	public void close() {
	}
}

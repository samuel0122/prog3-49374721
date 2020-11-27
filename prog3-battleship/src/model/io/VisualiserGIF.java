/**
 * @author Samuel Oliva Bulpitt
 */
package model.io;

import java.awt.Color;
import java.io.File;
import java.util.Objects;

import model.Board;
import model.Game;
import model.exceptions.io.BattleshipIOException;
import model.io.gif.*;
import model.ship.Board2D;

/**
 *Clase VisualiserGIF, permite crear GIF animados de la partida.
 */
public class VisualiserGIF implements IVisualiser {
	/** Game game, guarda la partida a representar */
	private Game game;
	/** AnimatedGID agif, donde guardaremos los frames */
	private AnimatedGIF agif;
	
	/**
	 * Constructor de la clase VisualiserGIF
	 * @param Game g
	 */
	public VisualiserGIF(Game g) {
		Objects.requireNonNull(g);
		this.game=g;
		agif = new AnimatedGIF();
	}
	
	/**
	 * Metodo show, guarda en agif el frame del estado de la partida actual. Dibuja el board1
	 * encima del board2, sin importar que sean 2D o 3D.
	 */
	@Override
	public void show() {
		Board board1=game.getBoard1();
		Board board2=game.getBoard2();
		
		int h, w;
		h = board1.getSize(); //Alto
		
		//Ancho del board
		if(board1 instanceof Board2D)
			w = h;
		else  //Si es 3D... Tamaño^2 + Tamaño-1 (para separadores)
			w = h*h+h-1;

		try{
			//Frame del ancho del board y alto*2+1 para dibujar un board debajo del otro con una linea espaciadora
			FrameGIF frame = new FrameGIF(w, h*2+1);
			//Dibuja board 1
			String tabla = board1.show(false);
			for(int j=0; j < h; j++) {
				for(int i=0; i < w; i++) {
					char c = tabla.charAt(i+w*j);
					switch (c) {
						case Board.BOARD_SEPARATOR: frame.printSquare(i, j, Color.ORANGE);
							break;
						case Board.HIT_SYMBOL: frame.printSquare(i, j, Color.RED);
							break;
						case Board.NOTSEEN_SYMBOL: frame.printSquare(i, j, Color.LIGHT_GRAY);
							break;
						default: frame.printSquare(i, j, Color.BLUE);
							break;
					}
				}
			}
			//Dibuja linea entre ambas tablas
			for(int i=0; i < w; i++) {
				frame.printSquare(i, h, Color.DARK_GRAY);
			}
			//Dibuja board 2
			tabla = board2.show(false);
			for(int j=0; j < h; j++) {
				for(int i=0; i < w; i++) {
					char c = tabla.charAt(i+w*j);
					switch (c) {
						case Board.BOARD_SEPARATOR: frame.printSquare(i, j+h+1, Color.ORANGE);
							break;
						case Board.HIT_SYMBOL: frame.printSquare(i, j+h+1, Color.RED);
							break;
						case Board.NOTSEEN_SYMBOL: frame.printSquare(i, j+h+1, Color.LIGHT_GRAY);
							break;
						default: frame.printSquare(i, j+h+1, Color.BLUE);
							break;
					}
				}
			}
			
			//Guarda el frame
			agif.addFrame(frame);
			
		} catch (BattleshipIOException e) { //Si lanza excepcion, lo cambio a RuntimeException
			
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Metodo Close, guarda el agif actual en un fichero.gif
	 */
	@Override
	public void close() {
		try {
			agif.saveFile(new File("files/output.gif"));
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}

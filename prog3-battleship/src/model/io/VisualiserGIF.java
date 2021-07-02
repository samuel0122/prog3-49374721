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
 * Clase que permite crear un GIF animado que representa la partida.
 */
public class VisualiserGIF implements IVisualiser {
	/** Partida a representar */
	private Game game;
	/** GIF donde guardaremos los frames */
	private AnimatedGIF agif;
	
	/**
	 * Constructor de la clase VisualiserGIF.
	 * 
	 * @param g Partida a convertir en GIF.
	 * @throws NullPointerException Si el game introducido es nulo.
	 */
	public VisualiserGIF(Game g) {
		Objects.requireNonNull(g);
		this.game=g;
		agif = new AnimatedGIF();
	}
	
	/**
	 * Guarda el frame del turno de la partida representando board1 encima de board2.
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
					char c = tabla.charAt(i+w*j+j);
					//charAt(eje x + tamaño ancho*j + j para saltarse los \n que hay uno por j
					switch (c) {
						case Board.BOARD_SEPARATOR: 
							frame.printSquare(i, j, Color.ORANGE);
							break;
						case Board.WATER_SYMBOL: 
							frame.printSquare(i, j, Color.BLUE);
							break;
						case Board.NOTSEEN_SYMBOL:
							frame.printSquare(i, j, Color.LIGHT_GRAY);
							break;
						default: 
							frame.printSquare(i, j, Color.RED);
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
					
					char c = tabla.charAt(i+w*j+j);
					
					switch (c) {
						case Board.BOARD_SEPARATOR: 
							frame.printSquare(i, j+h+1, Color.ORANGE);
							break;
						case Board.WATER_SYMBOL: 
							frame.printSquare(i, j+h+1, Color.BLUE);
							break;
						case Board.NOTSEEN_SYMBOL:
							frame.printSquare(i, j+h+1, Color.LIGHT_GRAY);
							break;
						default: 
							frame.printSquare(i, j+h+1, Color.RED);
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
	 * Guarda el agif actual en un fichero.
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

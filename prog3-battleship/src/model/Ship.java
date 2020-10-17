package model;

import java.util.*;

public class Ship {
	
	private static int BOUNDING_SQUARE_SIZE = 5;
	private static int HIT_VALUE = -1;
	private static int CRAFT_VALUE = 1;
	
	private Orientation orientation; //North, East...
	private char symbol; //Caracter que representa al barco
	private String name; //Nombre del barco
	private Coordinate position;
	
	private int shape[][] = new int[][] {
        { 0, 0, 0, 0, 0,               // NORTH    ·····
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ..#..
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // EAST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###·
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // SOUTH    ·····
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ..#..
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // WEST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###·
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0}};             //          ·····
	
    /*
     * Constructor de la clase Ship
     */
          
	public Ship(Orientation o, char s, String n) {
		orientation = o;
		symbol = s;
		name = n;
		position = null;
	}
	
	/*
	 * Devuelve copia defensiva de la posicion del barco o null si no se ha asignado
	 */
	//Hecho
	public Coordinate getPosition() {
		if(position!=null) {
			Coordinate pos = position.copy();
			return pos;
		}
			
		return null;
	}
	
	//Hecho
	public void setPosition(Coordinate position) {
		this.position = new Coordinate(position);
	}
	
	//Hecho
	public String getName() {
		return name;
	}
	
	//Hecho
	public Orientation getOrientation() {
		return orientation;
	}
	
	//Hecho
	public char getSymbol() {
		return symbol;
	}
	
	//Hecho
	public int [][] getShape(){
		return shape;
	}
	
	private Coordinate getRelativePosition (Coordinate c) {
		Coordinate cRel = new Coordinate (c.get(0) - position.get(0), c.get(1) - position.get(1));
		
		return cRel;
	}
	//Mio
	private boolean comprobarCoord(Coordinate c) {
		if(c.get(0)>=0 && c.get(0)<BOUNDING_SQUARE_SIZE && c.get(1)>=0 && c.get(1)<BOUNDING_SQUARE_SIZE)
			return true;
		
		return false;
	}
	
	//Hecho 
	public int getShapeIndex(Coordinate c) {
		if(comprobarCoord(c)) 
			return c.get(1)*BOUNDING_SQUARE_SIZE+c.get(0);
		
		return 0;
	}
	
	//Hecho
	public Set<Coordinate> getAbsolutePositions(Coordinate c){
		Set<Coordinate> positions = new HashSet<Coordinate>();
		
		for(int i=0; i < BOUNDING_SQUARE_SIZE*BOUNDING_SQUARE_SIZE ; i++) { //Recorre todos los valores de shape
			
			if(shape[orientation.ordinal()][i] == CRAFT_VALUE || shape[orientation.ordinal()][i]==HIT_VALUE) { //Si una coordenada tiene el valor de barco, lo guarda
				int coordX = c.get(0) + i/BOUNDING_SQUARE_SIZE;
				int coordY = c.get(1) + i%BOUNDING_SQUARE_SIZE;
				positions.add(new Coordinate(coordX, coordY));
			}
		}
		
		return positions;
	}
	
	//Hecho
	public Set<Coordinate> getAbsolutePositions(){
		return getAbsolutePositions(position);
	}
	
	//Hecho
	public boolean hit (Coordinate c) {
		
		int pos = getShapeIndex(getRelativePosition(c));
		if(shape[orientation.ordinal()][pos] == CRAFT_VALUE) {
			shape[orientation.ordinal()][pos] = HIT_VALUE;
			return true;
		}
		return false;
		
	}
	
	//Hecho
	public boolean isShotDown() {
		
		for(int i=0; i < BOUNDING_SQUARE_SIZE*BOUNDING_SQUARE_SIZE ; i++) { //Recorre todos los valores de shape
			
			if(shape[orientation.ordinal()][i] == CRAFT_VALUE) { //Si una coordenada tiene el valor de barco, aun no ha caido
				return false;
			}
		}
		return true;
		
	}
	
	//Hecho
	public boolean isHit(Coordinate c) {
		
		int pos = getShapeIndex(getRelativePosition(c));
		if(shape[orientation.ordinal()][pos] == HIT_VALUE)
			return true;
		return false;
	}
	
	//Hecho
	public String toString() {
		String dibujo = " ";
		for (int i=0; i < BOUNDING_SQUARE_SIZE; i++) {
			dibujo += "-";
		}
		
		dibujo += "\n";
		for(int i=0; i < BOUNDING_SQUARE_SIZE; i++) {
			dibujo += "|";
			for(int j=0; j < BOUNDING_SQUARE_SIZE; j++) {
				if(shape[orientation.ordinal()][i*BOUNDING_SQUARE_SIZE+j] == CRAFT_VALUE) {
					dibujo += symbol;
				} else if (shape[orientation.ordinal()][i*BOUNDING_SQUARE_SIZE+j] == HIT_VALUE) {
					dibujo += Board.HIT_SYMBOL;
				} else {
					dibujo += Board.WATER_SYMBOL;
				}
				
			}
			dibujo += "|\n";
		}
		
		dibujo += " ";
		
		for (int i=0; i < BOUNDING_SQUARE_SIZE; i++) {
			dibujo += "-";
		}
		
		dibujo += "\n";
		return dibujo;
	}
	
}

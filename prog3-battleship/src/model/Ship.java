package model;

import java.util.*;

public class Ship {
	
	private int BOUNDING_SQUARE_SIZE = 5;
	private int HIT_VALUE = -1;
	private int CRAFT_VALUE = 1;
	
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
	}
	
	/*
	 * Devuelve copia defensiva de la posicion del barco o null si no se ha asignado
	 */
	//####################################################33
	public Coordinate getPosition() {
		
		if(position.hashCode()!=0) {
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
	
	//################################ FALTA AÑADIR SI ES UNA CASILLA CHOCADA
	public Set<Coordinate> getAbsolutePositions(Coordinate c){
		Set<Coordinate> positions = new HashSet<Coordinate>();
		for(int i=0; i < BOUNDING_SQUARE_SIZE*BOUNDING_SQUARE_SIZE ; i++) { //Recorre todos los valores de shape
			if(shape[orientation.ordinal()][i]==CRAFT_VALUE || shape[orientation.ordinal()][i]==HIT_VALUE) { //Si una coordenada tiene el valor de barco, lo guarda
				positions.add(new Coordinate(c.get(0)+i/BOUNDING_SQUARE_SIZE, c.get(1)+i%BOUNDING_SQUARE_SIZE));
			}
		}
		
		return positions;
	}
	
	//Hecho
	public Set<Coordinate> getAbsolutePositions(){
		return getAbsolutePositions(position);
	}
	
	//Creo que esta hecho
	public boolean isShotDown() {
		if(getAbsolutePositions(position).isEmpty())
			return true;
		return false;
	}
	
	//Creo que esta hecho
	public boolean isHit(Coordinate c) {
		int i = getShapeIndex(c);
		if(shape[orientation.ordinal()][i]==HIT_VALUE)
			return true;
		return false;
	}
	
	
	public String toString() {
		return null;
	}
	
}

package model;

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
	
	public Coordinate getPosition() {
		Coordinate pos = position.copy();
		if(pos.hashCode()!=0)
			return pos;
		return null;
	}
	
	public void setPosition(Coordinate position) {
		this.position = new Coordinate(position);
	}
	
	public String getName() {
		return null;
	}
	
	public Orientation getOrientation() {
		return null;
	}
	
	public char getSymbol() {
		return 'p';
	}
	
	public int [][] getShape(){
		return null;
	}
	
	public int getShapeIndex(Coordinate c) {
		return 0;
	}
	
	public Set<Coordinate> getAbsolutePositions(Coordinate c){
		
	}
	
	public Set<Coordinate> getAbsolutePositions(){
		
	}
	
	public boolean isShotDown() {
		return false;
	}
	
	public boolean isHit(Coordinate c) {
		return false;
	}
	
	
	public String toString() {
		return null;
	}
	
}

package model;

import java.util.*;

public class Board {

	public static char HIT_SYMBOL = '•';
	public static char WATER_SYMBOL = ' ';
	public static char NOTSEEN_SYMBOL = '?';
	private static int MAX_BOARD_SIZE = 20;
	private static int MIN_BOARD_SIZE = 5;
	private int size;
	private int numCrafts;
	private int destroyedCrafts;
	
	private Set<Coordinate> seen = new HashSet<Coordinate>(); //Coordenadas que el otro ha visto
	private Map<Coordinate, Ship> board = new HashMap<Coordinate, Ship>();
	
	
	public Board(int size) {
		if(size < MIN_BOARD_SIZE && size > MAX_BOARD_SIZE) {
			System.err.println("ERROR: Incorrect size. Setting minimum size: "+MIN_BOARD_SIZE+" .");
			this.size=MIN_BOARD_SIZE;
			seen = null;
			board = null;
		}
		
		this.size=size;
	}
	
	public int getSize() {
		return size;
	}
	
	//Si está entre minimo y maximo, true
	public boolean checkCoordinate(Coordinate c) {
		if(c.get(0)<size && c.get(0)>=0 && c.get(1)<size && c.get(1)>=0)
			return true;
		return false;
	}
	
	public boolean addShip(Ship ship, Coordinate c) {
		if(checkCoordinate(c)) {
			System.err.println("ERROR: Incorrect size.");
		} else {
			
			board.put(c, ship);
			return true;
		}
		
		return false;
		
	}
	
	public Ship getShip(Coordinate c) {
		return null;
	}
	
	public boolean isSeen(Coordinate c) {
		return false;
	}
	
	public CellStatus hit (Coordinate c) {
		return WATER;
	}
	
	public boolean areAllCraftDestroyed() {
		if(destroyedCrafts == numCrafts)
			return true;
		return false;
	}
	
	public Set<Coordinate> getNeighborhood(Ship ship, Coordinate position){
		
	}
	
	public Set<Coordinate> getNeighborhood(Ship ship){
		
	}
	
	public String show(boolean unveil) {
		return "HOLA";
	}
	
	public String toString() {
		return "HOLA";
	}
		
}

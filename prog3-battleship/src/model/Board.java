package model;

import java.util.*;

public class Board {
//HACER  ADDSHIP
	public static final char HIT_SYMBOL = '•';
	public static final char WATER_SYMBOL = ' ';
	public static final char NOTSEEN_SYMBOL = '?';
	private static final int MAX_BOARD_SIZE = 20;
	private static final int MIN_BOARD_SIZE = 5;
	private int size;
	private int numCrafts;
	private int destroyedCrafts;
	
	private Set<Coordinate> seen = new HashSet<Coordinate>(); //Coordenadas que el otro ha visto
	private Map<Coordinate, Ship> board = new HashMap<Coordinate, Ship>();
	
	//Hecho
	public Board(int size) {
		numCrafts = 0;
		destroyedCrafts = 0;
		/*seen = null;
		board = null;*/
		
		if(size < MIN_BOARD_SIZE && size > MAX_BOARD_SIZE) {
			this.size=MIN_BOARD_SIZE;
			System.err.println("ERROR: Board(); Tamaño incorrecto. Aplicando el tamaño mínimo: "+MIN_BOARD_SIZE+".");
			
		} else {
			this.size=size;
		}

	}
	
	//Hecho
	public int getSize() {
		return size;
	}
	
	//Hecho
	public boolean checkCoordinate(Coordinate c) {
		if(c.get(0)<size && c.get(0)>=0 && c.get(1)<size && c.get(1)>=0)
			return true;
		return false;
	}
	
	//################################################################
	public boolean addShip(Ship ship, Coordinate c) {
		Set <Coordinate> positions = ship.getAbsolutePositions(c);
		
		for(Coordinate pos : positions) {
			
			if(!checkCoordinate(pos)) { //Si la coord esta fuera del tablero
				System.err.println("ERROR: Board.addShip(); Una posición del barco está fuera del tablero.");
				return false;
			} else if(getShip(pos) != null){ //Si la coord ya está ocupada
				System.err.println("Error: Board.addShip(); Una posición del barco ya está ocupada.");
				return false;
				
			} else {
				
				for(Coordinate posVecinos : pos.adjacentCoordinates()) {
					if(getShip(posVecinos) != null){ //Si la coord ya está ocupada
						System.err.println("Error: Board.addShip(); Una posición al rededor del barco ya está ocupada.");
						return false;
					}
				}
				
			}
			
		}
		ship.setPosition(c);
		
		board.put(c, ship);
		numCrafts ++;
		return true;
		
		
	}
	
	//Hecho 
	/*
	 * Duelve el barco que tenga alguna posicion en la coordenada que pide
	 */
	public Ship getShip(Coordinate c) {
		if(numCrafts > 0) {
			Set <Coordinate> TodosCoord = board.keySet(); //Copio todas las coord donde hay barco
		
			for(Coordinate CoordBarco : TodosCoord) { //Recorro todas las coord
				
				//De cada barco, copio todas sus posiciones
				Set <Coordinate> todosPositions = board.get(CoordBarco).getAbsolutePositions();
				
				//Recorro todas sus posiciones
				for( Coordinate pos : todosPositions) {
					
					//Si alguna de esas posiciones es igual a la coordenada que preguntan
					if(pos.equals(c)) {
						
						//Devuelvo el barco con la posicion que coincide
						return board.get(CoordBarco);
					}
				}
			}
		}
		
		return null;
	}
	
	//Hecho
	public boolean isSeen(Coordinate c) {
		if(seen != null && seen.contains(c)) {
			return true;
		}
		return false;
	}
	
	//Hecho
	public CellStatus hit (Coordinate c) {
		seen.add(c);
		
		if(!checkCoordinate(c)) { //Si está fuera del tablero imprime error
			System.err.println("ERROR: Board.hit(); Coordenada fuera del tablero.");
			return CellStatus.WATER;
		}
		
		Ship barco = getShip(c); //Cojo el barco que tiene esa coordenada
		
		if(barco != null) { //Si hay algun barco
			if(barco.hit(c)) { //Y si consigo golpearle
				if(barco.isShotDown()) { //Y lo destruyo
					
					//Añado todos sus vecinos al seen
					Set <Coordinate> vecinosDescubiertos = getNeighborhood(barco);
					for(Coordinate vecino:vecinosDescubiertos) {
						seen.add(vecino);
					}
					//Y devuelvo destroyed
					destroyedCrafts += 1;
					return CellStatus.DESTROYED;
				}
				//O devuelvo hit si solo le ha golpeado
				return CellStatus.HIT;
			}
		}
		
		//Si no habia barco manda WATER
		return CellStatus.WATER;

	}
	
	//Hecho
	public boolean areAllCraftDestroyed() {
		if(destroyedCrafts == numCrafts)
			return true;
		return false;
	}
	
	//Hecho
	public Set<Coordinate> getNeighborhood(Ship ship, Coordinate position){
		Set<Coordinate> neighbors = new HashSet <Coordinate>();
		
		//Por cada coord del barco
		for(Coordinate posBarco : ship.getAbsolutePositions(position)) {
			
			//Añadir las coordenadas adyacentes
			for(Coordinate coordAdyacentes : posBarco.adjacentCoordinates()) {
				//Si esa coordenada no se sale del tablero
				if(checkCoordinate(coordAdyacentes)) {
					neighbors.add(coordAdyacentes);
				}
			}
		}
		
		//Quitamos las posiciones del barco de la lista de vecinos
		for(Coordinate posBarco : ship.getAbsolutePositions(position)) {
			neighbors.remove(posBarco);
		}
		
		return neighbors;
	}
	
	//Hecho
	public Set<Coordinate> getNeighborhood(Ship ship){
		return getNeighborhood(ship, ship.getPosition());
	}
	
	//################################################################
	public String show(boolean unveil) {
		String tabla = "";
		
		for(int i = 0; i<size; i++) {
			if(i > 0)
				tabla += "\n";
			for (int j = 0; j<size; j++) {
				Coordinate coord = new Coordinate (i, j);
				
				if(unveil) { //Si unveil es true
					
					
					Ship barco = getShip(coord);
					
					if(barco == null) { //Si no hay barco 
						tabla += WATER_SYMBOL;
					} else {
						if(barco.isHit(coord)) { //Si la coord está golpeada
							tabla += HIT_SYMBOL;
						} else { //Si la coordenada no está golpeada
							tabla += barco.getSymbol();
						}
					}
					
				} else { //Si unveil es false
					
					
					if( seen.contains(coord) ) { //Si la coordenada fue vista
						Ship barco = getShip(coord);
						
						if(barco == null) { //Si no hay un barco
							tabla += WATER_SYMBOL;
						} else {
							if(barco.isShotDown()) { //Si el barco está destruido
								tabla += barco.getSymbol();
							} else { //Si el barco no está destruido
								tabla += HIT_SYMBOL;
							}
						}
					} else { //Si la coordenada no fue vista
						tabla += NOTSEEN_SYMBOL;
					}
					
				}
			}
		}
		return tabla;
	}
	
	//Hecho
	public String toString() {
		String info = "Board " + size + "; crafts: "+ numCrafts +"; destroyed: " + destroyedCrafts;
		return info;
	}
		
}

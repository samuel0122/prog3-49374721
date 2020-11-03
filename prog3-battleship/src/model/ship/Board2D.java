package model.ship;
/*
 * @author Samuel Oliva Bulpitt, 49374721
 */


import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;

/**
 * The Class Board.
 */
public class Board2D extends Board {

	/**
	 * Instantiates a new board. Comienza poniendo a 0 el numero de navios creados y destruidos y
	 * comprueba que el tamaño seleccionado para el tablero es correcto, si no pone el minimo.
	 *
	 * @param size the size
	 */
	public Board2D(int size) { super(size); }
	
	/**
	 * Check coordinate. Comprueba si está dentro del tamaño del tablero,
	 * devuelve true si lo está o falso si no lo está
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	@Override
	public boolean checkCoordinate(Coordinate c) {
		if(c instanceof Coordinate2D) {
			if(c.get(0)<this.getSize() && c.get(0)>=0 && c.get(1)<this.getSize() && c.get(1)>=0)
				return true;
			return false;
		} else 
			throw new IllegalArgumentException();
		
	}
	
	
	/**
	 * Show. Devuelve el tablero con todos los barcos e interacciones representadas
	 * ocultando las coordenadas no conocidas si es false o mostrando todo si es true, imitando
	 * las vistas de tablero propio o tablero rival.
	 *
	 * @param unveil the unveil
	 * @return the string
	 */
	public String show(boolean unveil) {
		String tabla = "";
		
		for(int i = 0; i<this.getSize(); i++) {
			if(i > 0)
				tabla += "\n";
			for (int j = 0; j<this.getSize(); j++) {
				int nCoord [] = {j, i};
				Coordinate coord = CoordinateFactory.createCoordinate( nCoord);
				
				if(unveil) { //Si unveil es true devuelve vista de tablero propio
					
					
					Craft barco = barcoQueOcupa(coord);
					
					if(barco == null) { //Si no hay barco coloca el simbolo de WATER
						tabla += WATER_SYMBOL;
					} else {
						if(barco.isHit(coord)) { //Si la posicion del ship está golpeada coloca el simbolo de HIT
							tabla += HIT_SYMBOL;
						} else { //Si la posicion del ship no está golpeada coloca su simbolo
							tabla += barco.getSymbol();
						}
					}
					
				} else { //Si unveil es false devuelve vista de tablero rival
					
					
					if( this.isSeen(coord) ) { //Si la coordenada es conocida
						Craft barco = barcoQueOcupa(coord);
						
						if(barco == null) { //Si la coordenada es conocida y no hay ship coloca WATER
							tabla += WATER_SYMBOL;
						} else {
							if(barco.isShotDown()) { //Si la coordenada es conocida y hay un ship destruido coloca su simbolo
								tabla += barco.getSymbol();
							} else { //Si la coordenada es conocida y hay un ship golpeado coloca HIT
								tabla += HIT_SYMBOL;
							}
						}
					} else { //Si la coordenada no es conocida coloca NOTSEEN
						tabla += NOTSEEN_SYMBOL;
					}
					
				}
			}
		}
		return tabla;
	}

	
		
}

/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.ship;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;

/**
 * Clase Board2D, subclase de Board
 */
public class Board2D extends Board {

	/**
	 * Constructor Board2D que llama al constructor de Board con el tamaño.
	 *
	 * @param size the size
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public Board2D(int size) { super(size); }
	
	/**
	 * CheckCoordinate comprueba que la coordenada está dentro de  los límites del tablero 2D.
	 *
	 * @param c the c
	 * @return true, if successful
	 * @throws IllegalArgumentException the illegal argument exception
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
	 * Show Board3D. Crea String con la representación del tablero. Si unveil es true muestra todas las posiciones, si es false muestra las coordenadas guardadas en seen
	 *
	 * @param unveil the unveil
	 * @return String tablero2D
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
					
					
					Craft barco = getCraft(coord);
					
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
						Craft barco = getCraft(coord);
						
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
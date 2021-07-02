/**
  	@author Samuel Oliva Bulpitt
 
 */

package model.ship;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;

/**
 * Tablero 2D.
 */
public class Board2D extends Board {

	/**
	 * Constructor Board2D.
	 *
	 * @param size Tamaño del tablero.
	 * @throws IllegalArgumentException Si el tamaño no es el permitido.
	 */
	public Board2D(int size) { super(size); }
	
	/**
	 * Comprueba que la coordenada está dentro de  los límites del tablero 2D.
	 *
	 * @param c Coordenada2D.
	 * @return true, si es una coordenada valida.
	 * @throws IllegalArgumentException Si el parametro no es una coordenada 2D.
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
	 * Representa el tablero en modo oculto o mostrando todos los elementos dependiendo de unveil.
	 * 
	 * @param unveil True si se quiere mostrar todos los elementos del tablero.
	 * @return Representacion del tablero2D.
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
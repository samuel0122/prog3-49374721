package model.aircraft;

import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;

public class Board3D extends model.ship.Board2D {

	public Board3D(int size) { super(size); }

	public boolean checkCoordinate(Coordinate c) {
		if(c instanceof Coordinate3D) {
			if((c.get(0)<this.getSize() && c.get(0)>=0)   &&   (c.get(1)<this.getSize() && c.get(1)>=0)   &&   (c.get(2)<this.getSize() && c.get(2)>=0))
				return true;
			return false;
		} else 
			throw new IllegalArgumentException();
		
	}
	
	public String show(boolean unveil) { //Comprobar barcoQueOcupa();
		String tabla = "";
		
		for(int j = 0; j<this.getSize(); j++) { //Por último las filas
			if(j > 0)
				tabla += "\n";
			for(int k=0; k<this.getSize(); k++) { //Luego va las capas
				for (int i = 0; i<this.getSize(); i++) { //Primero los x (izquierda a derecha)
					int nCoord [] = {i, j, k};
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
				tabla += BOARD_SEPARATOR;
			}
		}
		return tabla;
	}
}


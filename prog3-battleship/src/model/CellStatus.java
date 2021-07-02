/**
  	@author Samuel Oliva Bulpitt
 */
package model;

/**
 * Enum que establece los estados de las casillas del board.
 */
public enum CellStatus {
    /** Estado agua de una casilla del board. */
    WATER,
    /** Estado navio golpeado de una casilla del board. */
    HIT, 
    /** Estado navio destruido de una casilla del board. */
    DESTROYED;
}


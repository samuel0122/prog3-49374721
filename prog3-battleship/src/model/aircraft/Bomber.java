package model.aircraft;

import model.Orientation;

public class Bomber extends Aircraft {

	public Bomber (Orientation o) {
		super(o, '⇶', "Bomber");
		
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	1, 1, 1, 1, 1,	
		    	1, 0, 1, 0, 1,
		    	0, 0, 1, 0, 0},
		      { 	0, 1, 1, 0, 0,
		    		0, 0, 1, 0, 0,	
		    		1, 1, 1, 1, 0,	
		    		0, 0, 1, 0, 0,
		    		0, 1, 1, 0, 0},
		      { 0, 0, 1, 0, 0,
		    	1, 0, 1, 0, 1,	
		    	1, 1, 1, 1, 1,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 1, 1, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 1,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 1, 0}};
	}

}

package mains;

import model.Coordinate;
import model.Orientation;
import model.Ship;
import model.ship.Board2D;

public class MainP2_partida3 {

	public static void main(String[] args) {
				
		Board2D bplayer1 = new Board2D(10);
		Ship s1p1 = new Ship(Orientation.EAST,'P',"Dijkstra");
		Ship s2p1 = new Ship(Orientation.NORTH,'s',"Boole");
		Ship s3p1 = new Ship(Orientation.EAST,'d',"Knuth");
		bplayer1.addCraft(s1p1,new Coordinate(0,0));
		bplayer1.addCraft(s2p1,new Coordinate(5,-1));
		s2p1.getPosition();
		System.out.println(bplayer1.board.get(new Coordinate(5,-1)));
		System.out.println(s2p1.getPosition());
		bplayer1.addCraft(s3p1,new Coordinate(2,3));
		
		System.out.println("(1) Player 1:" + bplayer1);
		System.out.println(bplayer1.show(true));
		
		Board2D bplayer2 = new Board2D(10);
		Ship s1p2 = new Ship(Orientation.SOUTH,'X',"X-wing");
		Ship s2p2 = new Ship(Orientation.WEST,'M',"Millenium Falcon");
		Ship s3p2 = new Ship(Orientation.NORTH,'C',"Corellian cruiser");
		bplayer2.addCraft(s1p2,new Coordinate(0,0));
		bplayer2.addCraft(s2p2,new Coordinate(5,5));
		
		//Error  de está al lado
		bplayer2.addCraft(s3p2,new Coordinate(1,1));
		System.out.println(s3p2.getAbsolutePositions(new Coordinate(1,1)));

		System.out.println("(1) Player 2:"+bplayer2);
		System.out.println(bplayer2.show(true));
		
		//Error ya está ocupado
		bplayer2.addCraft(s3p2,new Coordinate(0,2));
		System.out.println("(2) Player 2:"+bplayer2);
		System.out.println(bplayer2.show(true));
		
		//Error fuera del tablero
		bplayer2.addCraft(s3p2,new Coordinate(9,0));
		System.out.println("(3) Player 2:"+bplayer2);
		System.out.println(bplayer2.show(true));
		
		bplayer2.hit(new Coordinate(0,0));
		bplayer1.hit(new Coordinate(1,1));
		bplayer2.hit(new Coordinate(2,2));
		bplayer2.hit(new Coordinate(3,3));
		bplayer1.hit(new Coordinate(5,5));
		bplayer2.hit(new Coordinate(2,3));
		bplayer2.hit(new Coordinate(2,1));

		System.out.println("-------------------");
		System.out.println("Player 1:" + bplayer1);
		System.out.println(bplayer1.show(true));
		System.out.println(bplayer1.show(false));
		
		System.out.println("-------------------");
		System.out.println("Player 2:" + bplayer2);
		System.out.println(bplayer2.show(true));
		System.out.println(bplayer2.show(false));
		
		System.out.println("-------------------");
		System.out.println(s3p1);
		System.out.println(s1p2);
	}
}
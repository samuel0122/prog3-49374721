package mains;

import model.*;
import model.aircraft.Board3D;
import model.exceptions.*;
import model.exceptions.io.BattleshipIOException;
import model.io.*;
import model.ship.*;

public class MainP4 {

	private static void mainBoard3DPlayerFile() {
		IPlayer player1 = null;
		IPlayer player2 = null;
		Board b1 = new Board3D(6);
		Board b2 = new Board3D(6);
		
		try {
			player1 = PlayerFactory.createPlayer("John","files/playerfile-john.txt");
			player2 = PlayerFactory.createPlayer("Mary","files/playerfile-mary.txt");
		} catch (BattleshipIOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			player1.putCrafts(b1);
			player2.putCrafts(b2);
		} catch (InvalidCoordinateException | NextToAnotherCraftException | OccupiedCoordinateException	| BattleshipIOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		// We process all shots by player 1 at board 2
		try {
			Coordinate c;
			do {
				c=player1.nextShoot(b2);
				if (c!=null) {
					System.out.println(c);
					System.out.println(b2.show(false));
					System.out.println("--------------------------------");
				}
			} while(c != null);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException | BattleshipIOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("=============================");
		
		// We process all shots by player 2 at board 1
		try {
			Coordinate c;
			do {
				c=player2.nextShoot(b1);
				if (c!=null) {
					System.out.println(c);
					System.out.println(b1.show(false));
					System.out.println("--------------------------------");
				}
			} while(c != null);
		} catch (CoordinateAlreadyHitException | InvalidCoordinateException | BattleshipIOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("=============================");
		System.out.println(b1.show(true));
		System.out.println("--------------------------------");
		System.out.println(b2.show(true));
	}
	
	
	public static void main(String[] args) {
		mainBoard3DPlayerFile();
		
		try {
			IPlayer player1 = PlayerFactory.createPlayer("John","files/playerfile-john.txt");
			IPlayer player2 = PlayerFactory.createPlayer("Mary","files/playerfile-mary.txt");
			IPlayer player3 = PlayerFactory.createPlayer("Jose", "2423432");
			IPlayer player4 = PlayerFactory.createPlayer("Antonia", "142234");
			Board b1 = new Board3D(6);
			Board b2 = new Board3D(6);
			Board b3 = new Board2D(8);
			Board b4 = new Board2D(8);
			
			Game game = new Game(b1, b2, player1, player2);
			Game game2 = new Game(b3, b4, player3, player4);
			
			IVisualiser visualiser = VisualiserFactory.createVisualiser("Console", game);
			IVisualiser visualiser2 = VisualiserFactory.createVisualiser("GIF", game2);
			
			game.playGame(visualiser);
			game2.playGame(visualiser2);
			
			System.out.println("Done");
		} catch (BattleshipIOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
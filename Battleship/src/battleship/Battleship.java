package battleship;

import java.util.Scanner;
import java.util.Random;

public class Battleship {
	// in the gameArray a value of zero indicates an empty square
	// 1 indicates a square occupied by a player ship
	// 2 indicates a square occupied by a computer ship
	// 3 indicates a square where a ship has been destroyed.
	
	private int[][] gameArray;

	// starts a new game, used from the launcher
	public void start() {
		createGameArray();
		drawGrid();
		placePlayerShips();
		placeComputerShips();

	}

	// method for populating game array
	private void createGameArray() {
		gameArray = new int[10][10];
	}

	// method for drawing grid from game array
	private void drawGrid() {
		int counter = 0;
		StringBuilder line;
		String display;
		System.out.println("   0123456789   ");
		for (int row = 0; row < gameArray.length; row++) {
			line = new StringBuilder();
			line.append(Integer.toString(counter) + " |");
			for (int col = 0; col < gameArray[row].length; col++) {
				switch (getSquareValue(row, col)) {
				case 1:
					display = "@";
					break;
				//temp case for development
				case 2:
					display = "C";
					break;
				case 3:
					display = "X";
					break;
				default:
					display = " ";
					break;
				}
				line.append(display);
			}
			line.append("| " + Integer.toString(counter));
			counter++;
			System.out.println(line);
		}
		System.out.println("   0123456789   ");
	}

	// method for checking value of an individual grid square
	private int getSquareValue(int x, int y) {
		return gameArray[x][y];
	}

	// method for changing a grid square's value
	private void setSquareValue(int x, int y, int val) {
		gameArray[x][y] = val;
	}


	// method for allowing player to place five ships
	private void placePlayerShips() {
		Scanner input = new Scanner(System.in);
		int x;
		int y;
		for (int i = 1; i < 6; i++) {
			System.out.println("Enter X coordinate for your ship: ");
			x = input.nextInt();
			System.out.println("Enter Y coordinate for your ship: ");
			y = input.nextInt();
			setSquareValue(x, y, 1);
			drawGrid();
			System.out.println("You have placed " + i + " of 5 ships.");
		}
		input.close();
	}
	
	// method for generating random numbers
	private int getRandom(int min, int max) {
		Random rand = new Random();
		int result = rand.nextInt(max) + min;
		return result;
	}
	
	//method for placing computer ships
	private void placeComputerShips() {
		int x;
		int y;
		for(int i = 1; i < 6; i++) {
			x = getRandom(0,9);
			y = getRandom(0,9);
			setSquareValue(x,y,2);
			// wait a bit for extra drama
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			// then alert player
			System.out.println(i+". computer ship DEPLOYED");
			
		}
		drawGrid();
	}
}

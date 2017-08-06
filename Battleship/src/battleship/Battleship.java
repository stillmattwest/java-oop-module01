package battleship;

import java.util.Scanner;

public class Battleship {

	private int[][] gameArray;

	// starts a new game, used from the launcher
	public void start() {
		createGameArray();
		drawGrid();
		placePlayerShips();

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

	// method for deploying a ship
	private void deployShip(int x, int y, int player) {
		setSquareValue(x,y,player);
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
			deployShip(x, y, 1);
			drawGrid();
			System.out.println("You have placed " + i + " of 5 ships.");
		}
		input.close();
	}
}

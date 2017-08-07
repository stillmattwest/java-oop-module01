package battleship;

import java.util.Scanner;
import java.util.Random;

public class Battleship {
	// in the gameArray a value of zero indicates an empty square
	// 1 indicates a square occupied by a player ship
	// 2 indicates a square occupied by a computer ship
	// 3 indicates a square where a ship has been destroyed.

	private int[][] gameArray;
	private boolean gameOver = false;
	Scanner input = new Scanner(System.in);
	int playerShips = 5;
	int computerShips = 5;

	// starts a new game, used from the launcher
	public void start() {
		createGameArray();
		drawGrid();
		placePlayerShips();
		placeComputerShips();
		playerTurn();
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
				// temp case for development
				case 2:
					display = " ";
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
		
		int x;
		int y;
		int squareValue;
		int shipCount = 0;
		while (shipCount < 5) {
			System.out.println("Enter X coordinate for your ship: ");
			x = input.nextInt();
			System.out.println("Enter Y coordinate for your ship: ");
			y = input.nextInt();
			squareValue = getSquareValue(x, y);
			if (squareValue == 0) {
				setSquareValue(x, y, 1);
				shipCount++;
			} else {
				System.out.println("You already have a ship in that square.");
			}
			drawGrid();
			System.out.println("You have placed " + shipCount + " of 5 ships.");
		}
	}

	// method for generating random numbers
	private int getRandom(int min, int max) {
		Random rand = new Random();
		int result = rand.nextInt(max) + min;
		return result;
	}

	// method for placing computer ships
	private void placeComputerShips() {
		int x;
		int y;
		int squareValue;
		int shipCount = 0;
		while (shipCount < 5) {
			x = getRandom(0, 9);
			y = getRandom(0, 9);
			squareValue = getSquareValue(x, y);
			if (squareValue == 0) {
				setSquareValue(x, y, 2);
				shipCount++;
				// wait a bit for extra drama
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				// then alert player
				System.out.println(shipCount + ". computer ship DEPLOYED");
			}
		}
		drawGrid();
	}

	// method for player's turn
	private void playerTurn() {
		int x;
		int y;
		int hit;
		drawGrid();
	    System.out.println("Your ships: "+playerShips+" | Computer Ships: "+computerShips);
		System.out.println("YOUR TURN");
		System.out.println("Enter X Coordinate for target:");
		x = input.nextInt();
		System.out.println("Enter Y Coordinate for target:");
		y = input.nextInt();
		hit = getSquareValue(x, y);
		switch (hit) {
		case 1:
			System.out.println("You just sank one of your OWN ships.");
			System.out.println("Thanks for the help but I'm more than capable of beating you on my own.");
			playerShips--;
			break;
		case 2:
			System.out.println("You sunk one of my ships! This will not go unpunished.");
			computerShips--;
			break;
		case 3:
			System.out.println("Someone already attacked that square. How does it feel to waste a turn?");
			break;
		default:
			System.out.println("You missed.");
			break;
		}
		setSquareValue(x, y, 3);
		isGameOver();
		if (!gameOver) {
			computerTurn();
		} else {
			if(playerShips > 0) {
				System.out.println("YOU WIN. Thanks, gg.");
			}else {
				System.out.println("Okay... suicide is a strategy now, I guess.");
			}
			
			input.close();
		}

	}

	// method for computer's turn

	private void computerTurn() {
		int x = getRandom(0, 9);
		int y = getRandom(0, 9);
		System.out.println("COMPUTER TURN");
		int hit = getSquareValue(x, y);
		switch (hit) {
		case 1:
			System.out.println("Computer attacked square " + x + "," + y);
			System.out.println("Got you!");
			setSquareValue(x, y, 3);
			break;
		case 2:
			computerTurn();
			break;
		case 3:
			computerTurn();
			break;
		default:
			System.out.println("Computer attacked square " + x + "," + y + " and missed.");
			setSquareValue(x, y, 3);
			break;
		}

		isGameOver();
		if (!gameOver) {
			playerTurn();
		} else {
			System.out.println("COMPUTER WINS. It's not your fault, your brain is analog. gg");
			input.close();
		}

	}

	// method for checking if game is over
	private void isGameOver() {
		int playerShips = 0;
		int computerShips = 0;
		int squareValue;
		for (int row = 0; row < gameArray.length; row++) {
			for (int col = 0; col < gameArray[row].length; col++) {
				squareValue = getSquareValue(row, col);
				switch (squareValue) {
				case 1:
					playerShips++;
					break;
				case 2:
					computerShips++;
					break;
				default:
					break;
				}
			}
		}
		if (playerShips == 0 || computerShips == 0) {
			gameOver = true;
		}
	}

}

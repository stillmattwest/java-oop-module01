package battleship;

public class Battleship {

	private int[][] gameArray;

	// add a public start method for launching a new game. This will be called by
	// the launcher
	public void start() {
		createGameArray();
		drawGrid();
		
	}

	// add method for populating game array
	private void createGameArray() {
		gameArray = new int[10][10];
	}
	// add method for drawing grid from game array
	private void drawGrid() {
		int counter = 0;
		StringBuilder line;
		System.out.println("   0123456789   ");
		for(int[] row : gameArray) {
			line = new StringBuilder();
			line.append(Integer.toString(counter)+" |");
			for(int col : row) {
				line.append(Integer.toString(row[col]));
			}
			line.append("| "+Integer.toString(counter));
			counter++;
			System.out.println(line);
		}
		System.out.println("   0123456789   ");
	}

	// add method for checking value of an individual grid square
	private int checkSquareValue(int x, int y) {
		return gameArray[x][y];
	}

	// add method for changing a grid square's value

}

import processing.core.PApplet;

public class Main extends PApplet {
	private int[][] grid;
	int cols;
	int rows;
	int res = 20;
	
	@Override
	public void settings () {
		size(720, 480);
		cols = width/res;
		rows = height/res;
		
		grid = new int[cols][rows];
		for (int i=0; i < cols; i ++) {
			for (int j=0; j < rows; j ++) {
				grid[i][j] = floor(random(2));
			}
		}
	}
	
	public int countNeighbors (int[][] grid, int x, int y) {
		int neighbors = 0;
		for (int i=-1; i < 2; i ++) {
			for (int j=-1; j < 2; j ++) {
				neighbors = grid[x+i][y+j];
			}
		}
		return neighbors;
	}
	
	@Override
	public void draw () {
		background(0);
		stroke(100);
		
		for (int i=0; i < cols; i ++) {
			for (int j=0; j < rows; j ++) {
				int x = i * res;
				int y = j * res;
				if (grid[i][j] == 1) {
					fill(255);
				} else {
					fill(0);
				}
				
				rect(x, y, res, res);
			}
		}
		
		int[][] next = new int[cols][rows];
		
		for (int i=0; i < cols; i ++) {
			for (int j=0; j < rows; j ++) {
				if (i == 0 || i == cols - 1 || j == 0 || j == rows - 1) {
					next[i][j] = grid[i][j];
				} else {
					int neighbors = countNeighbors(grid, i, j);
					int state = grid[i][j];
					neighbors -= state;
					if (state == 0 && neighbors == 3) {
						next[i][j] = 1;
					} else if (state == 1 && (neighbors < 2 || neighbors > 3)) {
						next[i][j] = 0;
					} else {
						next[i][j] = state;
					}
				}
			}
		}
		
		grid = next;
	}
	
	public static void main (String[] args) {
		String[] appletArgs = new String[] {"Main"};
		PApplet.main(appletArgs);
	}
}

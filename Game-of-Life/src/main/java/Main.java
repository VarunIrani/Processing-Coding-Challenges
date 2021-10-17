import processing.core.PApplet;

public class Main extends PApplet {
	public final int res = 20;
	public int cols, rows;
	public Cell[][] grid;
	
	@Override
	public void settings () {
		size(720, 480);
		cols = width/res;
		rows = height/res;
		
		grid = new Cell[cols][rows];
		
		for (int i=0; i < cols; i ++) {
			for (int j=0; j < rows; j ++) {
				if (random(100) < 50) {
					grid[i][j] = new Cell(i, j, res, true);
				} else {
					grid[i][j] = new Cell(i, j, res, false);
				}
			}
		}
	}
	
	public int aliveNeighborsCount (int x, int y) {
		int aNC = 0;
		for (int i=-1; i <= 1; i ++) {
			for (int j =-1; j <= 1; j ++) {
				if ((x+i) >= 0 && (x+i) < cols && (y+j) >= 0 && (y+j) < rows && (x+i) != x && (y+j) != y) {
					if (grid[(x+i)][(y+j)].live)
						aNC ++;
				}
			}
		}
		return aNC;
	}
	
	public Cell[][] oneYearPassed(Cell[][] grid) {
		Cell[][] nextStage = new Cell[cols][rows];
		
		for (int i=0; i < cols; i ++) {
			for (int j=0; j < rows; j ++) {
				int aNC = aliveNeighborsCount(i, j);
				if ((grid[i][j].live && aNC < 2) || (grid[i][j].live && aNC > 3)) {
					nextStage[i][j] = new Cell(i, j, res, false);
				} else if ((grid[i][j].live && aNC == 3) || (grid[i][j].live && aNC == 2)) {
					nextStage[i][j] = new Cell(i, j, res, true);
				} else if (!grid[i][j].live && aNC == 3) {
					nextStage[i][j] = new Cell(i, j, res, true);
				} else {
					nextStage[i][j] = new Cell(i, j, res, grid[i][j].live);
				}
			}
		}
		
		return nextStage;
	}
	
	@Override
	public void draw () {
		background(56);
		
		frameRate(5);
		
		for (Cell[] cells : grid) {
			for (Cell cell : cells) {
				cell.draw(this);
			}
		}
		
		grid = oneYearPassed(grid);
	}
	
	public static void main (String[] args) {
		String[] appletArgs = new String[] {"Main"};
		PApplet.main(appletArgs);
	}
}

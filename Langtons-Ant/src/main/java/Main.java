import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;

public class Main extends PApplet {
	int[][] grid;
	float cr = 0;
	int epochs = 10;
	ArrayList<Ant> ants;
	PImage antImage;
	
	@Override
	public void settings () {
		size(1200, 720);
		grid = new int[width][height];
		ants = new ArrayList<>();
		ants.add(new Ant((int) random(random(100, 500), width-100), (int) random(100, height-100),
				width, height,
				Color.RED));
		ants.add(new Ant((int) random(random(100, 500), width-100), (int) random(100, height-100), width, height,
				Color.BLUE));
		ants.add(new Ant((int) random(random(100, 500), width-100), (int) random(100, height-100), width, height,
				Color.GREEN));
		
		ants.add(new Ant((int) random(random(100, 500), width-100), (int) random(100, height-100), width, height,
				Color.YELLOW));
		ants.add(new Ant((int) random(random(100, 500), width-100), (int) random(100, height-100), width, height,
				Color.MAGENTA));
		ants.add(new Ant((int) random(random(100, 500), width-100), (int) random(100, height-100), width, height,
				Color.CYAN));
		
		antImage = createImage(width, height, RGB);
		antImage.loadPixels();
		for (int i = 0; i < antImage.pixels.length; i ++) {
			antImage.pixels[i] = color(0);
		}
		antImage.updatePixels();
	}
	
	@Override
	public void draw () {
		for (int n = 0; n < epochs; n ++) {
			for (Ant ant : ants) {
				int state = grid[ant.x][ant.y];
				Color col;
				if ( state == 0 ) {
					ant.turnRight();
					grid[ant.x][ant.y] = 1;
					col = new Color(0, 0, 0);
				} else {
					ant.turnLeft();
					grid[ant.x][ant.y] = 0;
					col = ant.color;
				}
				antImage.loadPixels();
				int pix = ant.x + ant.y * antImage.width;
				antImage.pixels[pix] = col.getRGB();
				antImage.updatePixels();
				ant.moveForward();
			}
		}
		
		image(antImage, 0, 0);
		cr += 0.1;
		epochs += 40;
	}
	
	public static void main (String[] args) {
		String[] stringArgs = new String[] {"Main"};
		PApplet.main(stringArgs);
	}
	
}

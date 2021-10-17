import java.awt.*;

public class Ant {
	public int x;
	public int y;
	public Color color;
	int width;
	int height;
	int dir;
	
	final int ANT_UP = 0;
	final int ANT_RIGHT = 1;
	final int ANT_DOWN = 2;
	final int ANT_LEFT = 3;
	
	public Ant(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	void turnRight () {
		dir ++;
		if (dir >ANT_LEFT)
			dir = ANT_UP;
	}
	
	void turnLeft () {
		dir --;
		if (dir < ANT_UP)
			dir = ANT_LEFT;
	}
	
	void moveForward() {
		switch (dir) {
			case ANT_UP -> y --;
			case ANT_RIGHT -> x ++;
			case ANT_DOWN -> y ++;
			case ANT_LEFT -> x --;
		}
		
		if (x > width-1) {
			x = 0;
		} else if (x < 0) {
			x = width-1;
		}
		
		if (y > height-1) {
			y = 0;
		} else  if (y < 0) {
			y = height-1;
		}
		
	}
}

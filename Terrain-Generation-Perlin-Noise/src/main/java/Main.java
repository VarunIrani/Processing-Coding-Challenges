import processing.core.PApplet;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main extends PApplet {
	int scale = 20;
	int w = 2000;
	int h = 1300;
	int cols, rows;
	float[][] terrain;
	Color[][] colors;
	float flayingY = 0;
	float flayingX = 0;
	int KEYCODE = 0;
	
	public void settings() {
		size(1200, 720, P3D);
		
		cols = w/scale;
		rows = h/scale;
		
		terrain = new float[cols][rows];
		colors = new Color[cols][rows];
	}
	
	public void draw() {
		float yOf = flayingY;
		for (int y=0; y < rows; y ++) {
			float  xOf = flayingX;
			for (int x=0; x < cols; x ++) {
				terrain[x][y] = map(noise(xOf, yOf), 0, 1, -200, 200);
				xOf += 0.09;
			}
			yOf += 0.09;
		}
		
		for (int y=0; y < rows; y ++) {
			for (int x=0; x < cols; x ++) {
				int g = (int) map(terrain[x][y], -200, 200, 0, 255);
				colors[x][y] = new Color(0, g, 0);
			}
		}
		
		background(0,181,226);
		strokeWeight(0);
		translate((float)width/2, (float)height/2-90);
		rotateX(PI/3);
		
		translate((float)-w/2, (float)-h/2);
		
		for (int y=0; y < rows-1; y ++) {
			beginShape(TRIANGLE_STRIP);
			for (int x=0; x < cols; x ++) {
				fill(colors[x][y].getRGB());
				vertex(x*scale, y*scale, terrain[x][y]);
				vertex(x*scale, (y+1)*scale,  terrain[x][y+1]);
			}
			endShape();
		}
		
		switch (KEYCODE) {
			case 1 -> flayingY -= 0.01;
			case 2 -> flayingY += 0.01;
			case 3 -> flayingX -= 0.01;
			case 4 -> flayingX += 0.01;
		}
	}
	
	public static void main (String[] args) {
		String[] appletArgs = new String[] {"Main"};
		PApplet.main(appletArgs);
	}
	
	public void keyReleased() {
		KEYCODE = 0;
	}
	
	@Override
	public void keyPressed (processing.event.KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_UP) {
			KEYCODE = 1;
		}
		if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			KEYCODE = 2;
		}
		if (event.getKeyCode() == KeyEvent.VK_LEFT) {
			KEYCODE = 3;
		}
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
			KEYCODE = 4;
		}
	}
}

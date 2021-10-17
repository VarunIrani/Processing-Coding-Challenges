import processing.core.PApplet;

import java.awt.*;

public class Cell {
	public int x, y, size;
	public boolean live;
	
	public Cell(int i, int j, int res, boolean live) {
		this.x = i * res;
		this.y = j * res;
		this.size = res;
		this.live = live;
	}
	
	public void draw(PApplet pApplet) {
		if (this.live) {
			pApplet.fill(Color.BLACK.getRGB());
		} else {
			pApplet.fill(Color.WHITE.getRGB());
		}
		
		pApplet.rect(x, y, size, size);
	}
}

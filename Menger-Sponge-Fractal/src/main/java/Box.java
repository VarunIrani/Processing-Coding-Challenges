import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

import static processing.core.PApplet.abs;

public class Box {
	PVector pos;
	float r;
	
	public Box(float x, float y, float z, float r_) {
		pos = new PVector(x, y, z);
		r = r_;
	}
	
	public void show(PApplet pApplet) {
		pApplet.pushMatrix();
		pApplet.translate(pos.x, pos.y, pos.z);
		pApplet.fill(new Color(240, 200, 130).getRGB());
		pApplet.strokeWeight(0);
		pApplet.box(r);
		pApplet.popMatrix();
	}
	
	public ArrayList<Box> generate() {
		ArrayList<Box> boxes = new ArrayList<>();
		for (int x=-1; x < 2; x ++) {
			for (int y=-1; y < 2; y ++) {
				for (int z=-1; z < 2; z ++) {
					int sum = abs(x) + abs(y) + abs(z);
					float newR = r/3;
					if (sum > 1) {
						Box b = new Box(pos.x + x * newR,
								pos.y + y * newR,
								pos.z + z * newR, newR);
						boxes.add(b);
					}
				}
			}
		}
		return boxes;
	}
}

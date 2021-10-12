import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class Main extends PApplet {
	public class Box {
		PVector pos;
		float r;
		
		public Box(float x, float y, float z, float r_) {
			pos = new PVector(x, y, z);
			r = r_;
		}
		
		public void show() {
			pushMatrix();
			translate(pos.x, pos.y, pos.z);
			fill(new Color(240, 200, 130).getRGB());
			strokeWeight(0);
			box(r);
			popMatrix();
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
	
	float a = 0;
	ArrayList<Box> sponge;
	@Override
	public void settings() {
		size(720, 480, P3D);
		sponge = new ArrayList<>();
		Box b = new Box(0, 0, 0, 200);
		sponge.add(b);
	}
	
	@Override
	public void draw () {
		background(66);
		lights();
		translate((float) width/2, (float) height/2);
		rotateX(a);
		rotateY(a*0.4f);
		rotateZ(a*0.2f);
		for (Box b : sponge)
			b.show();
		
		a += 0.01;
	}
	
	@Override
	public void mousePressed () {
		ArrayList<Box> next = new ArrayList<>();
		for (Box b : sponge) {
			ArrayList<Box> newBoxes = b.generate();
			next.addAll(newBoxes);
		}
		sponge = next;
	}
	
	public static void main (String[] args) {
		String[] appletArgs = new String[] {"Main"};
		PApplet.main(appletArgs);
	}
}

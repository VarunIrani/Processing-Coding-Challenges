import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {
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
			b.show(this);
		
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

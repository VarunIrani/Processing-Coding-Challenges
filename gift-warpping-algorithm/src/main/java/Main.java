import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Random;

public class Main extends PApplet {
	
	private ArrayList<PVector> vector;
	private PVector leftMost;
	private PVector currentPoint;
	private PVector nextVertex;
	private int index;
	private ArrayList<PVector> hull;
	
	public Main() {}
	
	public void settings() {
		size(800, 500);
		vector = new ArrayList<>();
		hull = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			vector.add(new PVector(random.nextInt(width), random.nextInt(height)));
		}
		
		vector.sort((a, b) -> (int) (a.x - b.x));
		this.leftMost = vector.get(0);
		this.currentPoint = leftMost;
		hull.add(currentPoint);
		this.nextVertex = vector.get(1);
		index = 2;
	}
	
	public void draw() {
		background(0);
		stroke(255);
		strokeWeight(8);
		for (PVector p : vector) {
			point(p.x, p.y);
		}
		
		stroke(0, 0, 255);
		strokeWeight(2);
		fill(0, 0, 255, 50);
		beginShape();
		for (PVector p : hull) {
			vertex(p.x, p.y);
		}
		endShape(CLOSE);
		
		stroke(0, 255, 0);
		point(leftMost.x, leftMost.y);
		
		stroke(200, 255, 0);
		strokeWeight(10);
		point(currentPoint.x, currentPoint.y);
		
		stroke(0, 255, 0);
		strokeWeight(2);
		line(currentPoint.x, currentPoint.y, nextVertex.x, nextVertex.y);
		
		PVector checking = vector.get(index);
		stroke(255);
		strokeWeight(2);
		line(currentPoint.x, currentPoint.y, checking.x, checking.y);
		
		PVector a = PVector.sub(nextVertex, currentPoint);
		PVector b = PVector.sub(checking, currentPoint);
		
		PVector cross = a.cross(b);
		
		if (cross.z < 0) {
			nextVertex = checking;
		}
		
		index++;
		if (index == vector.toArray().length) {
			if (nextVertex == leftMost) {
				System.out.println("Done");
				clear();
				background(0);
				stroke(255);
				strokeWeight(8);
				for (PVector p : vector) {
					point(p.x, p.y);
				}
				
				stroke(0, 0, 255);
				strokeWeight(2);
				fill(0, 0, 255, 50);
				beginShape();
				for (PVector p : hull) {
					vertex(p.x, p.y);
				}
				endShape(CLOSE);
				
				noLoop();
			} else {
				hull.add(nextVertex);
				currentPoint = nextVertex;
				index = 0;
				nextVertex = leftMost;
			}
		}
	}
	
	public static void main(String[] passedArgs) {
		String[] appletArgs = new String[] {"Main"};
		PApplet.main(appletArgs);
	}
}

package ru.stqa.ptf.sandbox2;

public class Point {

	public double x1;
	public double y1;
	public double x2;
	public double y2;

	public Point p1(double x1, double y1) {
		this.x1 = x1;
		this.y1 = y1;
	}
	public Point p2(double x2, double y2) {
		this.x2 = x2;
		this.y2 = y2;
}

	public static double distance(Point p1, Point p2) {
		return Math.sqrt((p2.x2-p1.x1)*(p2.x2-p1.x1)+(p2.y2-p1.y1)*(p2.y2-p1.y1));

	}
}

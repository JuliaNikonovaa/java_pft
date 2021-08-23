package ru.stqa.ptf.sandbox2;

public class Start {

	public static void main(String[] args) {

		Point p1 = new Point(4, 5);
		Point p2 = new Point(6, 8);
		System.out.println("Distance between p1 and p2 =" + p1.distance(p2));
	}

}

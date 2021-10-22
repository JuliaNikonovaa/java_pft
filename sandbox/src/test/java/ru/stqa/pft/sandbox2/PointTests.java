package ru.stqa.pft.ptf.sandbox2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

	@Test


	public void testdistance() {
		Point p1 = new Point (4, 5);
		Point p2 = new Point (6, 8);
		Assert.assertEquals(p1.distance(p2), 3.605551275463989);
	}
@Test

	public void testdistance2() {
		Point p1 = new Point (4, 5);
		Point p2 = new Point (6, 8);
		Assert.assertEquals(p1.distance(p2), 3.0);
	}
}

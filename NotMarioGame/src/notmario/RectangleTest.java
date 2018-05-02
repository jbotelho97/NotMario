package notmario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RectangleTest implements ApplicationConstants {

	@Test
	void testInvalidIntersection() {
		Rectangle r = new Rectangle(0, 0, 1, 1, 0, 0, 0);
		Character c = new Character(100,100,1,1,1,1, null);
		assertFalse(r.isInside(c, 1));
	}

	@Test
	void testValidIntersection() {
		Rectangle r = new Rectangle(0, 0, 100, 100, 0, 0, 0);
		Character c = new Character(50,50,5,1,1,1, null);
		assertTrue(r.isInside(c, 1));
	}

	@Test
	void testNotEdge() {
		Rectangle r = new Rectangle(0, 0, 100, 100, 0, 0, 0);
		Character c = new Character(0, 100, 5, 1, 1, 1, null);
		assertFalse(r.playerEdge(c, 1));
	}

/*  TODO: Improve test case!
	void testEdge() {
		Rectangle r = new Rectangle(2.5006f, -65, 70, 25, 0, 0, 0);
		Character c = new Character(0, -0.375f, 1, 4, 1, 1, null);
		assertTrue(r.playerEdge(c, 1));
	}
*/
}

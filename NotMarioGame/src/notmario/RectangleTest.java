package notmario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RectangleTest {

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

}

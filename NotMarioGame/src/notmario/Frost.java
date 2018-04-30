package notmario;
import processing.core.PApplet;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Frost extends Powerup {
	private Image frostIcon;
	private float projectileSpeed;
	
	public Frost(float x, float y, int chance) {
		if (chance ==  0) {
			super.init(x, y, 2, 2 ,2, frostIcon);
		}else {
			return;
		}
	}
	
}
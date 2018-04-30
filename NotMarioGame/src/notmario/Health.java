package notmario;
import processing.core.PApplet;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Health extends Powerup {
	private Image healthIcon;
	private float projectileSpeed;
	
	public Health(float x, float y, int chance) {
		if (chance ==  0) {
			super.init(x, y, 2, 2 ,2, healthIcon);
		}else {
			return;
		}
	}
	
}
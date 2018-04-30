package notmario;
import processing.core.PApplet;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Fire extends Powerup {
	private Image fireIcon;
	private float projectileSpeed;
	
	public Fire(float x, float y, int chance) {
		if (chance ==  0) {
			super.init(x, y, 2, 2 ,2, fireIcon);
			projectileSpeed = 5.0f;
		}else {
			return;
		}
	}
	public void Shoot() {
		
	}
}
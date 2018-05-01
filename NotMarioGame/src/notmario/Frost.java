package notmario;
import processing.core.PApplet;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Frost implements Powerup {
	private Image frostIcon;
	private float projectileSpeed;
	private float xcor, ycor, size, width, height;
	private int damage;
	private int powerUpIndex;
	public Frost(float x, float y, float d, float h, float w, Image img, int chance) {
		if (chance ==  1) {
			xcor = x;
			ycor = y;
			damage = 2;
			size = 1;
			height = 2;
			width = 2;
			frostIcon = img;
			projectileSpeed = 5.0f;
		}else {
			return;
		}
	}
	}
	
}
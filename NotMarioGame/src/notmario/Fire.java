package notmario;
import processing.core.PApplet;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Fire implements Powerup, ApplicationConstants {
	private Image fireIcon;
	
	private float xcor, ycor, size, width, height;
    private float projectileSpeed = 0.1f;
	private int powerUpIndex;
	private static PApplet app_;
	
	
	
	private int damage;
	public Fire(float x, float y, Image img, int chance) {
		if (chance ==  0) {
			this.xcor = x;
			this.ycor = y;
			damage = 2;
			size = 1;
			height = 2;
			width = 2;
			fireIcon = img;
			projectileSpeed = 5.0f;
		}else {
			return;
		}
	}
	 public float getXcoor(){
     return xcor;
	 }
	 public float getYcoor(){
     return ycor;
	 }
	 public float getWidth(){
     return width;
	 }
	 public float getHeight(){
     return height;
	 }
	 public Image getSprite(){
     return fireIcon;
	 }
	 public void draw(){
     app_.pushMatrix();

     app_.stroke(0);
     app_.fill(0, 0, 255);
     
     app_.rect(xcor, ycor, width, height);

     app_.popMatrix();
	 }
	 public void spawnPowerup() {}
	 public void shoot() {}
	
}
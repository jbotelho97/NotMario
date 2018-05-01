package notmario;
import processing.core.PApplet;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.util.ArrayList;

public class Fire implements Powerup, ApplicationConstants {
	private Image fireIcon;
	private ArrayList <Fire> shooting;//where our bullets will be stored
	private float xcor, ycor, size, width, height;
    private float projectileSpeed = 0.1f;
	private int powerUpIndex;
	private static PApplet app_;
	private boolean active = true;
	
	
	private int damage;
	public Fire(float x, float y, int chance) {
		if (chance ==  0) {
			this.xcor = x;
			this.ycor = y;
			active = true;
			damage = 2;
			size = 1;
			height = 2;
			width = 2;
			
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
	 public void shoot(float dir){
		if(active) {
		
			 passiveMove(dir);
		}
	}
	 
	 public void passiveMove(float direction) {
		 if(active) {
			 xcor += direction* projectileSpeed;
		 }
	 }
	 public void spawnPowerup() {}
	
	
}
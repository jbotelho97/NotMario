package notmario;
import processing.core.PApplet;
import processing.event.KeyEvent;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Event;

public class Frost extends PApplet implements Powerup, ApplicationConstants {
	private Image frostIcon;
	private ArrayList <Frost> shooting;
	private float xcor, ycor, size, width, height;
    private float projectileSpeed = 0.001f;
	private int powerUpIndex;
	private static PApplet app_;
	private boolean active = false;
	private static int appSetCounter_ = 0;
	private float direction;
	private int damage;
	
	public Frost(float x, float y, int chance, float dir) {
		if (chance ==  0) {
			this.xcor = x;
			this.ycor = y;
			active = true;
			damage = 2;
			direction = dir;
			shooting = new ArrayList();
		}else {
			return;
		}
	}
	protected static int setup(PApplet theApp)
	{

		
		if (appSetCounter_ == 0) 
		{
			app_ = theApp;
			appSetCounter_ = 1;
		}
		else
			appSetCounter_ = 2;

		return appSetCounter_;

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
     return frostIcon;
	 }
	

	 public void draw()
	  {
		
		app_.stroke(0);
		app_.fill(0, 0, 255);

	    app_.ellipse(xcor, ycor, 1, 1);
	    
	     
	    
	  }
	 public void move() {
		 if(active) {
			 xcor += 0.1 * -direction;
		 }
	 }
	
	
	 
	 public void spawnPowerup() {}
	
	
}
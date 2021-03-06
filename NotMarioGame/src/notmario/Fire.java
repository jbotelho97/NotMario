package notmario;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Event;

public class Fire extends PApplet implements Powerup, ApplicationConstants {
	private PImage fireIcon;
	private ArrayList <Fire> shooting;//where our bullets will be stored
	private float xcor, ycor, size, width, height;
	private float projectileSpeed = 0.001f;
	private int powerUpIndex;
	private static PApplet app_;
	private boolean active = false;
	private static int appSetCounter_ = 0;
	private float direction;
	private int damage;

	public Fire(float x, float y, int chance, float dir, PImage img) {
		if (chance ==  0) {
			this.xcor = x;
			this.ycor = y;
			active = true;
			damage = 2;
			direction = dir;
			shooting = new ArrayList();
			fireIcon = img;
			fireIcon.resize(5, 5);
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
	public PImage getSprite(){
		return fireIcon;
	}


	public void draw()
	{

		app_.stroke(0);
		app_.fill(255, 0,0);

		//app_.ellipse(xcor, ycor-2, 1, 1);
		if(direction == 1) {
			app_.pushMatrix();
			app_.scale(-1,1);
			app_.image(fireIcon, -xcor, ycor-2);
			app_.popMatrix();
		}
		else
		{
			app_.image(fireIcon, xcor, ycor - 2);
		}



	}
	public void move() {
		if(active) {
			if (direction ==0) {
				xcor+= 0.15 *1;
			}
			xcor += 0.15 * -direction;
		}
	}


	public void passiveMove(int dir) {
		xcor += 0.1 * dir;
	}


	public void spawnPowerup() {}


}
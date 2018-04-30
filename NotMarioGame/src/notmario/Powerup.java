package notmario;

import processing.core.PApplet;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public abstract class Powerup implements ApplicationConstants 
{
	private String[] powerUps = ["fire", "frost", "health"];  //stores the various powerups
	
	private static int appSetCounter_ = 0;				
    
	private static PApplet app_;
    
	private float xcor, ycor, size, angle, width, height;
	
	private int powerUpIndex;
	
	private Image sprite;
	
	private int damage;
	
	public void init(float x, float y, float d, float h, float w, ImageIcon img) {
		xcor = x;
		ycor = y;
		size = 1;
		damage = d;
		angle = 0;
		height = h;
		width = w;
		sprite = img.getImage();
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
	    public float getWidth() {
	        return width;
	    }
	    public float getHeight(){
	        return height;
	    }
	    public Image getSprite(){
	        return sprite;
	    }
	    public void draw(){
	        app_.pushMatrix();

	        app_.stroke(0);
	        app_.fill(0, 0, 255);

	        app_.rect(xcor, ycor, width, height);

	        app_.popMatrix();
	    }
	    public void spawnPowerup() {
	    	
	    }
	

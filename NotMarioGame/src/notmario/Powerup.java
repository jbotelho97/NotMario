package notmario;

import processing.core.PApplet;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public interface Powerup
{
	
	
	

	 public float getXcoor();/*{
	        return xcor;
	    }*/
	    public float getYcoor();/*{
	        return ycor;
	    }*/
	    public float getWidth(); /*{
	        return width;
	    }*/
	    public float getHeight();/*{
	        return height;
	    }*/
	    public Image getSprite();/*{
	        return sprite;
	    }*/
	    /*{
	        app_.pushMatrix();

	        app_.stroke(0);
	        app_.fill(0, 0, 255);

	        app_.rect(xcor, ycor, width, height);

	        app_.popMatrix();
	    }*/
	    public void spawnPowerup();
	    public void shoot(float dir);/* {
	   	
	    }*/
}
	

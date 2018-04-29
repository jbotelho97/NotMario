package notmario;

import java.awt.Image;

import processing.core.PApplet;

public class Rectangle {
//  Here we store a reference to the app. in a static (aka "class") variable.
	private static PApplet app_; 
	private static int appSetCounter_ = 0;
	private Image sprite_;

	private float x_, y_, playerSpeedX_, w_, h_, r, b, g;
	
	/**
	 * Builds the rectangle by setting its status variables
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param w width of rectangle
	 * @param h height of rectangle
	 */
	public Rectangle(float x, float y, float w, float h, float red, float green, float blue) {
		x_ = x;
		y_ = y;
		w_ = w;
		h_ = h;
		r = red;
		b = blue;
		g = green;
		playerSpeedX_ = 0.1f;
	}
	
	/**
	 * Builds the rectangle by setting its status variables
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param w width of rectangle
	 * @param h height of rectangle
	 * @param sprite image that may be attached to rectangle
	 */
	public Rectangle(float x, float y, float w, float h, float red, float green, float blue, Image sprite) {
		x_ = x;
		y_ = y;
		w_ = w;
		h_ = h;
		r = red;
		b = blue;
		g = green;
		playerSpeedX_ = 0.1f;
		sprite_ = sprite;
	}
	
	/**
	 * draws the rectangle to the window
	 */
	public void draw() 
	{
		app_.strokeWeight(.1f);
		app_.fill(r, g, b);
		app_.rect(x_, y_, w_, h_);
		app_.ellipse(x_, y_, 1, 1);
	}
	
	public float getX() {
		return x_;
	}
	
	public float getY() {
		return y_;
	}
	
	public void setX(float x) {
		x_ = x;
	}
	
	public void setY(float y) {
		y_ = y;
	}
	
	public void move(int direction) {
		x_ += (playerSpeedX_*direction);
	}

	/**
	 * Passes a reference of the window to the rectangle to use when drawing
	 * @param theApp
	 * @return
	 */
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

	/** Tells whether the character is inside the platform
	 * 
	 * @param Character 
	 * @return true if (x, y) is inside this object
	 */
	public boolean isInside(Character player)
	{		
		float leftX = player.x_-player.width/2;
		float rightX = player.x_+player.width/2;
		float bottomY = player.y_-player.height/2;
		
		// Check bottom left corner of player character
		if((leftX >= x_) && (leftX <= x_ + w_) && (bottomY >= y_) && (bottomY <= y_ + h_)) {
			//System.out.println(leftX + " " + rightX + " " + bottomY + " " + x_ + " " + y_ + " " + h_ + " " + w_);
			return true;
		}
		// Check bottom right corner of player character
		if ((rightX >= x_) && (rightX <= x_ + w_) && (bottomY >= y_) && (bottomY <= y_ + h_)) {
			//System.out.println(leftX + " " + rightX + " " + bottomY + " " + x_ + " " + y_ + " " + h_ + " " + w_);
			return true;
		}

		return false;
	}
}

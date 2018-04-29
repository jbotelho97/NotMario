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
		boolean result;
		if((player.x_ >= x_) && (player.x_ <= x_ + w_) && (player.y_ >= y_) && (player.y_ - player.height/2<= y_ + h_)) {
			result = true;
		}
		else if((player.x_+player.width >= x_) && (player.x_ + player.width <= x_ + w_) && (player.y_ >= y_) && (player.y_ - player.height/2 <= y_ + h_)) {
			result = true;
		}
		else
			result = false;
		return result;
	}
}

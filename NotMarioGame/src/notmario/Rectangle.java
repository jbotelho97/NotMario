package notmario;

import processing.core.PApplet;

public class Rectangle {
//  Here we store a reference to the app. in a static (aka "class") variable.
	private static PApplet app_; 
	private static int appSetCounter_ = 0;

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

	/** Tells whether the point whose coordinates are passed is inside this object
	 * 
	 * @param x  x coordinate of the point (in world coordinates)
	 * @param y x coordinate of the point (in world coordinates)
	 * @return true if (x, y) is inside this object
	 */
	public boolean isInside(float x, float y)
	{		
		return ((x >= x_) && (x <= x_ + w_) && (y >= y_) && (y <= y_ + h_));
	}
}

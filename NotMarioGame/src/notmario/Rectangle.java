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
	
	public float getWidth() {
		return w_;
	}
	
	public float getHeight() {
		return h_;
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

 	//Tells whether player char is in rectangle
	public boolean isInside(Character player, int dir)
	{		
		float leftX = player.x_-player.width/2;
		float rightX = player.x_+player.width/2;
		float bottomY = player.y_-player.height/2;
		boolean inside = false;
		// Check bottom left corner of player character
		if((leftX >= x_) && (leftX <= x_ + w_) && (bottomY >= y_) && (bottomY <= y_ + h_)) {
			//System.out.println(leftX + " " + rightX + " " + bottomY + " " + x_ + " " + y_ + " " + h_ + " " + w_);
			inside = true;
		}
		// Check bottom right corner of player character
		if ((rightX >= x_) && (rightX <= x_ + w_) && (bottomY >= y_) && (bottomY <= y_ + h_)) {
			//System.out.println(leftX + " " + rightX + " " + bottomY + " " + x_ + " " + y_ + " " + h_ + " " + w_);
			inside = true;
		}
		
		if (inside) {
			// Fell inside floor somehow.
			if (bottomY < y_ + h_) {
				player.y_ = y_ + h_ + player.height/2;
				player.vy_ = 0;
			}
		}
		if (playerEdge(player, dir)) {
			inside = false;
		}
		return inside;
	}
	

	public boolean isInside(float x1, float y1) {
		
		System.out.println(x_ + " " + y_);
		return (x1 >= x_ && x1 <= (x_ + w_) && y1 > y_ && y1 < (y_ + h_));
		
	}

	//Method for enemy collision. This is just temporary until I find a better place to put it. -Jack
	public boolean enemyInside(Enemy e){
		if((e.getXcoor() >= x_) && (e.getXcoor() <= x_ + w_) && (e.getYcoor() >= y_  + h_ - 0.1f) && (e.getYcoor() <= y_ + h_ + 0.1f)) {
			e.land();
			return true;
		}
		else if((e.getXcoor()+ e.getWidth() >= x_) && (e.getXcoor() + e.getWidth() <= x_ + w_) && (e.getYcoor() >= y_ + h_ - 0.1f) && (e.getYcoor() <= y_ + h_ + 0.1f)) {
			e.land();
			return true;
		}
		return false;
	}

	//Method for determining if enemy is touching edge
	public boolean enemyEdge(Enemy e){
		float farX = e.getXcoor() + e.getWidth();
		if((e.getXcoor() >= x_ + w_ - 0.05f) &&(e.getXcoor() <= x_ + w_ +0.05f) &&(e.getYcoor() < y_ + h_)){
			return true;
		}
		else if((farX >= x_ -0.05f) && (farX <= x_ + 0.05f) && (e.getYcoor() < y_ + h_)){
			return true;
		}
		else{
			return false;
		}
	}

	//Method for determining if player is touching edge
	public boolean playerEdge(Character player, int dir) {
		float leftX = player.x_-player.width/2;
		float rightX = player.x_+player.width/2;
		float bottomY = player.y_-player.height/2;
		float topY = player.y_-player.height/2;
		if (topY > y_ + h_ - 0.1f) {
			return false;
		}
		float leftWallX = x_;
		float rightWallX = x_ + w_;
		
		// Going left, hitting right wall
		//leftX - 0.05f < rightWallX < left
		if ((dir > 0) && (rightWallX < leftX) && (leftX - 0.1f < rightWallX)) {
			return true;
		}
		// Going right, hitting left wall
		if ((dir < 0) && (rightX + 0.1f > leftWallX) && (rightX < leftWallX)) {
			return true;
		}
		
		return false;
	}
}

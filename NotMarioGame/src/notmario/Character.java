package notmario;

import java.awt.Image;

import processing.core.PApplet;

public class Character implements ApplicationConstants 
{
	private static int appSetCounter_ = 0;
	private static PApplet app_; 
	
	
		
		private boolean ref_ = false;
		private boolean airborne = false;
		public float x_, y_, size_, angle_, vy_, width, height;
		//color
		private float r_, b_, g_;
		private Image sprite;
		
		/**
		 * Main constructor of a character. It is given a location, size and color while being
		 * passed the starting animation
		 * @param x
		 * @param y
		 * @param size
		 * @param keyframe
		 * @param r
		 * @param g
		 * @param b
		 */
		public Character(float x, float y, float size, float r, float g, float b) {
			//location and size
			x_ = x;
			y_ = y;
			size_ = size;
			width = 5;
			height = 10;
			
			r_ = r;
			b_ = b;
			g_ = g;
			
			//movement values in x and y direction
			vy_ = 0.0f;
			
			
		}
		
		/**
		 * draws all components of character onto the window. Passed a reference to the PApplet
		 * @param theApp
		 */
		public void draw() {
			
			// we use this object's instance variable to access the application's instance methods and variables
			app_.pushMatrix();
			
			//move to correct location, size, and orientation on the window
			app_.translate(x_-width/2, y_-height/2);
			app_.rotate(angle_);
			app_.scale(size_);
			
//			//to see reference frames and attack boxes
//			if(ref_) {
//				app_.noFill();
//				app_.stroke(255, 0, 0);
//				app_.rect(REFERENCE_BOX_OFFSET_X, REFERENCE_BOX_OFFSET_Y, REFERENCE_BOX_W, REFERENCE_BOX_H);
//				
//			}
			
			app_.stroke(0);
			app_.fill(r_, g_, b_);
			
			app_.rect(0, 0, width, height);
			
			app_.fill(0);
			app_.ellipse(0, 0, 1, 1);
			app_.ellipse(0 + width, 0, 1, 1);
			
			app_.popMatrix();
		}
		
		
		public void animate() {
			if(airborne)
				y_ += (vy_ += gravity);
		}
		
		/**
		 * Used to toggle reference box and attack box of player
		 */
		public void toggleRef() {
			ref_ = !ref_;
		}
		
		/**
		 * Sets a y velocity and begins the jump animation
		 */
		public void jump() {
			if(airborne == false) {
				vy_ = 0.15f;
				airborne = true;
			}
		}
		
		public void fall() {
			airborne = true;
		}
		
		/**
		 * Sets the y velocity to zero
		 */
		public void land() {
			vy_ = 0f;
			airborne = false;
		}
	
	/**
	 * Passes a reference of the window to the player to use when drawing
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
}

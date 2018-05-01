package notmario;

import java.awt.Image;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Character implements ApplicationConstants 
{
	private static int appSetCounter_ = 0;
	private static PApplet app_; 
	private Powerup pow;
	private ArrayList <Fire> fireBalls;
	private ArrayList <Frost> frostBalls;
	
	private int activePowerUp =4;
		
		private boolean ref_ = false;
		private boolean airborne = false;
		public float x_, y_, size_, angle_, vy_, width, height;
		//color
		private float r_, b_, g_;
		public int health;
		
		private PImage[] sprites;
		private int spriteIndex;
		
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
		public Character(float x, float y, float size, float r, float g, float b, PImage[] images) {
			//location and size
			x_ = x;
			y_ = y;
			size_ = size;
			width = 40/WORLD_TO_PIXEL;
			height = 74/WORLD_TO_PIXEL;
			health = 100;
			r_ = r;
			b_ = b;
			g_ = g;
			fireBalls = new ArrayList();
			frostBalls = new ArrayList();
			
			sprites = images;
			spriteIndex = 0;
			
			//movement values in y direction
			vy_ = 0.0f;
			
			
		}
		
		/**
		 * draws all components of character onto the window. Passed a reference to the PApplet
		 */
		public void draw() {
			for(Fire fire: fireBalls) {
				if(fire != null)
				fire.draw();
			}
			for(Frost frost: frostBalls) {
				if(frost != null)
				frost.draw();
			}
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
			
			//Collision rectangle for Player
			//app_.rect(0, 0, width, height);
			
			app_.fill(0);
			
			//Ground collision for Player
			//app_.ellipse(0, 0, 1, 1);
			//app_.ellipse(0 + width, 0, 1, 1);
			
			app_.pushMatrix();
			app_.scale(PIXEL_TO_WORLD, -PIXEL_TO_WORLD);
			app_.image(sprites[spriteIndex], 0, -height/PIXEL_TO_WORLD);
			app_.popMatrix();
			
			app_.popMatrix();
		}
		
		
		public void animate() {
			if(airborne)
				y_ += (vy_ += gravity);
			for(Fire fire: fireBalls) {
				fire.move();
			}
			for(Frost frost: frostBalls) {
				frost.move();
			}
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
				spriteIndex = 3;
				airborne = true;
			}
		}

		
		public void fall() {
			spriteIndex = 3;
			airborne = true;
		}
		
		/**
		 * Sets the y velocity to zero
		 */
		public void land() {
			vy_ = 0f;
			airborne = false;
			if(spriteIndex == 3) {
				spriteIndex = 0;
			}
		}

		//Takes a hit
		public void takeHit(Enemy e){
			health -= e.getDamage();
			activePowerUp = 4;
			if (health <= 0){
				System.out.println("You died!");
				System.exit(0);
			}
		}
		
		public boolean isAirborne() {
			return airborne;
		}
		
		public void setImageIndex(int i) {
			spriteIndex = i;
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
	public void shoot(float dir) {
		System.out.println("activated");
		 if(activePowerUp == 0) {
		 if(airborne) {
			 System.out.println("airborne");
		   Fire temp = new Fire(x_+ (width/2 * -dir),y_- (vy_+gravity), 0, dir);
		   fireBalls.add(temp);
		 }else {
			 Fire temp = new Fire(x_+ (width/2*-dir),y_, 0, dir); 
			 fireBalls.add(temp);
		 }
		 }else if(activePowerUp == 1) {
			 if(airborne) {
				 System.out.println("airborne");
			   Frost temp = new Frost(x_+ (width/2 * -dir),y_- (vy_+gravity), 0, dir);
			   frostBalls.add(temp);
			 }else {
				 Frost temp = new Frost(x_+ (width/2*-dir),y_, 0, dir); 
				 frostBalls.add(temp);
			 }
		 }
		else {
			 return;
		 }
		 
	}
	public void setActivePowerUp(int number) {
		activePowerUp = number;
		if(activePowerUp == 2) {
			System.out.println("activated");
			setHealth(100);
		
		}
	}
	public void setHealth(int h) {
		health = h;
		activePowerUp = 4;
		System.out.println(health);
	}
	public int getHealth() {
		return health;
	}
	}


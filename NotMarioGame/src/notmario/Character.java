package notmario;


import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Character implements ApplicationConstants 
{
	private static int appSetCounter_ = 0;
	private static PApplet app_;//reference to the app graphical window
	private ArrayList <Fire> fireBalls; //to hold any created fireballs
	private ArrayList <Frost> frostBalls; //to hold any created frost attacks
	private int activePowerUp =4; //default setting for no powerup
		
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
			//sets location, size, color and images for object
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
			
			
			app_.stroke(0);
			app_.fill(r_, g_, b_);
			
			
			app_.fill(0);
			
			
			app_.pushMatrix();
			app_.scale(PIXEL_TO_WORLD, -PIXEL_TO_WORLD);
			app_.image(sprites[spriteIndex], 0, -height/PIXEL_TO_WORLD);
			app_.popMatrix();
			
			app_.popMatrix();
		}
		
		
		public void animate() {
			if (airborne) {
				y_ += (vy_ += gravity);
			}
			//System.out.println(y_);
			if (y_ <= -35) {
				setHealth(0);
			}
			for (Fire fire: fireBalls) {
				if(fire != null)
				fire.move();
			}
			for (Frost frost: frostBalls) {
				if(frost != null) 
				frost.move();
				
			}
		}
		
		public void passiveMoveProjectiles(int direction, boolean isMove) {
			if(isMove) {
				for(Fire fire: fireBalls) {
					if(fire != null)
					fire.passiveMove(direction);
				}
				for(Frost frost: frostBalls) {
					if(frost != null)
					frost.passiveMove(direction);
				}
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
		
		public void die() {
			spriteIndex = 4;
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
		}
		
		public boolean isAirborne() {
			return airborne;
		}
		
		public int getImageIndex() {
			return spriteIndex;
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
		//System.out.println("activated");
		 if(activePowerUp == 0) {
	
			 if(airborne) {
		   Fire temp = new Fire(x_+ (width/2 * -dir),y_- (vy_+gravity), 0, dir);
		   fireBalls.add(temp);
		 }else {
			 Fire temp = new Fire(x_+ (width/2*-dir),y_, 0, dir); 
			 fireBalls.add(temp);
		 }
		 }else if(activePowerUp == 1) {
			 if(airborne) {
				 //System.out.println("airborne");
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
	public int getActivePowerUp() {
		return activePowerUp;
	}
	public void setHealth(int h) {
		health = h;
		activePowerUp = 4;
		//System.out.println(health);
	}
	public int getHealth() {
		return health;
	}
	public ArrayList<Frost> getPowerUpFrost() {
		if( activePowerUp == 1) {
			return frostBalls;
		}else {
		return null;
		}
	}
	public ArrayList<Fire> getPowerUpFire(){
	if( activePowerUp == 0 ) {
		return fireBalls;
	}else {
		return null;
	}
	}
	
	}


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

	private boolean airborne = false; //is the character airborne
	public float x_, y_, size_, angle_, vy_, width, height; //drawing variables
	//color
	private float r_, b_, g_;
	public int health; //amount of damage player can take before dying

	private PImage[] sprites; //holds all images
	private int spriteIndex; //specific image from array

	/**
	 * Main constructor of a character. It is given a location, size and color while being
	 * passed the character images
	 * @param x
	 * @param y
	 * @param size
	 * @param keyframe
	 * @param r
	 * @param g
	 * @param b
	 * @param images
	 */
	public Character(float x, float y, float size, float r, float g, float b, PImage[] images) {
		//sets location, size, color and images for object as well as the powerup arrays
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
	 * draws all components of character onto the window. Also draws the fire or frost attacks
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
		app_.pushMatrix();

		//move to correct location, size, and orientation on the window
		app_.translate(x_-width/2, y_-height/2);
		app_.rotate(angle_);
		app_.scale(size_);


		app_.stroke(0);
		app_.fill(r_, g_, b_);


		app_.fill(0);

		//draw the correct proportion for the image of the character
		app_.pushMatrix();
		app_.scale(PIXEL_TO_WORLD, -PIXEL_TO_WORLD); //Character image is in pixels not world units
		app_.image(sprites[spriteIndex], 0, -height/PIXEL_TO_WORLD);//draw image


		app_.popMatrix();

		app_.popMatrix();

		app_.pushMatrix();
		app_.scale(0.3f); //Character image is in pixels not world units
		if(activePowerUp == 0) {
			app_.image(sprites[5], -165, 110);
		}
		else if(activePowerUp == 1){
			app_.image(sprites[6], -165, 110);
		}

		app_.popMatrix();

		//}
	}

	/**
	 * this method handles all movement of the player and the fire and frost attacks. Character only involves 
	 * vertical movement
	 */
	public void animate() {
		if (airborne) {
			y_ += (vy_ += gravity);
		}
		if (y_ <= -35) { //if the character goes below the world he dies
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

	/**
	 * this method handles all of the passive movement for the attacks. As the player moves, the attacks must move
	 * with the entire world.
	 * @param direction
	 * @param isMove
	 */
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
	 * Sets a y velocity and begins the jump animation
	 */
	public void jump() {
		if(airborne == false) {
			vy_ = 0.15f;
			spriteIndex = 3;
			airborne = true;
		}
	}

	//if player leaves a platform and there is nothing below, it falls
	public void fall() {
		spriteIndex = 3;
		airborne = true;
	}

	//sets the death image
	public void die() {
		spriteIndex = 4;
	}

	/**
	 * Sets the y velocity to zero and changes the image back to neutral
	 */
	public void land() {
		vy_ = 0f;
		airborne = false;
		if(spriteIndex == 3) {
			spriteIndex = 0;
		}
	}

	//Takes a hit and loses current powerup
	public void takeHit(Enemy e){
		health -= e.getDamage();
		activePowerUp = 4; //default no powerups
	}

	//return state of character
	public boolean isAirborne() {
		return airborne;
	}

	//returns the current image being used
	public int getImageIndex() {
		return spriteIndex;
	}

	//sets the current image being used
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

	/**
	 * This method adds a projectile to the correct arraylist if the character has a powerup
	 * @param dir
	 */
	public void shoot(float dir) {
		//System.out.println("activated");
		if(activePowerUp == 0) {

			if(airborne) {
				Fire temp = new Fire(x_+ (width/2 * -dir),y_- (vy_+gravity), 0, dir, sprites[8]);
				fireBalls.add(temp);
			}else {
				Fire temp = new Fire(x_+ (width/2*-dir),y_, 0, dir, sprites[8]); 
				fireBalls.add(temp);
			}
		}else if(activePowerUp == 1) {
			if(airborne) {
				Frost temp = new Frost(x_+ (width/2 * -dir),y_- (vy_+gravity), 0, dir, sprites[9]);
				frostBalls.add(temp);
			}else {
				Frost temp = new Frost(x_+ (width/2*-dir),y_, 0, dir, sprites[9]); 
				frostBalls.add(temp);
			}
		}
		else {
			return;
		}

	}

	/**
	 * This method takes in a number given from defeating an enemy and sets a power up
	 * @param number
	 */
	public void setActivePowerUp(int number) {
		activePowerUp = number;
		if(activePowerUp == 2) {
			//System.out.println("activated");
			setHealth(100);

		}
	}

	//return current powerup
	public int getActivePowerUp() {
		return activePowerUp;
	}

	//To change health when needed
	public void setHealth(int h) {
		health = h;
		activePowerUp = 4;
	}

	//return current health
	public int getHealth() {
		return health;
	}

	//returns an array list of frost attacks
	public ArrayList<Frost> getPowerUpFrost() {
		if( activePowerUp == 1) {
			return frostBalls;
		}else {
			return null;
		}
	}

	//returns an array of fire attacks
	public ArrayList<Fire> getPowerUpFire(){
		if( activePowerUp == 0 ) {
			return fireBalls;
		}else {
			return null;
		}
	}

}


/*
Author : Jack Botelho
This is the base class for all of the non-playable characters.
Some methods will be overwritten in subclasses.
This uses the Template Method Pattern
 */

package notmario;
import processing.core.PApplet;

import java.awt.Image;
import java.util.Random;
import java.util.ArrayList;
/*
Delete if not used!
import java.awt.Graphics;
 */

public abstract class Enemy implements ApplicationConstants {

	private int health; //health or hits remaining
	/*
    xcor -> x-coordinate
    ycor -> y-coordinate
    size -> size of enemy
    angle -> delete if we don't use rolling
    velXLocal -> velocity relative to the ground
    velXWorld -> cancels out scrolling
    velYLocal -> vertical velocity relative to the ground
    velYWorld -> cancels out screen movement
    width -> width of sprite
    height -> height of sprite
    playerSpeedX -> Scalar of the speed of player.
	 */
	private float xcor, ycor, size, velXLocal, velXWorld, width, height, velYLocal, velYWorld,playerSpeedX;
	public float angle; //detrmines angle of sprite
	public boolean isLeft, airborne, isMoving; //Checks if the enemy is facing left. Airbone checks if instance is airborne, isMoving is true if it is moving.
	private Image sprite; //The sprite of the enemy stored as an image.
	private int damage; //The amount of damage the enemy deals to the player
	private int random;
	/*
    Necessary for initialization of Character and detects MyWorld
	 */
	private static int appSetCounter_ = 0; //Needed for instance of PApplet
	private static PApplet app_; //Instance of PApplet

	//Initialized the enemy at a specific xcor, ycor and with the corresponding sprite.
	public void init(float x_, float y_, int hits, float w, float h, int d, Image img) {
		xcor = x_;
		ycor = y_;
		size = 1;
		angle = 0;
		health = hits;
		width = w;
		height = h;
		sprite = img;
		damage = d;
		isLeft = true;
		playerSpeedX = 0.1f;
		airborne = false;
	}

	//Initializes a null enemy.
	public void initNull(){
		xcor = 0;
		ycor = 0;
		size = 0;
		angle = 0;
		health = 0;
		width = 0;
		height  = 0;
		isLeft = true;
		airborne = false;
		playerSpeedX = 0.1f;
		die();
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

	//Method returns the current health.
	public int getHealth() {
		return health;
	}

	//Method to get co-ordinates. Returns x-cor and y-cor and width/height
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
	public boolean getMove(){return isMoving;}
	public int getDamage(){return damage;}

	//Methods to change speed / direction
	public void setVelXL(float a){velXLocal = a;}
	public void setVelXG(float a){velXWorld = a;}
	public void setVelYL(float a){velYLocal = a;}
	public void setVelYG(float a){velYWorld = a;}

	//Methods to determine sprite direction
	public void setDir(boolean a){
		isLeft = a;
	}

	//Methods to set or change xcor and ycor
	public void setXcor(float a){xcor = a;}
	public void setYcor(float a){ycor = a;}
	public void setMove(boolean a){isMoving = a;}

	//Method to set speed
	public void setSpeed(float a){xcor += a;}

	//Methods to set/change health remaining
	public void setHealth(int a){health = a;}

	//Method to land enemy based off character.land()
	public void land(){
		velYLocal = 0.0f;
		airborne = false;
	}

	//Allows enemy to fall
	public void fall(){
		if(airborne){
			ycor += (velYLocal += gravity);
		}
	}
	/*
    Draws enemy sprite.
    NOTE: Right now I have a temp method that will show the same box depending on enemy type.
    Will change later -Jack
	 */
	public void draw(){
		app_.pushMatrix();

		app_.stroke(0);
		app_.fill(0, 0, 255);

		app_.rect(xcor, ycor, width, height);

		app_.popMatrix();
	}

	//Passive move
	public void passiveMove(int direction, boolean isMove){
		if(isMove){
			xcor += (playerSpeedX * direction);
		}
	}

	public int collision(Character p) {
		float pLeftX = p.x_ - p.width/2;
		float pRightX = p.x_ + p.width/2;

		if((pRightX >= xcor) && (pLeftX <= xcor + width) && (p.y_ - p.height/2 <= ycor) && (p.y_ - p.height/2 >= ycor - height)){
			//System.out.println("You got hit");
			// System.out.println("Px: " + p.x_ + " Py: " + p.y_ + " Sx: " + xcor + " Sy: " + ycor);
			return -1;
		}
		else if((pRightX >= xcor) && (pLeftX <= xcor + width) && (p.y_ - p.height/2 >= ycor + height - 0.1f) && (p.y_ - p.height/2 <= ycor + height + 0.2f)){
			//System.out.println("Hit successful.");
			takeHit(p);
			p.vy_ = 0.1f;
			return 1;
		}
		return 0;
	}
	public int powerUpCollision(ArrayList<Fire> f, ArrayList<Frost> g, Enemy e) {

		if(f != null) {
			for(int i = 0; i<f.size(); i++) {
				if(f.get(i) != null) {
					float fLeftX = f.get(i).getXcoor() - f.get(i).getWidth()/2;
					float fRightX = f.get(i).getXcoor()+ f.get(i).getWidth()/2;
					if((int)fRightX == (int)xcor || (int)fLeftX == (int)xcor) {
						f.set(i, null);
						takeHitPow(2);
					

					}
				}
			}
		}
		if(g!= null) {

			for(int i = 0; i<g.size(); i++) {
				if(g.get(i)!= null) {
					float gLeftX = g.get(i).getXcoor()- g.get(i).getWidth()/2;
					float gRightX = g.get(i).getXcoor()+ g.get(i).getWidth()/2;
					if((int)gRightX == (int)xcor || (int)gLeftX == (int)xcor) { 
						g.set(i, null);
						e.setXspeed();
					}
				}
			}
		}


		return 0;
	}
	//Damages the enemy
	public void takeHit(Character p){
		if(health > 1){
			health--;
		}
		else {
			health = 0;
			Random rand = new Random();
			int chance = rand.nextInt(100);
			if(chance >= 0 && chance <= 70) { 
				chance = rand.nextInt(3);
				p.setActivePowerUp(chance);
			}


			die();
		}

	}
	public void takeHitPow(int dam) {
		if(health >1) {
			health -= dam;
		}
		else {
			health = 0;
			die();
		}
	}

	//Kills the enemy
	public void die(){




		xcor = -201;
		ycor = -201;
		//RNG Roll here
	}

	//Updates the enemies co-ordinates based on its movement pattern. Different for every enemy class.
	public abstract void moveCycle(LevelHandler h);

	//Collision for reaching the edge of a platform
	public abstract void turnAround();

	public void setXspeed() {
		velXLocal = 0;
	}
	//Switches between animation stances
	// public void animate();
}
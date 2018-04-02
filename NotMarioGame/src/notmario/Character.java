package notmario;

import processing.core.PApplet;

public class Character implements ApplicationConstants 
{
	private static int appSetCounter_ = 0;
	private static PApplet app_; 
	
	//All graphical parameters for drawing player and attack boxes
		private static final float FACE_DIAMETER = 2;
		private static final float BODY_DIAMETER1 = 2f;
		private static final float BODY_DIAMETER2 = 2.5f;
		private static final float BODY_OFFSET = FACE_DIAMETER;
		private static final float ARM_DIAMETER_W = 1;
		private static final float ARM_DIAMETER_H = 0.5f;
		private static final float LEG_OFFSET = 0.5f*FACE_DIAMETER+BODY_DIAMETER2;
		private static final float LEG_DIAMETER_W = ARM_DIAMETER_W;
		private static final float LEG_DIAMETER_H = ARM_DIAMETER_H;
		private static final float REFERENCE_BOX_W = 2f*FACE_DIAMETER;
		private static final float REFERENCE_BOX_H = FACE_DIAMETER + BODY_DIAMETER2 + 2f*LEG_DIAMETER_W;
		private static final float REFERENCE_BOX_OFFSET_X = -FACE_DIAMETER;
		private static final float REFERENCE_BOX_OFFSET_Y = -2.5f*FACE_DIAMETER;
		
		private boolean ref_ = false;
		private boolean airborne = false;
		private float x_, y_, size_, angle_, vx_, vy_, arm_angle, arm_angle2, arm_angle3, arm_angle4, leg_angle, leg_angle2, leg_angle3, leg_angle4;
		//color
		private float r_, b_, g_;
		
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
			
			r_ = r;
			b_ = b;
			g_ = g;
			
			//movement values in x and y direction
			vx_ = 0.1f;
			vy_ = 0.0f;
			
			arm_angle = (float)(Math.PI/3);
			arm_angle2 = 0;
			arm_angle3 = -(float)(Math.PI/3);
			arm_angle4 = 0;
			leg_angle = (float)(Math.PI/2);
			leg_angle2 = 0;
			leg_angle3 = (float)(Math.PI/2);
			leg_angle4 = 0;
			
		}
		
		/**
		 * draws all components of character onto the window. Passed a reference to the PApplet
		 * @param theApp
		 */
		public void draw() {
			
			// we use this object's instance variable to access the application's instance methods and variables
			app_.pushMatrix();
			
			//move to correct location, size, and orientation on the window
			app_.translate(x_, y_);
			app_.rotate(angle_);
			app_.scale(size_);
			
			//to see reference frames and attack boxes
			if(ref_) {
				app_.noFill();
				app_.stroke(255, 0, 0);
				app_.rect(REFERENCE_BOX_OFFSET_X, REFERENCE_BOX_OFFSET_Y, REFERENCE_BOX_W, REFERENCE_BOX_H);
				
			}
			
			app_.stroke(0);
			app_.fill(r_, g_, b_);
			
			drawArm2();
			
			drawBody();
			
			drawArm1();
			
			//draw head
			app_.ellipse(0, 0, FACE_DIAMETER, FACE_DIAMETER);
			
			//draw legs
			drawLeg1();
			
			drawLeg2();
			
			
			
			app_.popMatrix();
		}
		
		public void animate(float a, boolean move) {
			//set vx to the correct direction
			float vx = vx_ * a;
			
			// we use this object's instance variable to access the application's instance methods and variables
			//Players cannot leave right or left side of world
			if (((x_+ REFERENCE_BOX_OFFSET_X+REFERENCE_BOX_W) + vx  >= XMAX) || ((x_+ REFERENCE_BOX_OFFSET_X) + vx <= XMIN))
			{
				vx *= -1;
				x_ -= vx;
				
			}
			//Players cannot leave the top of world
			if(y_ + vy_ >= YMAX)
			{
				y_-= vy_;
			}
			//Players cannot leave floor of world (not quite bottom of world)
			if((y_-REFERENCE_BOX_H)+vy_ <= YMIN+2)
			{
				y_ -= vy_;
				land();
			}
			
			//Players land on the platform
			if((y_-REFERENCE_BOX_H) >= -22 && ((x_+ REFERENCE_BOX_OFFSET_X+REFERENCE_BOX_W/2) <= 30 && (x_) >= -30)) {
				if((y_-REFERENCE_BOX_H)+vy_ <= -22) {
					y_-=vy_;
					vy_-=gravity;
					land();
				}
			}
			
			//update location of player
			y_ += vy_;
			
			if(move == true)
				x_ += vx;
			
			//apply gravity
			vy_+=gravity;
		}
		
		/**
		 * Draw the left side arm(from user perspective)
		 */
		public void drawArm1() {
			app_.pushMatrix();
			app_.translate(-FACE_DIAMETER/2, -FACE_DIAMETER/2);
			app_.rotate(arm_angle);
			app_.translate(-ARM_DIAMETER_W/2, -ARM_DIAMETER_H/2);
			app_.ellipse(0, 0, ARM_DIAMETER_W, ARM_DIAMETER_H);
			app_.translate(-ARM_DIAMETER_W/2, 0);
			app_.rotate(arm_angle2);
			app_.translate(-ARM_DIAMETER_W/2, 0);
			app_.ellipse(0, 0, ARM_DIAMETER_W, ARM_DIAMETER_H);
			app_.popMatrix();
		}
		
		/**
		 * Draw the right side arm(from user perspective)
		 */
		public void drawArm2() {
			app_.pushMatrix();
			app_.translate(FACE_DIAMETER/2, -FACE_DIAMETER/2);
			app_.rotate(arm_angle3);
			app_.translate(ARM_DIAMETER_W/2, -ARM_DIAMETER_H/2);
			app_.ellipse(0, 0, ARM_DIAMETER_W, ARM_DIAMETER_H);
			app_.translate(ARM_DIAMETER_W/2, 0);
			app_.rotate(arm_angle4);
			app_.translate(ARM_DIAMETER_W/2, 0);
			app_.ellipse(0, 0, ARM_DIAMETER_W, ARM_DIAMETER_H);
			app_.popMatrix();
		}
		
		/**
		 * Draw the left side leg(from user perspective)
		 */
		public void drawLeg1() {
			app_.pushMatrix();
			app_.translate(-FACE_DIAMETER/4, -LEG_OFFSET+LEG_DIAMETER_W/2);
			app_.rotate(leg_angle);
			app_.translate(-LEG_DIAMETER_W/2, 0);
			app_.ellipse(0, 0, LEG_DIAMETER_W, LEG_DIAMETER_H);
			app_.translate(-LEG_DIAMETER_W/2, 0);
			app_.rotate(leg_angle2);
			app_.translate(-LEG_DIAMETER_W/2, 0);
			app_.ellipse(0, 0, LEG_DIAMETER_W, LEG_DIAMETER_H);
			app_.popMatrix();
		}
		
		/**
		 * Draw the left side leg(from user perspective)
		 */
		public void drawLeg2() {
			app_.pushMatrix();
			app_.translate(FACE_DIAMETER/4, -LEG_OFFSET+LEG_DIAMETER_W/2);
			app_.rotate(leg_angle3);
			app_.translate(-LEG_DIAMETER_W/2, 0);
			app_.ellipse(0, 0, LEG_DIAMETER_W, LEG_DIAMETER_H);
			app_.translate(-LEG_DIAMETER_W/2, 0);
			app_.rotate(leg_angle4);
			app_.translate(-LEG_DIAMETER_W/2, 0);
			app_.ellipse(0, 0, LEG_DIAMETER_W, LEG_DIAMETER_H);
			app_.popMatrix();
		}
		
		/**
		 * draw the body itself
		 */
		public void drawBody() {
			app_.pushMatrix();
			app_.translate(0, -BODY_OFFSET);
			app_.ellipse(0, 0, BODY_DIAMETER1, BODY_DIAMETER2);
			app_.popMatrix();
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
				vy_ = 0.12f;
				airborne = true;
			}
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

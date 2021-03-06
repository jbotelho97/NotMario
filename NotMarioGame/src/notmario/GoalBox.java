package notmario;

import processing.core.PApplet;
import processing.core.PImage;

public class GoalBox extends Rectangle implements ApplicationConstants {

	//  Here we store a reference to the app. in a static (aka "class") variable.
	private static PApplet app_; 
	private static int appSetCounter_ = 0;

	private PImage sprite;

	public GoalBox(float x, float y, float w, float h, float red, float green, float blue, PImage img) {
		super(x, y, w, h, red, green, blue);
		sprite = img;
		// TODO Auto-generated constructor stub
	}

	public void draw() {
		//super.draw();
		if(sprite != null) {
			//System.out.println("ok?");
			app_.pushMatrix();
			app_.scale(PIXEL_TO_WORLD, -PIXEL_TO_WORLD);
			app_.image(sprite, getX()/PIXEL_TO_WORLD, (getY()/PIXEL_TO_WORLD)-(getHeight()/PIXEL_TO_WORLD ));
			app_.popMatrix();
		}
	}

	@Override
	public boolean isInside(Character player, int dir) {
		float leftX = player.x_-player.width/2;
		float rightX = player.x_+player.width/2;
		float bottomY = player.y_-player.height/2;
		boolean inside = false;
		// Check bottom left corner of player character
		if((leftX >= getX()) && (leftX <= getX() + getWidth()) && (bottomY >= getY()) && (bottomY <= getY() + getHeight())) {
			//System.out.println(leftX + " " + rightX + " " + bottomY + " " + x_ + " " + y_ + " " + h_ + " " + w_);
			inside = true;
		}
		// Check bottom right corner of player character
		if ((rightX >= getX()) && (rightX <= getX() + getWidth()) && (bottomY >= getY()) && (bottomY <= getY() + getHeight())) {
			//System.out.println(leftX + " " + rightX + " " + bottomY + " " + x_ + " " + y_ + " " + h_ + " " + w_);
			inside = true;
		}
		return inside;

	}

	public boolean enemyInside(Enemy e){
		return false;
	}

	public boolean enemyEdge(Enemy e){
		return false;
	}

	public boolean playerEdge(Character player, int dir) {
		return false;
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
}

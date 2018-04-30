package notmario;

import processing.core.PApplet;

public class GoalBox extends Rectangle {
	
//  Here we store a reference to the app. in a static (aka "class") variable.
	private static PApplet app_; 
	private static int appSetCounter_ = 0;

	public GoalBox(float x, float y, float w, float h, float red, float green, float blue) {
		super(x, y, w, h, red, green, blue);
		// TODO Auto-generated constructor stub
	}
	
	public void draw() {
		super.draw();
	}

	public boolean isInside(Character player) {
		return true;
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

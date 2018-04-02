package notmario;
// test2
import processing.core.PApplet;

public class LevelHandler {
	
	private static int appSetCounter_ = 0;
	private static PApplet app_; 
	
	Rectangle[][] levelCollection;
	
	int currentLevel, platformIndex;
	
	public LevelHandler() {
		
		currentLevel = 0;
		platformIndex = 0;
		createMenu();     //create all levels and save them in levelCollection
		createLevel1();
		createLevel2();
		createLevel3();
		
	}
	
	/**
	 * This method draws each rectangle in the current row
	 */
	public void draw() {
		
		for(platformIndex = 0; platformIndex <= levelCollection[currentLevel].length; platformIndex++) {
			levelCollection[currentLevel][platformIndex].draw();
		}
	}
	
	/**
	 * this method moves each rectangle in the current row
	 * @param direction
	 */
	public void move(int direction) {
		
		for(platformIndex = 0; platformIndex <= levelCollection[currentLevel].length; platformIndex++) {
			levelCollection[currentLevel][platformIndex].move(direction);
		}
	}
	
	/**
	 * this method changes the current level by moving down one row in the array levelCollection
	 * @param count
	 */
	public void setLevel(int count) {
		currentLevel = count;
	}
	
	public void createMenu() {
		
	}
	
	public void createLevel1() {
		
	}
	
	public void createLevel2() {
		
	}
	
	public void createLevel3() {
		
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

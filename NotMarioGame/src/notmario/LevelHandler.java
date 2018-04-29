package notmario;
// test3
import processing.core.PApplet;

public class LevelHandler {
	
	private static int appSetCounter_ = 0;
	private static PApplet app_; 
	
	Rectangle[][] levelCollection;
	
	int currentLevel, platformIndex;
	
	public LevelHandler() {
		
		levelCollection = new Rectangle[4][20];
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
			if(levelCollection[currentLevel][platformIndex] == null)
				break;
			levelCollection[currentLevel][platformIndex].draw();
		}
	}
	
	/**
	 * this method moves each rectangle in the current row
	 * @param direction
	 */
	public void move(int direction, boolean move) {
		if(move) {
			for(platformIndex = 0; platformIndex <= levelCollection[currentLevel].length; platformIndex++) {
				if(levelCollection[currentLevel][platformIndex] == null)
					break;
				levelCollection[currentLevel][platformIndex].move(direction);
			}
		}
	}
	
	/**
	 * This method tests if the character has landed on any platforms.
	 */
	public boolean isInside(Character player) {
		for(platformIndex = 0; platformIndex < levelCollection[currentLevel].length; platformIndex++) {
			if(levelCollection[currentLevel][platformIndex] == null) {
				break;
			}

			if (levelCollection[currentLevel][platformIndex].isInside(player)) {
				System.out.println(platformIndex);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * this method changes the current level by moving down one row in the array levelCollection
	 * @param count
	 */
	public void setLevel(int count) {
		currentLevel = count;
	}
	
	public void createMenu() {
		
		levelCollection[0][0] = new Rectangle(0, -30, 25, 25, 255, 0, 0);
		levelCollection[0][1] = new Rectangle(-25, -30, 25, 25, 255, 0, 0);
		levelCollection[0][2] = new Rectangle(25, -30, 25, 25, 255, 0, 0);
		levelCollection[0][3] = new Rectangle(50, -30, 25, 25, 255, 0, 0);
		levelCollection[0][4] = new Rectangle(75, -30, 25, 25, 255, 0, 0);
		levelCollection[0][5] = new Rectangle(100, -30, 25, 25, 255, 0, 0);
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

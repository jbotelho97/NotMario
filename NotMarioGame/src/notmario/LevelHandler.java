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
		createWinMenu();
		createDeathMenu();
		
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
	public boolean isInside(Character player, int dir) {
		for(platformIndex = 0; platformIndex < levelCollection[currentLevel].length; platformIndex++) {
			if(levelCollection[currentLevel][platformIndex] == null) {
				break;
			}

			if (levelCollection[currentLevel][platformIndex].isInside(player, dir)) {
			//	System.out.println(platformIndex);
				return true;
			}
		}
		return false;
	}
	
	public boolean isInside(float x, float y) {
		for(platformIndex = 0; platformIndex < levelCollection[currentLevel].length; platformIndex++) {
			if(levelCollection[currentLevel][platformIndex] == null) {
				break;
			}

			if (levelCollection[currentLevel][platformIndex].isInside(x, y)) {
				System.out.println(platformIndex);
				return true;
			}
		}
		return false;
	}

	/**
	 * This method tests if the character has landed on any platforms.
	 */
	public boolean isEdge(Character player, int dir) {
		for(platformIndex = 0; platformIndex < levelCollection[currentLevel].length; platformIndex++) {
			if(levelCollection[currentLevel][platformIndex] == null) {
				break;
			}

			if (levelCollection[currentLevel][platformIndex].playerEdge(player, dir)) {
				System.out.println(platformIndex);
				return true;
			}
		}
		return false;
	}

	public boolean enemyInside(Enemy e) {
		boolean inside = false;
		boolean edge = false;
	    for(platformIndex = 0; platformIndex <= levelCollection[currentLevel].length; platformIndex++) {
			if(levelCollection[currentLevel][platformIndex] == null)
				break;
			inside |= levelCollection[currentLevel][platformIndex].enemyInside(e);
			edge |= levelCollection[currentLevel][platformIndex].enemyEdge(e);			
		}
		if (edge) { e.turnAround(); }
		return !inside;
	}
	
	public float returnIndex() {
		return platformIndex;
	}
	
	/**
	 * this method changes the current level by moving down one row in the array levelCollection
	 * @param count
	 */
	public void setLevel(int count) {
		currentLevel = count;
	}
	
	public void createMenu() {
		
		levelCollection[0][0] = new Rectangle(10, -20, 20, 10, 0, 255, 255);
		levelCollection[0][1] = new Rectangle(-30, -20, 20, 10, 255, 0, 255);
	}
	
	public void createLevel1() {
		
		levelCollection[1][0] = new Rectangle(-25, -65, 100, 60, 255, 0, 0);
		levelCollection[1][1] = new Rectangle(75, -65, 25, 70, 255, 0, 0);
		levelCollection[1][2] = new Rectangle(125, -65, 40, 70, 255, 0, 0);
		levelCollection[1][3] = new Rectangle(165, -65, 30, 55, 255, 0, 0);
		levelCollection[1][4] = new Rectangle(195, -65, 10, 70, 255, 0, 0);
		levelCollection[1][5] = new Rectangle(205, -65, 10, 85, 255, 0, 0);
		levelCollection[1][6] = new Rectangle(235, -65, 50, 55, 255, 0, 0);
		levelCollection[1][7] = new Rectangle(300, -65, 15, 65, 255, 0, 0);
		levelCollection[1][8] = new Rectangle(315, -65, 15, 75, 255, 0, 0);
		levelCollection[1][9] = new Rectangle(330, -65, 20, 85, 255, 0, 0);
		levelCollection[1][10] = new Rectangle(350, -65, 125, 55, 255, 0, 0);
		levelCollection[1][11] = new Rectangle(475, -65, 50, 125, 255, 0, 0);
		levelCollection[1][12] = new GoalBox(450, -15, 25, 25, 0, 0, 255);	
	}
	
	public void createWinMenu() {
		levelCollection[2][0] = new Rectangle(-10, -20, 20, 10, 255, 0, 255);
	}
	
	public void createDeathMenu() {
		levelCollection[3][0] = new Rectangle(-10, -20, 20, 10, 255, 0, 255);
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

package notmario;


import processing.core.PApplet;

public class MyWorld extends PApplet implements ApplicationConstants 
{
	private long frame_ = 0L;
	Rectangle platform;
	Character player1_;
	LevelHandler myGame;
	
	private int currentLevel = 0;
	private boolean animate_ = true;
	private boolean move_;
	private float dir1_;

	public void settings() {
		size(WIN_WIDTH, WIN_HEIGHT);
	}

	public void setup() {
		if ((width != WIN_WIDTH) || (height != WIN_HEIGHT))
		{
			println("Something went wrong in settings");
			System.exit(-2);
		}

		frameRate(400);
		
		myGame = new LevelHandler();

//		platform = new Rectangle(-30, -25, 60, 5, 255, 255, 255);
//
//		player1_ = new Character(-25, 25, 1, 255, 255, 255);

		setupGraphicClasses_(); // passes a reference of this app window to all graphical classes as a static variable
	}

	public void draw() {
		frame_++;

		//  Draw all objects
		if (frame_ % 5 == 0) 
		{
			background(0);

			// define world reference frame:  
			//    Origin at windows's center and 
			//    y pointing up
			//    scaled in world units
			translate(WORLD_ORIGIN_X, WORLD_ORIGIN_Y); 
			scale(WORLD_TO_PIXEL, -WORLD_TO_PIXEL);
			stroke(0);
			
			myGame.draw();

//			platform.draw();
//			player1_.draw();
		}

		if(animate_) {
//			player1_.animate(dir1_, move_);
		}
	}

	public void keyPressed() {
		switch(key) {
		//move left
		case 'a':
			dir1_ = -1;
			move_ = true;
			break;
			//move right
		case 'd':
			dir1_ = 1;
			move_ = true;
			break;
			//player 1 jump command
		case 'w':
//			player1_.jump();
			move_ = true;
			break;
		}
	}

	public void keyReleased() {

		switch(key) {
		//Causes all keys to stop movement (except jump)
		case 'a':
			dir1_ = 0;
			move_ = false;
			break;
		case 'd':
			dir1_ = 0;
			move_ = false;
			break;
			//toggle the player's reference and attack boxes
		case 't':
//			player1_.toggleRef();
			break;
			//pause the game
		case 'p':
			animate_ = !animate_;
			break;
		}


	}
	
	public void levelWin() {
		animate_ = false;
		myGame.setLevel(currentLevel+1);
		animate_ = true;
	}

	/**
	 * This is where the program passes a reference to the application.
	 */
	public void setupGraphicClasses_()
	{
		if (Rectangle.setup(this) != 1)
		{
			println("A graphic classe\'s setup() method was called illegally before this class");
			System.exit(-1);;
		}
		if (Character.setup(this) != 1)
		{
			println("A graphic classe\'s setup() method was called illegally before this class");
			System.exit(-1);;
		}
		if (LevelHandler.setup(this) != 1)
		{
			println("A graphic classe\'s setup() method was called illegally before this class");
			System.exit(-1);;
		}
	}

	public static void main(String[] args) {
		PApplet.main("notmario.MyWorld");
	}
}

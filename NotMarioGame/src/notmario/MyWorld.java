package notmario;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import processing.core.PApplet;
import processing.core.PFont;

import java.awt.font.*;

public class MyWorld extends PApplet implements ApplicationConstants 
{
	private long frame_ = 0L;
	//Rectangle platform;
	Character player1_;
	LevelHandler myGame;
	Enemy[] enemies;
    PFont f;


    private int currentLevel = 0;
    private int enemyCount = 0; //Temporary Counter for number of enemies in the level
	private boolean animate_ = true;
	private boolean move_, aButton, dButton;
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
		
		player1_ = new Character(0, 0, 1, 255, 0, 0);
		
		enemies = new Enemy[20];

		enemies[0] = new Spud(10,-5.0f);//Test Spud
		//enemies[1] = new Spudzilla(-10,2.50f);

        f = createFont("Arial",16,false); // Arial, 16 point, anti-aliasing off for right now

		setupGraphicClasses_(); // passes a reference of this app window to all graphical classes as a static variable
	}

	// GET IMAGE COMMENT LATER
		public static BufferedImage[] getImage(int xStart, int yStart, int rows, int cols, int count) {
			
			BufferedImage Img = null;
			try {
				
			Img = ImageIO.read(new File("character_sheet.png"));
			
			}
			
			catch(IOException ex)
			{
				
				JOptionPane.showMessageDialog(null, "<html>Error<br>Missing images</html>" ,
					       "Error",JOptionPane.ERROR_MESSAGE);
				
				System.exit(1);
				
			}
			
			// The above line throws an checked IOException which must be caught.
			
			BufferedImage[] sprites = new BufferedImage[count];

			for (int i = 0; i < count; i++)
			{
		
				sprites[count] = Img.getSubimage(
		            i * xStart,
		            yStart,
		            cols,
		            rows
			        );
			}
			
			
			return sprites;
			
		}
	
	public void draw() {
		frame_++;
		
		//  Draw all objects
		if (frame_ % 5 == 0) 
		{
			background(255);

			// define world reference frame:  
			//    Origin at windows's center and 
			//    y pointing up
			//    scaled in world units
			translate(WORLD_ORIGIN_X, WORLD_ORIGIN_Y); 
			scale(WORLD_TO_PIXEL, -WORLD_TO_PIXEL);
			stroke(0);



			myGame.draw();

//			platform.draw();
			player1_.draw();
			//TESTING ENEMIES - Jack
			enemies[0].draw();
            drawHealth(player1_.health); //Temporary Health Bar -Jack
			//enemies[1].draw();
            point(0,0);
           // point(10,-5);
		}

		if(animate_) {
			player1_.animate();
			myGame.move((int)dir1_, move_);

			if(myGame.isInside(player1_)) {
				System.out.println("inside");
				player1_.land();
			}
			else {
				System.out.println("outside");
				player1_.fall();
			}
			/*enemies[0].passiveMove((int)dir1_, move_);
			//enemies[1].passiveMove((int)dir1_, move_);
            myGame.isInside(player1_);
			enemies[0].moveCycle(myGame);
            point(enemies[0].getXcoor(), enemies[0].getYcoor());
            int x = enemies[0].collision(player1_);
            switch(x){
                case 1: //Player hits an enemy on the head
                    player1_.jump();
                    break;
                case 0: //Player hits nothing
                    break;
                case -1: //Player collides with an enemy.
                    player1_.takeHit(enemies[0]);
                    myGame.move(75, true);
                    enemies[0].passiveMove(75, true);
            }*/
		}
	}

	public void drawHealth(int h){
	    textFont(f,2);
	    fill(0);
	    scale(1,-1);
	    textAlign(LEFT);
	    String s = "Player Health : " + h;
	    text(s, -50, 35);

    }

	public void cleanEnemies() {
		int i = 0;
		Enemy[] newEnemies = new Enemy[enemies.length];
		for (int j = 0; j < enemies.length; j++) {
			if (enemies[j] == null) {
				return;
			}

			if (enemies[j].getHealth() > 0) {
				newEnemies[i] = enemies[j];
				i++;
			}
		}
		enemies = newEnemies;
	}
	
	public void keyPressed() {
		switch(key) {
		//move left
		case 'a':
			dir1_ = 1;
			move_ = true;
			aButton = true;
			break;
			//move right
		case 'd':
			dir1_ = -1;
			move_ = true;
			dButton = true;
			break;
			//player 1 jump command
		case 'w':
			player1_.jump();
//			move_ = true;
			break;
		}
	}

	public void keyReleased() {

		switch(key) {
		//Causes all keys to stop movement (except jump)
		case 'a':
			aButton = false;
			if(dButton)
				dir1_ = -1;
			break;
		case 'd':
			dButton = false;
			if(aButton)
				dir1_ = 1;
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
		
		if(aButton || dButton)
			move_ = true;
		else {
			dir1_ = 0;
			move_ = false;
		}


	}
	
	public void levelWin() {
		animate_ = false;
		currentLevel+=1;
		myGame.setLevel(currentLevel);
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
			System.exit(-1);
		}
		if (Character.setup(this) != 1)
		{
			println("A graphic classe\'s setup() method was called illegally before this class");
			System.exit(-1);
		}
		if (LevelHandler.setup(this) != 1)
		{
			println("A graphic classe\'s setup() method was called illegally before this class");
			System.exit(-1);
		}
		if (Enemy.setup(this) != 1)
		{
			println("A graphic classe\'s setup() method was called illegally before this class");
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		PApplet.main("notmario.MyWorld");
	}
}

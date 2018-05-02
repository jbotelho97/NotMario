package notmario;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import org.apache.commons.lang3.ArrayUtils;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;



public class MyWorld extends PApplet implements ApplicationConstants 
{
	private long frame_ = 0L;
	//Rectangle platform;
	Character player1_;
	LevelHandler myGame;
	Enemy[] enemies;
    PFont f;


    private int currentLevel = 0;
	private boolean animate_ = false;
	private boolean move_, aButton, dButton;
	private float dir1_, lastdir_;
    public int enemyCount;
    private PImage[] ourSprites;
    
    private Timer timer;


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
		
		//Cuts from sprite sheet all sprite needed for objects
				ourSprites = getImage(0,   0, 300, 300, 2);
				ourSprites = ArrayUtils.addAll(ourSprites, getImage(300, 300,  40,  40, 2));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage(  0, 300,  40,  74, 5));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage(200, 300,  43,  78, 1));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage(  0, 380,  13,  14, 3));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage( 40, 380,  12,  11, 1));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage( 60, 380,  17,  15, 1));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage(  0, 400,  15,  15, 1));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage(  0, 415,  15,  36, 1));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage(  0, 460,  11,  11, 1));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage(  0, 500,  12,  17, 1));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage(  0, 500,  26,  28, 1));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage(  0, 550,  24,  16, 1));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage( 25, 550,  32,  21, 1));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage( 60, 550,  48,  49, 1));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage( 60, 480,  41,  65, 1));
				ourSprites = ArrayUtils.addAll(ourSprites, getImage(110, 425, 184, 172, 1));
				//ourSprites = ArrayUtils.addAll(ourSprites, getImage(23,549,34,1,1));
				
		timer = new Timer();
		myGame = new LevelHandler();

        f = createFont("Arial",16,false); // Arial, 16 point, anti-aliasing off for right now

		setupGraphicClasses_(); // passes a reference of this app window to all graphical classes as a static variable
	}

	// GET IMAGE COMMENT LATER
		public PImage[] getImage(int xStart, int yStart, int width, int height, int count) {
			
			PImage Img;
				
			Img = loadImage("cs.png");
			
			if(Img == null) {
				System.exit(1);
			}
			
			// The above line throws an checked IOException which must be caught.
			
			PImage[] sprites = new PImage[count];

			for (int i = 0; i < count; i++)
			{
		
				sprites[i] = Img.get(
		            xStart + (i * width),
		            yStart,
		            width,
		            height
			        );
			}
			
			
			return sprites;
			
		}

	//20 - Spud, 21- A.Spud 22 -spudzilla
	public void generateEnemies(LevelHandler h){
        //If you want a test enemy, un comment next two lines.

    
	    switch(h.currentLevel){
	    
	    case 1:
			enemies[0] = new Spud(25,-3f,ourSprites[20]);//Test Spud
	        enemies[1] = new Spud(175,-5.0f,ourSprites[20]);//Test Spud
	        enemies[2] = new ArmoredSpud(255,-10.0f,ourSprites[21]);//Test Spud
	        enemies[3] = new ArmoredSpud(450, -10.0f, ourSprites[21]);
	        enemies[4] = new Spudzilla(375, -10.0f, ourSprites[22]);
	        enemies[5] = new ArmoredSpud(425, -10.0f, ourSprites[21]);
	        enemyCount += 6;
        }
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
			
			if(currentLevel == 1) {
//				platform.draw();
				player1_.draw();
				//TESTING ENEMIES - Jack
				for(Enemy e : enemies) {
					if(e != null) {
						e.draw();
					}
				}
	            drawHealth(player1_.health); //Temporary Health Bar -Jack
	            //System.out.println(player1_.getHealth());
				//enemies[1].draw();
	            //point(0,0);
	           // point(10,-5);
			}
			else if(currentLevel == 2) {
				drawWinMenu();
			}
			else if(currentLevel == 3) {
				System.out.println("activated" + ":" + currentLevel);
				drawDeathMenu();
			}
			else
				drawMenu();
		}


		if(animate_) {
			player1_.animate();

			if(myGame.isInside(player1_, (int)dir1_)) {
			//	System.out.println("inside");
				if(myGame.returnIndex() == 12) {
					currentLevel = 2;
					myGame.setLevel(currentLevel);
					animate_ = false;
				}
				else {
				player1_.land();
				}
			}
			else {
              //  System.out.println("outside");
                player1_.fall();
            }
			boolean onEdge = myGame.isEdge(player1_, (int)dir1_);
			if(!onEdge) {
				myGame.move((int)dir1_, move_);
				player1_.passiveMoveProjectiles((int)dir1_, move_);
            }
			for(int i = 0; i < enemyCount; i++) {
			    if(enemies[i] != null) {
			    	if(enemies[i].getHealth() >= 0) {
	                    if (!onEdge) {
	                    		enemies[i].passiveMove((int) dir1_, move_);//Moving w/ the screen
	                    }
	                    enemies[i].moveCycle(myGame); //Unique Movement Cycle
	                    if(player1_.getActivePowerUp() == 0 || player1_.getActivePowerUp() == 1) {
	                    
	                    int y = enemies[i].powerUpCollision(player1_.getPowerUpFire(), player1_.getPowerUpFrost(), enemies[i]);
	                    }
	                    int x = enemies[i].collision(player1_); //Checks if player collides with enemy.
	                    switch (x) {
	                        case 1: //Player hits an enemy on the head
	                            player1_.jump();
	                            break;
	                        case 0: //Player hits nothing
	                            break;
	                        case -1: //Player collides with an enemy.
	                            player1_.takeHit(enemies[i]);
	                            myGame.move(75, true);
	                            enemies[i].passiveMove(75, true);
	                            
	                            break;
	                    }
	                }
			    }
		    }

			if (player1_.getHealth() <= 0) {
				playerDie();
			}
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
	
	public void playerDie(){
		
		player1_.die();
		currentLevel = 3;
		myGame.setLevel(3);
		animate_ = false;
	}
	
	public void drawMenu() {
		textFont(f,5);
		fill(0);
		scale(1,-1);
		textAlign(CENTER);
		String s = "Potato Farmer XTREME";
		String s1 = "Play";
		String s2 = "Quit";
		text(s, 0, -30);
		text(s1 , 20, 17);
		text(s2, -21, 17);
		pushMatrix();
		scale(PIXEL_TO_WORLD*2);
		image(ourSprites[4], -20, -50);
		popMatrix();
	}
	
	public void drawWinMenu() {
		textFont(f,5);
		fill(0);
		scale(1,-1);
		textAlign(CENTER);
		String s = "Y OU WIN!";
		String s2 = "Quit";
		text(s, 0, -30);
		text(s2, 0, 17);
		pushMatrix();
		scale(PIXEL_TO_WORLD*2);
		image(ourSprites[9], -20, -50);
		popMatrix();
	}
	
	public void drawDeathMenu() {
		textFont(f,5);
		fill(0);
		scale(1,-1);
		textAlign(CENTER);
		String s = "Y ou Died!";
		String s2 = "Quit";
		text(s, 0, -30);
		text(s2, 0, 17);
		pushMatrix();
		scale(PIXEL_TO_WORLD*2);
		image(ourSprites[8], -20, -50);
		popMatrix();
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
		if (player1_ == null || !animate_) {
			return;
		}

		switch(key) {
		//move left
		case 'a':
			dir1_ = 1f;
			lastdir_ = 1f;
			move_ = true;
			aButton = true;
			break;
			//move right
		case 'd':
			dir1_ = -1f;
			lastdir_ = -1f;
			move_ = true;
			dButton = true;
			break;
			//player 1 jump command
		case 'w':
			player1_.jump();
//			move_ = true;
			break;
		case 'f':
			player1_.shoot(lastdir_);
		}
		
		if(aButton) {
			if(!player1_.isAirborne()) {
				player1_.setImageIndex(1);
			}
		}
		if(dButton) {
			if(!player1_.isAirborne()) {
				player1_.setImageIndex(2);
			}
		}
	}

	public void keyReleased() {
		if (player1_ == null || (!animate_ && key != 'p')) {
			return;
		}

		switch(key) {
		//Causes all keys to stop movement (except jump)
		case 'a':
			aButton = false;
			if(dButton) {
				dir1_ = -1;
				lastdir_ = -1;
				if(!player1_.isAirborne()) {
					player1_.setImageIndex(2);
				}
			}
			break;
		case 'd':
			dButton = false;
			if(aButton) {
				dir1_ = 1;
				lastdir_ = 1;
				if(!player1_.isAirborne()) {
					player1_.setImageIndex(1);
				}
			}
			break;
			
		case 'w':
			player1_.setImageIndex(0);
			break;
			//toggle the player's reference and attack boxes
		case 't':
//			player1_.toggleRef();
			break;
			//pause the game
		case 'p':
			if (player1_.getHealth() > 0)
				animate_ = !animate_;
			break;
		}
		
		if(aButton || dButton)
			move_ = true;
		else {
			dir1_ = 0;
			if(!player1_.isAirborne()) {
				player1_.setImageIndex(0);
			}
			move_ = false;
		}


	}
	
	public void mousePressed() {
		if(currentLevel == 0) {
			System.out.println(((mouseX-WORLD_ORIGIN_X)*PIXEL_TO_WORLD) + " " + ((mouseY-WORLD_ORIGIN_Y)*-PIXEL_TO_WORLD));
			if(myGame.isInside(((mouseX-WORLD_ORIGIN_X)*PIXEL_TO_WORLD), ((mouseY-WORLD_ORIGIN_Y)*-PIXEL_TO_WORLD))) {
				if(myGame.returnIndex() == 0)
					Restart();
				else
					System.exit(0);
			}
		}
		else if(currentLevel == 2) {
			if(myGame.isInside(((mouseX-WORLD_ORIGIN_X)*PIXEL_TO_WORLD), ((mouseY-WORLD_ORIGIN_Y)*-PIXEL_TO_WORLD))) {
				System.exit(0);
			}
		}
		else if(currentLevel == 3) {
			if(myGame.isInside(((mouseX-WORLD_ORIGIN_X)*PIXEL_TO_WORLD), ((mouseY-WORLD_ORIGIN_Y)*-PIXEL_TO_WORLD))) {
				System.exit(0);
			}
		}
	}
	
	public void Restart() {
		animate_ = false;
		currentLevel=1;
		myGame.setLevel(currentLevel);
		
		player1_ = new Character(0, 0, 1, 255, 0, 0, ArrayUtils.addAll(Arrays.copyOfRange(ourSprites, 4, 9), Arrays.copyOfRange(ourSprites,10, 15)));
		
		enemies = new Enemy[20];

        generateEnemies(myGame);
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
		if (Fire.setup(this) != 1)
		{
			println("A graphic classe\'s setup() method was called illegally before this class");
			System.exit(-1);
		}
		if (Frost.setup(this) != 1)
		{
			println("A graphic classe\'s setup() method was called illegally before this class");
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		PApplet.main("notmario.MyWorld");
	}
}

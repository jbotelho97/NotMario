/*
Author : Jack Botelho
This is the base class for all of the non-playable characters.
Some methods will be overwritten in subclasses.
This uses the Template Method Pattern
 */

package notmario;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public abstract class Enemy {

    private int xcor, ycor, health; //x-coordinate of center, y-coordinate of center, health remaining
    private boolean los; //If this instance sees an enemy.
    private Image sprite; //The sprite of the enemy stored as an image.

    //True if the player character is within the enemies line-of-sight
    abstract boolean detect();

    //Initialized the enemy at a specific xcor, ycor and with the corresponding sprite.
    public void init(int x, int y, ImageIcon img){
        xcor = x;
        ycor = y;
        sprite = img.getImage();
    }

    //Updates the enemies co-ordinates based on its movement pattern.
    public void updateCoor(int x, int y){
        xcor = x;
        ycor = y;
    }

    //Change when I have time to understand PApplet graphics
    public void paintComponent(Graphics g){
        g.drawImage(sprite, xcor, ycor, null);
    }
}

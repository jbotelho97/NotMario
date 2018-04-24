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

    private int health; //health or hits remaining
    /*
    xcor -> x-coordinate
    ycor -> y-coordinate
    size -> size of enemy
    angle -> delete if we don't use rolling
    velXLocal -> velocity relative to the ground
    velXWorld -> cancels out scrolling
    width -> width of sprite
    height -> height of sprite
     */
    private float xcor, ycor, size, angle, velXLocal, velXWorld, width, height;
    private boolean los; //If this instance is true this sees an enemy.
    private Image sprite; //The sprite of the enemy stored as an image.

    //Initialized the enemy at a specific xcor, ycor and with the corresponding sprite.
    public void init(float x_, float y_, int hits, float w, float h, ImageIcon img) {
        xcor = x_;
        ycor = y_;
        size = 1;
        angle = 0;
        health = hits;
        width = w;
        height = h;
        sprite = img.getImage();
    }

    public int getHealth() {
        return health;
    }

    //Updates the enemies co-ordinates based on its movement pattern.
    public void updateCoor(int x, int y) {
        xcor = x;
        ycor = y;
    }
}
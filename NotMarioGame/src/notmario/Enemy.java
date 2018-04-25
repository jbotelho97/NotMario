/*
Author : Jack Botelho
This is the base class for all of the non-playable characters.
Some methods will be overwritten in subclasses.
This uses the Template Method Pattern
 */

package notmario;
import processing.core.PApplet;

import java.awt.Image;
/*
Delete if not used!
import java.awt.Graphics;
 */

public abstract class Enemy {

    private int health; //health or hits remaining
    /*
    xcor -> x-coordinate
    ycor -> y-coordinate
    size -> size of enemy
    angle -> delete if we don't use rolling
    velXLocal -> velocity relative to the ground
    velXWorld -> cancels out scrolling
    velYLocal -> vertical velocity relative to the ground
    velYWorld -> cancels out screen movement
    width -> width of sprite
    height -> height of sprite
     */
    private float xcor, ycor, size, angle, velXLocal, velXWorld, width, height, velYLocal, velYWorld;
    private boolean isLeft, airborne; //Checks if the enemy is facing left. Airbone checks if instance is airborne.
    private Image sprite; //The sprite of the enemy stored as an image.
    /*
    Necessary for initialization of Character and detects MyWorld
     */
    private static int appSetCounter_ = 0;
    private static PApplet app_;

    //Initialized the enemy at a specific xcor, ycor and with the corresponding sprite.
    public void init(float x_, float y_, int hits, float w, float h, Image img) {
        xcor = x_;
        ycor = y_;
        size = 1;
        angle = 0;
        health = hits;
        width = w;
        height = h;
        sprite = img;
        isLeft = true;
    }

    public void initNull(){
        xcor = 0;
        ycor = 0;
        size = 0;
        angle = 0;
        health = 0;
        width = 0;
        height  = 0;
        isLeft = true;
    }

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

    //Method returns the current health.
    public int getHealth() {
        return health;
    }

    //Method to get co-ordinates. Returns x-cor and y-cor and width/height
    public float getXcoor(){
        return xcor;
    }
    public float getYcoor(){
        return ycor;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight(){
        return height;
    }
    public Image getSprite(){
        return sprite;
    }

    //Methods to change speed / direction
    public void setVelXL(float a){velXLocal = a;}
    public void setVelXG(float a){velXWorld = a;}
    public void setVelYL(float a){velYLocal = a;}
    public void setVelYG(float a){velYWorld = a;}

    //Methods to set or change xcor and ycor
    public void setXcor(float a){xcor = a;}
    public void setYcor(float a){ycor = a;}

    //Methods to set/change health remaining
    public void setHealth(int a){health = a;}

    //Updates the enemies co-ordinates based on its movement pattern. Different for every enemy class.
    public abstract void moveCycle();

    //Switches between animation stances
    public abstract void animate();
}
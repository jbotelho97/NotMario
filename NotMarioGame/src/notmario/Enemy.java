/*
Author : Jack Botelho
This is the base class for all of the non-playable characters.
Some methods will be overwritten in subclasses.
This uses the Template Method Pattern
 */

package notmario;
import processing.core.PApplet;

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
    /*
    Necessary for initialization of Character and detects MyWorld
     */
    private static int appSetCounter_ = 0;
    private static PApplet app_;

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



    //Updates the enemies co-ordinates based on its movement pattern. Different for every enemy class.
    public abstract void move();
}
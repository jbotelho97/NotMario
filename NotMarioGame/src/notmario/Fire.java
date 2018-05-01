package notmario;
import processing.core.PApplet;
import processing.event.KeyEvent;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Event;

public class Fire implements Powerup, ApplicationConstants {
	private Image fireIcon;
	private ArrayList <Fire> shooting;//where our bullets will be stored
	private float xcor, ycor, size, width, height;
    private float projectileSpeed = 0.1f;
	private int powerUpIndex;
	private static PApplet app_;
	private boolean active = true;
	
	
	private int damage;
	
	public Fire(float x, float y, int chance) {
		if (chance ==  0) {
			this.xcor = x;
			this.ycor = y;
			active = true;
			damage = 2;
			size = 1;
			height = 2;
			width = 2;
			shooting = new ArrayList();
		}else {
			return;
		}
	}
	
	 public float getXcoor(){
     return xcor;
	 }
	 public float getYcoor(){
     return ycor;
	 }
	 public float getWidth(){
     return width;
	 }
	 public float getHeight(){
     return height;
	 }
	 public Image getSprite(){
     return fireIcon;
	 }
	 public void draw(float dir){
		if(active) {
			 moveAll();//move all the bullets
			  displayAll();//display all the bullets
		}
	}
	 public void display()
	  {
	    app_.stroke(255, 0 , 0);
	    app_.rect(xcor,ycor, 2, 2);
	  }
	 public void move() {
		 if(active) {
			 ycor -= 0.5;
		 }
	 }
	 public void moveAll()
	 {
	   for(Fire temp : shooting)
	   {
	     temp.move();
	   }
	 }
	 void displayAll()
	 {
	   for(Fire temp : shooting)
	   {
	     temp.display();
	   }
	 }
	 void mousePressed()//add a new bullet if mouse is clicked
	 {
		 System.out.println("activated");
	   Fire temp = new Fire(++xcor,ycor, 0);
	   shooting.add(temp);
	 }
	 
	 public void spawnPowerup() {}
	
	
}
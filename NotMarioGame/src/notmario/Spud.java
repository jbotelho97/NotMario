/*
Author : Jack Botelho
Spud is a basic enemy similar to a goomba, he walks foward until he hits a wall or walks off an edge.
If he hits a wall he turns around.
 */
package notmario;
import processing.core.PApplet;

import java.awt.Image;
public class Spud extends Enemy {

    private Image spudIcon; //Spud's sprite
    private boolean isMoving; //Is true if spud is moving, false if spud is dead or not moving.

    //Standard initialization of a new spud.
    public Spud(float x, float y){
        super.init(x, y, 1, 5,5, spudIcon);
    }

    //For a null spud.
    public Spud(){
        super.initNull();
    }

    //Spud's unique move-cycle
    public void moveCycle(){
        isMoving = true;
        setVelXL(1);
    }

    public void animate(){}
}

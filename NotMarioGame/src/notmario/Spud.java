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
    private float xspeed; //Spud's current speed/direction horizontally.

    //Standard initialization of a new spud.
    public Spud(float x, float y){
        super.init(x, y, 1, 5,5, 25, spudIcon);
        xspeed = -.060f;
        //xspeed = -10;
    }

    //For a null spud.
    public Spud(){
        super.initNull();
    }

    //Spud's unique move-cycle
    public void moveCycle(LevelHandler h){
        setSpeed(xspeed);
        airborne = h.enemyInside(this);
        if(airborne){
            fall();
        }
        else{
            land();
        }
    }

    //Turns Spud around when he hits the edge of a wall
    public void turnAround(){
        xspeed *= -1;
    }

}

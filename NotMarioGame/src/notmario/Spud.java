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
        super.init(x, y, 1, 5,5, spudIcon);
        xspeed = 1.0f;
    }

    //For a null spud.
    public Spud(){
        super.initNull();
    }

    //Spud's unique move-cycle
    public void moveCycle(){
        setMove(true);
        setVelXL(xspeed);
    }

    public void animate(){}
}

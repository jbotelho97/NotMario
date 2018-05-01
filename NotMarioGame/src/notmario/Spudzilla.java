/*
Author : Jack Botelho
Spudzilla is a bigger enemy that takes two hits to kill.
He is more intelligent and throws projectiles and tries to keep distance from a player.
 */
package notmario;
import processing.core.PApplet;
import java.awt.Image;

public class Spudzilla extends Enemy {

    private Image spudIcon; //Spudzilla's sprite
    private float xspeed; //Spudzilla's current speed/direction horizontally.
    public boolean jumping;

    public Spudzilla(float x, float y){
        super.init(x, y, 2, 7,9, 50, spudIcon);
        xspeed = -0.06f;
        isLeft = true;
    }

    //For a null spud.
    public Spudzilla(){
        super.initNull();
    }

    //Spudzilla will jump
    public void jump(){
        if(jumping && getYcoor() <= 20){
            float y = getYcoor();
            y += 0.15f;
            setYcor(y);
        }
        else{
            jumping = false;
            airborne = true;
            fall();
        }
    }

    //Checks jump height
    private boolean jumpable(float y){
        if(y >= 20){

        }
        return true;
    }

    public void moveCycle(LevelHandler h){
        setSpeed(xspeed);
        airborne = h.enemyInside(this);
        if(jumping){
            jump();
        }
        else if(airborne){
            fall();
        }
        else{
            land();
            jumping = true;
        }
    }

    @Override
    public void turnAround(){xspeed *= -1;}


}

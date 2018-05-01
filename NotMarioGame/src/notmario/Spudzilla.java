/*
Author : Jack Botelho
Spudzilla is a bigger enemy that takes two hits to kill.
He is a jumping enemy who endlessly jumps until he dies or kills the player.
 */
package notmario;
import java.awt.Image;

public class Spudzilla extends Enemy {

    private Image spudIcon; //Spudzilla's sprite
    private float xspeed; //Spudzilla's current speed/direction horizontally.
    public boolean jumping; //Is true when Spudzilla is jumping.

    public Spudzilla(float x, float y){ //Standard Spudzilla initialization
        super.init(x, y, 2, 6,8, 50, spudIcon);
        xspeed = -0.06f;
        isLeft = true;
    }

    //For a null spud.
    public Spudzilla(){
        super.initNull();
    }

    //Spudzilla will jump
    public void jump(){
        if(jumping && getYcoor() <= 20){//If he hasn't reached the maximum height of his jump
            float y = getYcoor();
            y += 0.15f;
            setYcor(y);
        }
        else{//This lets him fall back down.
            jumping = false;
            airborne = true;
            fall();
        }
    }


    //Override of the moveCycle. Spudzilla jumps and moves to the left until he dies or is killed.
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

    //Turns Spudzilla around
    @Override
    public void turnAround(){xspeed *= -1;}


}

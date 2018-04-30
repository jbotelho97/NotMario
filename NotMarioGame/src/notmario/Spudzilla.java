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
    FlyingPotato billy;

    public Spudzilla(float x, float y){
        super.init(x, y, 2, 10,20, 75, spudIcon);
        xspeed = 0.06f;
    }

    //For a null spud.
    public Spudzilla(){
        super.initNull();
    }

    public FlyingPotato spawn(){
        FlyingPotato bobby = new FlyingPotato(getXcoor(), getYcoor() + getWidth() * 0.75f);
        return bobby;
    }

    public void moveCycle(LevelHandler h){
        if(getXcoor() <= 30 && getXcoor() >= 0){
            setSpeed(xspeed);
        }
        else if(getXcoor() >= -30 && getXcoor() <= 0){
            setSpeed(-1 * xspeed);
        }
        if(billy != null && billy.alive == true){
            billy.moveCycle(h);
        }
        else{
            billy = spawn();
        }
    }

    public void turnAround(){}


}

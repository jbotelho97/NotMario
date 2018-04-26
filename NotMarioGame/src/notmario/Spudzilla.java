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
    private int xspeed; //Spudzilla's current speed/direction horizontally.

    public Spudzilla(float x, float y){
        super.init(x, y, 1, 10,20, spudIcon);
    }

    //For a null spud.
    public Spudzilla(){
        super.initNull();
    }

    public void moveCycle(){}
    public void animate(){}

}

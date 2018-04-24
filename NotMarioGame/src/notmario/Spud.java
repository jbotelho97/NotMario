package notmario;
import processing.core.PApplet;

import java.awt.Image;
public class Spud extends Enemy {

    private Image spudIcon;

    public Spud(float x, float y){
        super.init(x, y, 1, 100,100, spudIcon);
    }

    public Spud(){
        super.initNull();
    }

    public void move(){
    }

    public void animate(){

    }

}

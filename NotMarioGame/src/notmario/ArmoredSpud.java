package notmario;
import processing.core.PImage;


public class ArmoredSpud extends Enemy {

    private float xspeed; //speed of movement

    public ArmoredSpud(float x, float y, PImage aSpudIcon){
        super.init(x, y, 2,5, 5,35, aSpudIcon);
        xspeed = -0.06f;
    }

    //Initializes a null spud
    public ArmoredSpud(){
        super.initNull();
    }

    //Overrided the MoveCycle. Spud walks left untill he hits
    @Override
    public void moveCycle(LevelHandler h) {
        setSpeed(xspeed);
        airborne = h.enemyInside(this);
        if(airborne){
            turnAround();
        }
    }

    //To turn the spud around
    public void turnAround(){
        xspeed *= -1;
    }
    
    //to change spud's speed
    public void setXspeed() {
    	xspeed = 0;
    }
}

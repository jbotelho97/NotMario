package notmario;
import java.awt.Image;

public class ArmoredSpud extends Enemy {

    private float xspeed; //speed of movement
    private Image aSpudIcon;

    public ArmoredSpud(float x, float y){
        super.init(x, y, 2,5, 5,35, aSpudIcon);
        xspeed = -0.075f;
    }

    public ArmoredSpud(){
        super.initNull();
    }

    @Override
    public void moveCycle(LevelHandler h) {
        setSpeed(xspeed);
        airborne = h.enemyInside(this);
        if(airborne){
            turnAround();
        }
    }

    public void turnAround(){
        xspeed *= -1;
    }
}
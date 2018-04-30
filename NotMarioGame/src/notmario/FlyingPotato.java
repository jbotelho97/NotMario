package notmario;

import java.awt.Image;

public class FlyingPotato extends Enemy {

    private Image ballSprite;
    private float xspeed;
    public boolean alive;

    public FlyingPotato(float x, float y){
        super.init(x, y, 1000, 1,1,25, ballSprite);
        xspeed = 0.80f;
    }

    public FlyingPotato(){
        super.initNull();
    }

    @Override
    public void turnAround(){
        return;
    }

    @Override
    public void moveCycle(LevelHandler h) {
        setSpeed(xspeed);
}

}

package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;

public class GamePlayLoop extends AnimationTimer {
    protected Main main;
    Pos location;
    @Override
    public void handle(long now) {
        main.main.update();
        main.iBeagle.update();
    }
    public GamePlayLoop(Main main){
        super();
        this.main = main;
    }
}

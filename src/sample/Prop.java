package sample;

import javafx.scene.image.Image;

public class Prop extends Actor {

    public Prop(String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        spriteFrame.setTranslateX(xLocation);
        spriteFrame.setTranslateY(yLocation);
    }
    @Override
    public void update() {

    }
    public Prop(Prop prop, double x, double y){
        super(prop.getSpriteBound().getContent(), prop.getSpriteFrame());
        this.spriteFrame.setTranslateX(x);
        this.spriteFrame.setTranslateY(y);
    }
    public Prop clone(Prop prop, double x, double y){
        return new Prop(prop, x, y);
    }

}


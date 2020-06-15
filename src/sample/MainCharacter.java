package sample;

import javafx.scene.image.Image;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

import static sample.Main.HEIGHT;
import static sample.Main.WIDTH;

public class MainCharacter extends Hero {
    protected boolean collideProp = false;
    protected static final double SPRITE_PIXELS_X = 81;
    protected static final double SPRITE_PIXELS_Y = 81;
    protected static final double rightBoundary = WIDTH - SPRITE_PIXELS_X;
    protected static final double leftBoundary = 0;
    protected static final double bottomBoundary = HEIGHT - SPRITE_PIXELS_Y;
    protected static final double topBoundary = 0;
    protected Main main;
    private boolean animator = false;
    private byte framecounter = 0;
    private final byte runningspeed = 6;

    public MainCharacter(Main main, String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        this.main = main;
    }

    @Override
    public void update() {
        collideProp = false;
        setXYLocation();
        setBoundaries();
        setImageState();
        moveMain(iX, iY);
        checkCollision();
        if (collideProp){
            setXYLocationIfCollide();
        }
    }

    @Override
    public boolean collide(Actor object) {
        boolean collisionDetect = false;

        if(main.main.spriteFrame.getBoundsInParent().intersects(object.getSpriteFrame().getBoundsInParent())) {
            Shape intersection = SVGPath.intersect(main.main.getSpriteBound(), object.getSpriteBound());
            if(intersection.getBoundsInLocal().getWidth() != -1) {
                collisionDetect = true;
            }
        }

        return collisionDetect;
    }

    private void setXYLocation() {
        if(main.isRight()) { iX += vX * 2; }
        if(main.isLeft()) { iX -= vX * 2; }
        if(main.isDown()) { iY += vY * 2; }
        if(main.isUp()) { iY -= vY * 2; }
    }

    private void setXYLocationIfCollide() {
        if(main.isRight()) { iX -= vX * 2; }
        if(main.isLeft()) { iX += vX * 2; }
        if(main.isDown()) { iY -= vY * 2; }
        if(main.isUp()) { iY += vY * 2; }
    }

    private void setImageState() {
        if (!main.isDown() &&
                !main.isUp() &&
                !main.isLeft() &&
                !main.isRight()) {
            spriteFrame.setImage(imageStates.get(0));
            animator = false;
            framecounter = 0;
        }
        if (main.isRight()) {
            spriteFrame.setScaleX(1);
            this.setIsFlipH(false);
            if (!main.isUp() && !main.isDown()) {
                if (animator) {
                    spriteFrame.setImage(imageStates.get(2));
                } else {
                    spriteFrame.setImage(imageStates.get(1));
                }
                if (framecounter >= runningspeed) {
                    animator = !animator;
                    framecounter = 0;
                } else {
                    framecounter += 1;
                }
            }
        }
        if (main.isLeft()) {
            spriteFrame.setScaleX(-1);
            this.setIsFlipH(true);
            if (!main.isUp() && !main.isDown()) {
                if (animator) {
                    spriteFrame.setImage(imageStates.get(2));
                } else {
                    spriteFrame.setImage(imageStates.get(1));
                }
                if (framecounter >= runningspeed) {
                    animator = !animator;
                    framecounter = 0;
                } else {
                    framecounter += 1;
                }
            }
        }
        if (main.isUp()) {
            spriteFrame.setScaleX(1);
            //this.setIsFlipH(false);
            if (!main.isRight() && !main.isLeft()) {
                if (animator) {
                    spriteFrame.setImage(imageStates.get(3));
                } else {
                    spriteFrame.setImage(imageStates.get(4));
                }
                if (framecounter >= runningspeed) {
                    animator = !animator;
                    framecounter = 0;
                } else {
                    framecounter += 1;
                }
            }
        }
        if (main.isDown()) {
            spriteFrame.setScaleX(1);
            //this.setIsFlipH(false);
            if (!main.isRight() && !main.isLeft()) {
                if (animator) {
                    spriteFrame.setImage(imageStates.get(5));
                } else {
                    spriteFrame.setImage(imageStates.get(6));
                }
                if (framecounter >= runningspeed) {
                    animator = !animator;
                    framecounter = 0;
                } else {
                    framecounter += 1;
                }
            }
        }
        if (main.isDown()) { spriteFrame.setImage(imageStates.get(6)); }
        if (main.isUp()) { spriteFrame.setImage(imageStates.get(4)); }
        if (main.iswKey()) { spriteFrame.setImage(imageStates.get(5)); }
        if (main.issKey()) { spriteFrame.setImage(imageStates.get(8)); }
    }

    private void moveMain(double x, double y) {
        spriteFrame.setTranslateX(x);
        spriteFrame.setTranslateY(y);
    }

    private void setBoundaries() {
        if (iX > rightBoundary) { iX = rightBoundary; }
        if (iX < leftBoundary) { iX = leftBoundary; }
        if (iY > bottomBoundary) { iY = bottomBoundary; }
        if (iY < topBoundary) { iY = topBoundary; }
    }

    public void checkCollision() {
        for(int i=0; i<main.castDirector.getCurrentCast().size(); i++) {
            Actor object = main.castDirector.getCurrentCast().get(i);

            if(collide(object)) {
                if (object instanceof Prop) {
                    collideProp = true;
                }
                if (object instanceof Enemy || object instanceof Projectile){
                    lifeSpan-= 100;
                }

               /* main.castDirector.addToRemovedActors(object);
                main.root.getChildren().remove(object.getSpriteFrame());
                main.castDirector.resetRemovedActors();*/
                //scoringEngine(object);
            }
        }
    }

  /*  private void scoringEngine(Actor object) {
        if(object instanceof Prop) {
            main.gameScore -= 1;
            main.playiSound0();
        } else if(object instanceof PropV) {
            main.gameScore -= 2;
            main.playiSound1();
        } else if(object instanceof PropH) {
            main.gameScore -= 1;
            main.playiSound2();
        } else if(object instanceof PropB) {
            main.gameScore -= 2;
            main.playiSound3();
        } else if(object instanceof Treasure) {
            main.gameScore += 5;
            main.playiSound4();
        } else if(object.equals(main.iBullet)) {
            main.gameScore -= 5;
            main.playiSound5();
        } else if(object.equals(main.iCheese)) {
            main.gameScore += 5;
            main.playiSound0();
        } else if(object.equals(main.iBeagle)) {
            main.gameScore += 10;
            main.playiSound0();
        }
        main.scoreText.setText(String.valueOf(main.gameScore));
    }*/
}

package sample;

import java.util.Random;
import javafx.scene.image.Image;

public class Enemy extends Actor{

    protected static final Random randomNum = new Random();
    int attackCounter = 0;
    int attackFrequency = 250;
    boolean takeSides = false;
    int attackBoundary = 300;
    boolean onScreen = false;
    boolean callAttack = false;
    boolean shootBullet = false;
    int pauseCounter = 0;
    boolean launchIt = false;
    boolean bulletType = false;
    int spriteMoveR, spriteMoveL, destination;
    int randomLocation, bulletRange, bulletOffset;
    double randomOffset, bulletGravity = 0.2, cheeseGravity = 0.1;
    Main main;
    int iBagelLocation;

    public Enemy(Main main, String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        main = main;
        spriteFrame.setTranslateX(xLocation);
        spriteFrame.setTranslateY(yLocation);
       /* isAlive = true;
        isBonus = true;
        hasValu = true;*/
    }

    @Override
    public void update() {
        if(!callAttack) {
            if(attackCounter >= attackFrequency) {
                attackCounter = 0;
                spriteMoveR = 700;
                spriteMoveL = -70;
                randomLocation = randomNum.nextInt(attackBoundary);
                iBagelLocation = (int) main.main.getiY();
                bulletType = randomNum.nextBoolean();
                if(bulletType) {
                    spriteFrame.setTranslateY(randomLocation);
                    randomOffset = randomLocation + 5;
                } else {
                    spriteFrame.setTranslateY(iBagelLocation);
                    randomOffset = iBagelLocation + 5;
                }
                takeSides = randomNum.nextBoolean();
                callAttack = true;
            } else {
                attackCounter++;
            }
        } else {
            initiateAttack();
        }
        if(shootBullet) {
            shootProjectile();
            if (pauseCounter >= 60) {
                launchIt = true;
                pauseCounter = 0;
            } else {
                pauseCounter++;
            }
        }
    }

    private void initiateAttack() {
        if(!takeSides) {
            spriteFrame.setScaleX(1);
            //setIsFlipH(false);
            if(!onScreen) {
                destination = 500;
                if(spriteMoveR >= destination) {
                    spriteMoveR -= 2;
                    spriteFrame.setTranslateX(spriteMoveR);
                } else {
                    bulletOffset = 480;
                    shootBullet = true;
                    onScreen = true;
                }
            }
            if(onScreen && launchIt) {
                destination = 700;
                if(spriteMoveR <= destination) {
                    spriteMoveR += 1;
                    spriteFrame.setTranslateX(spriteMoveR);
                } else {
                    onScreen = false;
                    callAttack = false;
                    launchIt = false;
                    loadBullet();
                    loadCheese();
                    loadEnemy();
                    attackFrequency = 60 + randomNum.nextInt(480);
                }
            }
        }
        if(takeSides) {
            spriteFrame.setScaleX(-1);
            //setIsFlipH(true);
            if(!onScreen) {
                destination = 100;
                if(spriteMoveL <= destination) {
                    spriteMoveL += 2;
                    spriteFrame.setTranslateX(spriteMoveL);
                } else {
                    bulletOffset = 120;
                    shootBullet = true;
                    onScreen = true;
                }
            }
            if(onScreen && launchIt) {
                destination = -70;
                if(spriteMoveL >= destination) {
                    spriteMoveL -= 1;
                    spriteFrame.setTranslateX(spriteMoveL);
                } else {
                    onScreen = false;
                    callAttack = false;
                    launchIt = false;
                    loadBullet();
                    loadCheese();
                    loadEnemy();
                    attackFrequency = 60 + randomNum.nextInt(480);
                }
            }
        }
    }

    private void shootProjectile() {
        if(!bulletType && !takeSides) {
            main.iBullet.spriteFrame.setTranslateY(randomOffset);
            main.iBullet.spriteFrame.setScaleX(-0.5);
            main.iBullet.spriteFrame.setScaleY(0.5);
            bulletRange = -50;
            if(bulletOffset >= bulletRange) {
                bulletOffset -= 6;
                main.iBullet.spriteFrame.setTranslateX(bulletOffset);
                randomOffset = randomOffset + bulletGravity;
            } else {
                shootBullet = false;
            }
        }
        if(!bulletType && takeSides) {
            main.iBullet.spriteFrame.setTranslateY(randomOffset);
            main.iBullet.spriteFrame.setScaleX(0.5);
            main.iBullet.spriteFrame.setScaleY(0.5);
            bulletRange = 624;
            if(bulletOffset <= bulletRange) {
                bulletOffset += 6;
                main.iBullet.spriteFrame.setTranslateX(bulletOffset);
                randomOffset = randomOffset + bulletGravity;
            } else {
                shootBullet = false;
            }
        }
        if(bulletType && !takeSides) {
            main.iCheese.spriteFrame.setTranslateY(randomOffset);
            main.iCheese.spriteFrame.setScaleX(-0.5);
            main.iCheese.spriteFrame.setScaleY(0.5);
            bulletRange = -50;
            if(bulletOffset >= bulletRange) {
                bulletOffset -= 4;
                main.iCheese.spriteFrame.setTranslateX(bulletOffset);
                randomOffset = randomOffset + cheeseGravity;
            } else {
                shootBullet = false;
            }
        }
        if(bulletType && takeSides) {
            main.iCheese.spriteFrame.setTranslateY(randomOffset);
            main.iCheese.spriteFrame.setScaleX(0.5);
            main.iCheese.spriteFrame.setScaleY(0.5);
            bulletRange = 624;
            if(bulletOffset <= bulletRange) {
                bulletOffset += 4;
                main.iCheese.spriteFrame.setTranslateX(bulletOffset);
                randomOffset = randomOffset + cheeseGravity;
            } else {
                shootBullet = false;
            }
        }
    }

    private void loadBullet() {
        for (int i=0; i<main.castDirector.getCurrentCast().size(); i++) {
            Actor object = main.castDirector.getCurrentCast().get(i);
            if(object.equals(main.iBullet)) {
                return;
            }
        }
        main.castDirector.addCurrentCast(main.iBullet);
        main.root.getChildren().add(main.iBullet.spriteFrame);
    }

    private void loadCheese() {
        for (int i=0; i<main.castDirector.getCurrentCast().size(); i++) {
            Actor object = main.castDirector.getCurrentCast().get(i);
            if(object.equals(main.iCheese)) {
                return;
            }
        }
        main.castDirector.addCurrentCast(main.iCheese);
        main.root.getChildren().add(main.iCheese.spriteFrame);
    }

    private void loadEnemy() {
        for (int i=0; i<main.castDirector.getCurrentCast().size(); i++) {
            Actor object = main.castDirector.getCurrentCast().get(i);
            if(object.equals(main.iBeagle)) {
                return;
            }
        }
        main.castDirector.addCurrentCast(main.iBeagle);
        main.root.getChildren().add(main.iBeagle.spriteFrame);
    }
}

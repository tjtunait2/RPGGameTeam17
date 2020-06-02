package sample;

public class GamePiece {

    private static final String FLAVOR_OF_BAGEL = "Pumpernickel";
    private static final String SIZE_OF_BAGEL = "Mini Bagel";

    public int mainX = 0;
    public int mainY = 0;
    public String bagelOrientation = "side";
    public int lifeIndex = 1000;
    public int hitsIndex = 0;
    public String directionFacing = "E";
    public String movementType = "idle";
    public boolean currentlyMoving = false;

    GamePiece() {
        mainX = 0;
        mainY = 0;
        bagelOrientation = "side";
        lifeIndex = 1000;
        hitsIndex = 0;
        directionFacing = "E";
        movementType = "idle";
        currentlyMoving = false;
    }

    GamePiece(int x, int y, String orientation, int lifespan, String direction,
              String movement) {
        mainX = x;
        mainY = y;
        bagelOrientation = orientation;
        lifeIndex = lifespan;
        hitsIndex = 0;
        directionFacing = direction;
        movementType = movement;
        currentlyMoving = false;
    }

    public void movemain(int x, int y) {
        currentlyMoving = true;
        mainX = x;
        mainY = y;
    }

    public String getmainOrientation() {
        return bagelOrientation;
    }

    public void setmainOrientation(String orientation) {
        bagelOrientation = orientation;
    }

    public int getmainLifeIndex() {
        return lifeIndex;
    }

    public void setmainLifeIndex(int lifespan) {
        lifeIndex = lifespan;
    }

    public String getmainDirection() {
        return directionFacing;
    }

    public void setmainDirection(String direction) {
        directionFacing = direction;
    }

    public int getmainHitsIndex() {
        return hitsIndex;
    }

    public void setmainHitsIndex(int damage) {
        hitsIndex = damage;
    }

    public String getmainMovementType() {
        return movementType;
    }

    public void setmainMovementType(String movement) {
        movementType = movement;
    }
}


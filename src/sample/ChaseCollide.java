package sample;

public class ChaseCollide {
    private double minX, maxX, minY, maxY;

    public ChaseCollide(double minX, double maxX, double minY, double maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public boolean IsInRange(ChaseCollide other){
        return maxX >= other.minX && minX<= other.maxX
                && maxY>= other.minY && minY <= other.maxY;

    }
}
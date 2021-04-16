package model.user;
public class LifePoint {
    private int lifePoint;

    public LifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public void increaseLifePoint(int num){
        this.lifePoint+=num;
    }

    public void decreaseLifePoint(int num){
        this.lifePoint-=num;
    }
}
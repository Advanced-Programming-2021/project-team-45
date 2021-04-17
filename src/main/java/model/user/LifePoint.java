package model.user;
public class Lifepoint {
    private int lifepoint;

    public Lifepoint(int lifepoint) {
        this.lifepoint = lifepoint;
    }

    public int getLifepoint() {
        return lifepoint;
    }

    public void setLifepoint(int lifepoint) {
        this.lifepoint = lifePoint;
    }

    public void increaseLifepoint(int num){
        this.lifepoint+=num;
    }

    public void decreaseLifepoint(int num){
        this.lifepoint-=num;
    }
}
package model.user;

public class Lifepoint {

    private int lifepoint = 8000;

    public void startNewGame() {
        lifepoint = 8000;
    }

    public int getLifepoint() {
        return lifepoint;
    }

    public void setLifepoint(int lifepoint) {
        this.lifepoint = lifepoint;
    }

    public void increaseLifepoint(int num) {
        this.lifepoint += num;
    }

    public void decreaseLifepoint(int num) {
        this.lifepoint -= num;
    }
}
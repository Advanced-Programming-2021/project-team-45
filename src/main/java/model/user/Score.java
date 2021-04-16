package model.user;

public class Score {

    private int score;

    public Score(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(int num) {
        this.score += num;
    }

    public void decreaseScore(int num) {
        this.score -= num;
    }

}

package game.entities.enemies.rewards;

import game.main.Position;

public class Shield extends Reward {

    private int effectDuration;
    private int timeRemainingOnBoard;

    public Shield(Position pos, int effectDuration, int timeRemainingOnBoard) {
        super(pos, 0);
        this.effectDuration = effectDuration;
        this.timeRemainingOnBoard = timeRemainingOnBoard;
    }

    public bool isExpired() {
        return false;
    }

    public void updateOnBoard() {}

    public void collect() {}
}
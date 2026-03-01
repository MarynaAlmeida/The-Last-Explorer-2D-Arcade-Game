package game.entities.enemies.rewards;

import game.main.Position;

public class Coin extends Reward {
    public Coin(Position pos, int value) {
        super(pos, value);
    }

    public void collect() {
        this.collected = true;
    };
}
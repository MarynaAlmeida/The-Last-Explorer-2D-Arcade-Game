package main.java.game.entities.enemies;

public abstract class StaticEnemy extends Entity {
    protected int damageValue;

    public StaticEnemy(Position position, int damageValue) {
    }

    public int getDamageValue() {

    }

    @Override
    public void onCollision(Explorer explorer) {

    }
}
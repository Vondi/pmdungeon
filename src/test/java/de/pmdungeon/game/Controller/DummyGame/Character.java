package de.pmdungeon.game.Controller.DummyGame;


import de.pmdungeon.dungeonCreator.DungeonWorld;
import de.pmdungeon.graphic.Animation;
import de.pmdungeon.interfaces.IAnimatable;
import de.pmdungeon.interfaces.IEntity;
import de.pmdungeon.tools.Point;

/**
 * Base class for all characters
 */
public abstract class Character implements IEntity, IAnimatable {

    /**
     * position in level
     */
    protected Point position;
    /**
     * current level
     */
    protected DungeonWorld level;
    /**
     * how fast to move
     */
    protected float movementSpeed;
    /**
     * the currently active animation
     */
    protected Animation currentAnimation;
    /**
     * max HP
     */
    protected int maxLifepoints;
    /**
     * current HP
     */
    protected int currentLifepoints;
    /**
     * how much dmg this char do
     */
    protected int dmg;

    /**
     *
     * @param movementspeed
     * @param maxLifepoints
     * @param dmg
     */
    public Character(float movementspeed, int maxLifepoints, int dmg) {
        this.movementSpeed = movementspeed;
        this.maxLifepoints = maxLifepoints;
        this.currentLifepoints = maxLifepoints;
        this.dmg = dmg;
    }

    /**
     * calculates new position
     * @return new position
     */
    protected abstract Point move();

    /**
     * sets the level
     * @param level
     */
    public void updateLevel(DungeonWorld level) {
        this.level = level;
        this.updatePosition();
    }

    /**
     * find a random position in level and "spawn/port" there
     */
    public void updatePosition() {
        this.position = level.getRandomPointInDungeon();
    }

    public int getCurrentLifepoints() {
        return this.currentLifepoints;
    }

    public int getDmg() {
        return this.dmg;
    }

    /**
     * Thats hurt...
     * @param dmg
     */
    public void takeDmg(int dmg) {
        this.currentLifepoints -= dmg;

    }

    /**
     * Feels good
     * @param healValue
     */
    public void heal(int healValue) {
        this.currentLifepoints = Math.min(this.maxLifepoints, this.currentLifepoints + healValue);
    }

    @Override
    public void update() {
        this.position = this.move();
        this.draw();
    }

    @Override
    public Animation getActiveAnimation() {
        return this.currentAnimation;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public boolean deleteable() {
        return this.currentLifepoints <= 0;
    }
}

package de.fhbielefeld.pmdungeon.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;

public abstract class PlayerCharacter extends Character {

    protected PlayerCharacter(SpriteBatch batch, Dungeon dungeon) {
        super(batch, dungeon);
    }

    public void handleInput(Input input) {
        float targetX = positionX;
        float targetY = positionY;
        idle = true;
        if (input.isKeyPressed(Input.Keys.W)) {
            targetY += getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
        }
        if (input.isKeyPressed(Input.Keys.A)) {
            targetX -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
            facingLeft = true;
        }
        if (input.isKeyPressed(Input.Keys.S)) {
            targetY -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
        }
        if (input.isKeyPressed(Input.Keys.D)) {
            targetX += getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
            facingLeft = false;
        }
        move(targetX, targetY);
    }
}
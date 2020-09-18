package de.fhbielefeld.pmdungeon.game.interactable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.game.characters.Character;
import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.Coordinate;

public interface Interactable {
    void update();

    void render(SpriteBatch batch);

    void interact(Character character);

    Coordinate getCoordinate();
}
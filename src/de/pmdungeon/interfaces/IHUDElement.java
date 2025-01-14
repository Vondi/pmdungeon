package de.pmdungeon.interfaces;

import com.badlogic.gdx.graphics.Texture;
import de.pmdungeon.tools.Point;


/**
 * Should be implement by all hud objects
 */
public interface IHUDElement {

    /**
     * The position of hud elements are based on virtual coordinates.
     *
     * @return
     */
    Point getPosition();

    Texture getTexture();

    default float getWidth() {
        return 0.5f;
    }

    default float getHeight() {
        return getTexture().getHeight() / 2;
    }
}
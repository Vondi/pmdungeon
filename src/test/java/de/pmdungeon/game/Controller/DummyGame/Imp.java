package de.pmdungeon.game.Controller.DummyGame;

import com.badlogic.gdx.graphics.Texture;
import de.pmdungeon.graphic.Animation;
import de.pmdungeon.tools.Point;

import java.util.ArrayList;
import java.util.List;

public class Imp extends Monster {

    public Imp(float movementspeed, int maxLifepoints, int dmg) {
        super(movementspeed, maxLifepoints, dmg);
        this.setupAnimation();

    }

    @Override
    protected Point move() {
        Point newPosition = new Point(this.position);

        //move random on x
        if (Math.random() > 0.5) newPosition.x += movementSpeed;
        else newPosition.x -= movementSpeed;

        // move random on y
        if (Math.random() > 0.5) newPosition.y += movementSpeed;
        else newPosition.y -= movementSpeed;

        if (level.isTileAccessible(newPosition)) return newPosition;
        else return this.position;
    }

    private void setupAnimation() {
        Texture i1 = new Texture("./assets/textures/characters/demons/big_demon/big_demon_idle_anim_f0.png");
        Texture i2 = new Texture("./assets/textures/characters/demons/big_demon/big_demon_idle_anim_f1.png");
        Texture i3 = new Texture("./assets/textures/characters/demons/big_demon/big_demon_idle_anim_f2.png");
        Texture i4 = new Texture("./assets/textures/characters/demons/big_demon/big_demon_idle_anim_f3.png");
        List<Texture> textures = new ArrayList<>();
        textures.add(i1);
        textures.add(i2);
        textures.add(i3);
        textures.add(i4);
        this.currentAnimation = new Animation(textures, 4);
    }


}

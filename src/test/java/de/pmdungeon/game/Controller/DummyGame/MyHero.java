package de.pmdungeon.game.Controller.DummyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import de.pmdungeon.graphic.Animation;
import de.pmdungeon.tools.Point;

import java.util.ArrayList;
import java.util.List;

public class MyHero extends Character {

    private Animation idleRight;
    private Animation idleLeft;
    private Animation runRight;
    private Animation runLeft;
    private Animation nextIdleAnimation;
    private Animation nextRunAnimation;

    public MyHero(float movementspeed, int maxLifepoints, int dmg) {
        super(movementspeed, maxLifepoints, dmg);
        this.setupAnimations();
        this.currentAnimation = this.idleRight;
        this.nextIdleAnimation = this.currentAnimation;
        this.nextRunAnimation = this.runRight;
    }

    private void setupAnimations() {

        //idle right animation
        Texture i1 = new Texture("./assets/textures/characters/hero/lizard_m_idle_anim_f0.png");
        Texture i2 = new Texture("./assets/textures/characters/hero/lizard_m_idle_anim_f1.png");
        Texture i3 = new Texture("./assets/textures/characters/hero/lizard_m_idle_anim_f2.png");
        Texture i4 = new Texture("./assets/textures/characters/hero/lizard_m_idle_anim_f3.png");
        List<Texture> idleRightTextures = new ArrayList<>();
        idleRightTextures.add(i1);
        idleRightTextures.add(i2);
        idleRightTextures.add(i3);
        idleRightTextures.add(i4);
        this.idleRight = new Animation(idleRightTextures, 8);

        //idle left animation
        Texture il1 = new Texture("./assets/textures/characters/hero/il0.png");
        Texture il2 = new Texture("./assets/textures/characters/hero/il1.png");
        Texture il3 = new Texture("./assets/textures/characters/hero/il2.png");
        Texture il4 = new Texture("./assets/textures/characters/hero/il3.png");
        List<Texture> idleLeftTextures = new ArrayList<>();
        idleLeftTextures.add(il1);
        idleLeftTextures.add(il2);
        idleLeftTextures.add(il3);
        idleLeftTextures.add(il4);
        this.idleLeft = new Animation(idleLeftTextures, 8);


        //run left animation
        Texture rr1 = new Texture("./assets/textures/characters/hero/lizard_m_run_anim_f0.png");
        Texture rr2 = new Texture("./assets/textures/characters/hero/lizard_m_run_anim_f1.png");
        Texture rr3 = new Texture("./assets/textures/characters/hero/lizard_m_run_anim_f2.png");
        Texture rr4 = new Texture("./assets/textures/characters/hero/lizard_m_run_anim_f3.png");
        List<Texture> runRightTextures = new ArrayList<>();
        runRightTextures.add(rr1);
        runRightTextures.add(rr2);
        runRightTextures.add(rr3);
        runRightTextures.add(rr4);
        this.runRight = new Animation(runRightTextures, 2);

        //run left animation
        Texture rl1 = new Texture("./assets/textures/characters/hero/lizard_m_runr_anim_f0.png");
        Texture rl2 = new Texture("./assets/textures/characters/hero/lizard_m_runr_anim_f1.png");
        Texture rl3 = new Texture("./assets/textures/characters/hero/lizard_m_runr_anim_f2.png");
        Texture rl4 = new Texture("./assets/textures/characters/hero/lizard_m_runr_anim_f3.png");
        List<Texture> rleftTextures = new ArrayList<>();
        rleftTextures.add(rl1);
        rleftTextures.add(rl2);
        rleftTextures.add(rl3);
        rleftTextures.add(rl4);
        this.runLeft = new Animation(rleftTextures, 2);


    }

    @Override
    public void takeDmg(int dmg) {
        this.currentLifepoints -= dmg;
        System.out.println("AUA! " + this.currentLifepoints + " HP left");
    }

    @Override
    public Point move() {

        Point newPosition = new Point(this.position);
        this.currentAnimation = nextIdleAnimation;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            newPosition.y += movementSpeed;
            this.currentAnimation = nextRunAnimation;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            newPosition.y -= movementSpeed;
            this.currentAnimation = nextRunAnimation;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            newPosition.x += movementSpeed;
            this.currentAnimation = runRight;
            this.nextIdleAnimation = idleRight;
            this.nextRunAnimation = runRight;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            newPosition.x -= movementSpeed;
            this.currentAnimation = runLeft;
            this.nextIdleAnimation = idleLeft;
            this.nextRunAnimation = runLeft;
        }

        if (level.isTileAccessible(newPosition)) return newPosition;
        else return this.position;
    }


}

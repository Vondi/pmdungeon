package de.pmdungeon.game.Controller.DummyGame;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.pmdungeon.desktop.DesktopLauncher;
import de.pmdungeon.game.Controller.MainController;
import de.pmdungeon.graphic.HUD;

import java.util.ArrayList;
import java.util.List;

public class MyMain extends MainController {
    private final int MONSTER_DMG=2;
    private final int MONSTER_HP=2;
    private final float MONSTER_SPEED=0.1f;
    private final int HERO_DMG=5;
    private final int HERO_HP=20;
    private final float HERO_SPEED=0.2f;
    private final int HEAL_AFTER_KILL=1;
    private final int MONSTER_PER_LEVEL = 10;

    private List<Monster> monsterList = new ArrayList<>();
    private MyHero hero;
    private Label heroHP;




    @Override
    protected void setup() {
        hero = new MyHero(HERO_SPEED, HERO_HP, HERO_DMG);
        entityController.addEntity(hero);
        camera.follow(hero);
        //shows the current health of the player as text
    }

    @Override
    protected void beginFrame() {
        //check if its time to fight
        checkForFight();
    }

    @Override
    protected void endFrame() {
        //check if hero stands on door
        if (levelController.checkForTrigger(hero.getPosition())) levelController.triggerNextStage();

    }

    @Override
    public void onLevelLoad() {
        hero.updateLevel(levelController.getDungeon());

        //delete old monster
        monsterList.clear();
        entityController.removeAllFrom(Monster.class);

        //place new monster
        for (int i = 0; i < MONSTER_PER_LEVEL; i++) {
            Monster monster = new Imp(MONSTER_SPEED, MONSTER_HP, MONSTER_DMG);
            monsterList.add(monster);
            entityController.addEntity(monster);
            monster.updateLevel(levelController.getDungeon());
        }
    }


    /**
     * check if the hero and a monster are on the same field, than fight to death
     */
    private void checkForFight() {
        List<Monster> deadMonster = new ArrayList<>();
        for (Monster monster : monsterList) {
            //monster and hero on same position?
            if (this.levelController.getDungeon().getTileAt((int) monster.getPosition().x, (int) monster.getPosition().y) == this.levelController.getDungeon().getTileAt((int) hero.getPosition().x, (int) hero.getPosition().y))
            {
                //FIGHT
                monster.takeDmg(hero.getDmg());
                //monster is dead?
                if (monster.getCurrentLifepoints() <= 0) {
                    deadMonster.add(monster);
                    hero.heal(HEAL_AFTER_KILL);
                }
                hero.takeDmg(monster.getDmg());

            }
        }
        //remove dead monsters
        deadMonster.forEach(m -> monsterList.remove(m));
    }
}

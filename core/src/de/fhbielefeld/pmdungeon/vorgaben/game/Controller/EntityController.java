package de.fhbielefeld.pmdungeon.vorgaben.game.Controller;

import de.fhbielefeld.pmdungeon.vorgaben.interfaces.IEntity;
import java.util.ArrayList;


/**
 * Handles every entity in the dungeon.
 */
public class EntityController {

    /**
     * Contains all the entity's this controller handles.
     */
    private final ArrayList<IEntity> dungeonEntitys;
    public EntityController() {
        this.dungeonEntitys = new ArrayList<>();
    }
    /**
     * calls the update method for every entity in the list.
     * removes entity if deletable is set true
     */
    public void update() {
        ArrayList<IEntity> toRemove = new ArrayList();
        for (IEntity obj : dungeonEntitys) {
            if (obj.deleteable()) toRemove.add(obj);
            else obj.update();
        }
        dungeonEntitys.removeAll(toRemove);
    }

    /**
     * add an entity to the list
     *
     * @param entity
     */
    public void addEntity(IEntity entity) {
        if (!dungeonEntitys.contains(entity))
            this.dungeonEntitys.add(entity);
    }
    /**
     * removes entity from the list
     *
     * @param entity
     */
    public void removeEntity(IEntity entity) {
        if (dungeonEntitys.contains(entity))
            this.dungeonEntitys.remove(entity);
    }
    /**
     * returns entity list
     */
    public ArrayList<IEntity> getList(){ return this.dungeonEntitys;}
}

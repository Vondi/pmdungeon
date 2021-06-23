package de.pmdungeon.game.Controller;

import de.pmdungeon.interfaces.IEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EntityControllerTest {

    EntityController underTest;

    class CharacterDeletabelTrue implements IEntity {
        @Override
        public void update() {}

        @Override
        public boolean deleteable() {
            return true;
        }
    }
    class CharacterDeletabelFalse implements IEntity {
        @Override
        public void update() {}

        @Override
        public boolean deleteable() {
            return false;
        }
    }
    class HeldTrue extends CharacterDeletabelTrue {}
    class HeldFalse extends CharacterDeletabelFalse {}
    class HelferTrue extends CharacterDeletabelTrue {}
    class HelferFalse extends CharacterDeletabelFalse {}
    class MonsterTrue extends CharacterDeletabelTrue {}
    class MonsterFalse extends CharacterDeletabelFalse {}

    @BeforeEach
    void setUp() {
        // given
        underTest = new EntityController();
    }

    @Test
    void updateWithEmptyList() {
        // when
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();
        assertEquals(result, expected);
    }

    @Test
    void updateWithNotEmptyListAndDeletableTrue() {
        // given
        for (int index = 0; index < 10; index++) {
            underTest.addEntity(new HeldTrue());
            underTest.addEntity(new HelferTrue());
            underTest.addEntity(new MonsterTrue());
        }

        // when
        underTest.update();
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();
        assertEquals(result, expected);
    }

    @Test
    void updateWithNotEmptyListAndDeletabelFalse() {
        // given
        IEntity Held = new HeldFalse();
        IEntity Helfer = new HelferFalse();
        IEntity Monster = new MonsterFalse();
        IEntity Held1 = new HeldFalse();
        IEntity Helfer1 = new HelferFalse();
        IEntity Monster1 = new MonsterFalse();

        underTest.addEntity(Held);
        underTest.addEntity(Helfer);
        underTest.addEntity(Monster);
        underTest.addEntity(Held1);
        underTest.addEntity(Helfer1);
        underTest.addEntity(Monster1);

        // when
        underTest.update();
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();
        expected.add(Held);
        expected.add(Helfer);
        expected.add(Monster);
        expected.add(Held1);
        expected.add(Helfer1);
        expected.add(Monster1);

        assertEquals(result, expected);
    }

    @Test
    void updateWithNotEmptyListAndDeletableMixed() {
        // given
        IEntity HeldF = new HeldFalse();
        IEntity HelferF = new HelferFalse();
        IEntity MonsterF = new MonsterFalse();
        IEntity HeldT = new HeldTrue();
        IEntity HelferT = new HelferTrue();
        IEntity MonsterT = new MonsterTrue();


        underTest.addEntity(HeldF);
        underTest.addEntity(HeldT);
        underTest.addEntity(HelferT);
        underTest.addEntity(HelferF);
        underTest.addEntity(MonsterF);
        underTest.addEntity(MonsterT);

        //when
        underTest.update();
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();
        expected.add(HeldF);
        expected.add(HelferF);
        expected.add(MonsterF);

        assertEquals(result, expected);
    }

    @Test
    void addEntityEmptyListAddEmptyList() {

    }

    @Test
    void addEntityEmptyListAddIEntityElement() {

    }

    @Test
    void addEntityNotEmptyListAddEmptyList() {

    }

    @Test
    void addEntityNotEmpltyListAddIEntityElement() {

    }

    @Test
    void removeEntity() {
    }

    @Test
    void removeAll() {
    }

    @Test
    void removeAllFrom() {
    }

    @Test
    void getList() {
    }
}

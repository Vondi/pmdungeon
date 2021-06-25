package de.pmdungeon.game.Controller;

import de.pmdungeon.interfaces.IEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EntityControllerTest {

    EntityController underTest;

    class CharacterDeletabelTrue implements IEntity {
        @Override
        public void update() {
        }

        @Override
        public boolean deleteable() {
            return true;
        }
    }

    class CharacterDeletabelFalse implements IEntity {
        @Override
        public void update() {
        }

        @Override
        public boolean deleteable() {
            return false;
        }
    }

    class HeldTrue extends CharacterDeletabelTrue {
    }

    class HeldFalse extends CharacterDeletabelFalse {
    }

    class HelferTrue extends CharacterDeletabelTrue {
    }

    class HelferFalse extends CharacterDeletabelFalse {
    }

    class MonsterTrue extends CharacterDeletabelTrue {
    }

    class MonsterFalse extends CharacterDeletabelFalse {
    }

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
        assertEquals(expected, result);
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
        assertEquals(expected, result);
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

        assertEquals(expected, result);
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

        assertEquals(expected, result);
    }

    @Test
    void addEntityEmptyListAddEmptyList() {
        // when
        underTest.addEntity(null);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();

        assertEquals(expected, result);
    }

    @Test
    void addEntityEmptyListAddIEntityElement() {
        // given
        IEntity Held = new HeldTrue();

        // when
        underTest.addEntity(Held);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();
        expected.add(Held);

        assertEquals(expected, result);
    }

    @Test
    void addEntityNotEmptyListAddEmptyList() {
        // given
        IEntity Held = new HeldTrue();
        IEntity Helfer = new HelferTrue();
        IEntity Monster = new MonsterTrue();

        underTest.addEntity(Held);
        underTest.addEntity(Helfer);
        underTest.addEntity(Monster);

        // when
        underTest.addEntity(null);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();
        expected.add(Held);
        expected.add(Helfer);
        expected.add(Monster);

        assertEquals(expected, result);
    }

    @Test
    void addEntityNotEmptyListAddIEntityElement() {
        // given
        IEntity Held = new HeldTrue();
        IEntity Helfer = new HelferTrue();
        IEntity Monster = new MonsterTrue();

        underTest.addEntity(Held);
        underTest.addEntity(Helfer);
        underTest.addEntity(Monster);

        IEntity Held1 = new HelferTrue();

        // when
        underTest.addEntity(Held1);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();
        expected.add(Held);
        expected.add(Helfer);
        expected.add(Monster);
        expected.add(Held1);

        assertEquals(expected, result);


    }

    @Test
    void addEntityNotEmptyListAddExistingElement() {
        // given
        IEntity Held = new HeldTrue();
        IEntity Helfer = new HelferTrue();
        IEntity Monster = new MonsterTrue();

        underTest.addEntity(Held);
        underTest.addEntity(Helfer);
        underTest.addEntity(Monster);

        // when
        underTest.addEntity(Held);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();
        expected.add(Held);
        expected.add(Helfer);
        expected.add(Monster);

        assertEquals(expected, result);
    }

    @Test
    void removeEntityEmptyListRemoveEmptyElement() {
        // when
        underTest.removeEntity(null);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();

        assertEquals(expected, result);
    }

    @Test
    void removeEntityEmptyListRemoveIEntityElement() {
        // given
        IEntity Held = new HelferTrue();

        // when
        underTest.removeEntity(Held);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();

        assertEquals(expected, result);
    }

    @Test
    void removeEntityNotEmptyListRemoveEmptyElement() {
            // given
            IEntity Held = new HeldTrue();
            IEntity Helfer = new HelferTrue();
            IEntity Monster = new MonsterTrue();

            underTest.addEntity(Held);
            underTest.addEntity(Helfer);
            underTest.addEntity(Monster);

            // when
            underTest.removeEntity(null);
            ArrayList<IEntity> result = underTest.getList();

            // then
            ArrayList<IEntity> expected = new ArrayList<>();
            expected.add(Held);
            expected.add(Helfer);
            expected.add(Monster);

            assertEquals(expected, result);
    }

    @Test
    void removeEntityNotEmptyListRemoveNotExistingIEntityElement() {
        // given
        IEntity Held = new HeldTrue();
        IEntity Helfer = new HelferTrue();
        IEntity Monster = new MonsterTrue();

        underTest.addEntity(Held);
        underTest.addEntity(Helfer);
        underTest.addEntity(Monster);

        // given
        IEntity HeldDoesNotExist = new HelferTrue();

        // when
        underTest.removeEntity(HeldDoesNotExist);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();
        expected.add(Held);
        expected.add(Helfer);
        expected.add(Monster);

        assertEquals(expected, result);
    }

    @Test
    void removeEntityNotEmptyListRemoveExistingIEntityElement() {
        // given
        IEntity Held = new HeldTrue();
        IEntity Helfer = new HelferTrue();
        IEntity Monster = new MonsterTrue();

        underTest.addEntity(Held);
        underTest.addEntity(Helfer);
        underTest.addEntity(Monster);

        // when
        underTest.removeEntity(Helfer);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();
        expected.add(Held);
        expected.add(Monster);

        assertEquals(expected, result);
    }

    @Test
    void removeAllEmptyList() {
        // when
        underTest.removeAll();
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();

        assertEquals(expected, result);
    }

    @Test
    void removeAllNotEmptyList() {
        // given
        IEntity Held = new HeldTrue();
        IEntity Helfer = new HelferTrue();
        IEntity Monster = new MonsterTrue();

        underTest.addEntity(Held);
        underTest.addEntity(Helfer);
        underTest.addEntity(Monster);

        // when
        underTest.removeAll();
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();

        assertEquals(expected, result);
    }

    @Test
    void removeAllFromEmptyListRemoveEmptyClass() {
        // when
        underTest.removeAllFrom(null);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();

        assertEquals(expected, result);
    }

    @Test
    void removeAllFromEmptyListRemoveNotExistingClass() {
        // when
        underTest.removeAllFrom(HelferTrue.class);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();

        assertEquals(expected, result);

    }

    @Test
    void removeAllFromNotEmptyDiffernetClassesListRemoveNotExistingClass() {
        // given
        IEntity Held = new HeldTrue();
        IEntity Helfer = new HelferTrue();
        IEntity Monster = new MonsterTrue();

        underTest.addEntity(Held);
        underTest.addEntity(Helfer);
        underTest.addEntity(Monster);

        // when
        underTest.removeAllFrom(HeldFalse.class);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();
        expected.add(Held);
        expected.add(Helfer);
        expected.add(Monster);

        assertEquals(expected, result);
    }

    @Test
    void removeAllFromNotEmptyDifferentClassesListRemoveExistingClass() {
        // given
        IEntity Held = new HeldTrue();
        IEntity Helfer = new HelferTrue();
        IEntity Monster = new MonsterTrue();

        underTest.addEntity(Held);
        underTest.addEntity(Helfer);
        underTest.addEntity(Monster);

        // when
        underTest.removeAllFrom(HelferTrue.class);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();
        expected.add(Held);
        expected.add(Monster);

        assertEquals(expected, result);
    }

    @Test
    void removeAllFromNotEmptySameClassesListRemoveNotExistingClass() {
        // given
        IEntity Helfer = new HelferTrue();
        IEntity Helfer1 = new HelferTrue();
        IEntity Helfer2 = new HelferTrue();
        IEntity Helfer3 = new HelferTrue();

        underTest.addEntity(Helfer);
        underTest.addEntity(Helfer1);
        underTest.addEntity(Helfer2);
        underTest.addEntity(Helfer3);

        // when
        underTest.removeAllFrom(HeldFalse.class);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();
        expected.add(Helfer);
        expected.add(Helfer1);
        expected.add(Helfer2);
        expected.add(Helfer3);

        assertEquals(expected, result);

    }

    @Test
    void removeAllFromNotEmptySameClassesListRemoveExistingClass() {
        // given
        IEntity Helfer = new HelferTrue();
        IEntity Helfer1 = new HelferTrue();
        IEntity Helfer2 = new HelferTrue();
        IEntity Helfer3 = new HelferTrue();

        underTest.addEntity(Helfer);
        underTest.addEntity(Helfer1);
        underTest.addEntity(Helfer2);
        underTest.addEntity(Helfer3);


        // when
        underTest.removeAllFrom(HelferTrue.class);
        ArrayList<IEntity> result = underTest.getList();

        // then
        ArrayList<IEntity> expected = new ArrayList<>();

        assertEquals(expected, result);
    }
}

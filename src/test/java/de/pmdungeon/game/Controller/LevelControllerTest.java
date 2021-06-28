package de.pmdungeon.game.Controller;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.pmdungeon.desktop.DesktopLauncher;
import de.pmdungeon.dungeonCreator.DungeonWorld;
import de.pmdungeon.dungeonCreator.dungeonconverter.DungeonConverter;
import de.pmdungeon.dungeonCreator.tiles.Tile;
import de.pmdungeon.game.Controller.DummyGame.MyMain;
import de.pmdungeon.tools.Constants;
import de.pmdungeon.tools.Point;
import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LevelControllerTest {

    private LevelController levelControllerSpy;
    private MainController mainController;
    private DungeonWorld dungeonSpy;
    private DungeonWorld dungeonOld;
    private Object[] args;

    @BeforeAll
    static void beforeAll() {
        DesktopLauncher.run(new MyMain());
    }

    @BeforeEach
    void setUp() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Initialize Game with spys to check if methods get called
        mainController = spy(new MainController());
        Method onLevelLoad = mainController.getClass().getMethod("onLevelLoad");
        levelControllerSpy = spy(new LevelController(onLevelLoad, mainController, args));
        dungeonSpy = spy(new DungeonConverter().dungeonFromJson(Constants.STARTLEVEL));
        dungeonOld = levelControllerSpy.getDungeon();
        levelControllerSpy.loadDungeon(dungeonSpy);

        // Mock behaviour of methods to bypass exceptions from libGDX
        doNothing().when(dungeonSpy).renderFloor(isA(SpriteBatch.class));
        doNothing().when(dungeonSpy).renderWalls(isA(Integer.class), isA(Integer.class), isA(SpriteBatch.class));
    }

    // loadDungeon START

    @Test
    @DisplayName("New dungeonWorld is loaded")
    void loadDungeonCheckIfNewDungeonIsLoaded() {
        assertNotEquals(dungeonOld, dungeonSpy);
    }

    @Test
    @DisplayName("New dungeon is the one which was initiated")
    void loadDungeonCheckNewDungeon() {
        assertEquals(dungeonSpy, levelControllerSpy.getDungeon());
    }

    @Test
    @DisplayName("Right Methods were called in loadDungeon")
    void loadDungeonCheckIfMethodsGotCalled() {
        verify(dungeonSpy).makeConnections();
        verify(mainController, times(1)).onLevelLoad();
    }

    // loadDungeon END

    @Test
    @DisplayName("Next stage is triggered and draw is called")
    void updateWithNextLevelTriggeredTrue() {
        levelControllerSpy.triggerNextStage();
        doNothing().when(levelControllerSpy).draw();
        levelControllerSpy.update();
        assertNotEquals(dungeonSpy, levelControllerSpy.getDungeon());
        verify(levelControllerSpy, times(1)).draw();
    }

    @Test
    @DisplayName("Next stage is not triggered and draw is called")
    void updateWithNextLevelTriggeredFalse() {
        doNothing().when(levelControllerSpy).draw();
        levelControllerSpy.update();
        assertEquals(dungeonSpy, levelControllerSpy.getDungeon());
        verify(levelControllerSpy, times(1)).draw();
    }

    @Test
    @DisplayName("Trigger tile with tile that is a trigger")
    void checkForTriggerTrue() {
        Point p = new Point(5.0f, 5.0f);
        Tile tile = new Tile(Tile.Type.WALL,5,5);
        when(dungeonSpy.getNextLevelTrigger()).thenReturn(tile);
        assertTrue(levelControllerSpy.checkForTrigger(p));
    }

    @Test
    @DisplayName("Trigger tile with tile that is a trigger")
    void checkForTriggerFalse() {
        Point p = new Point(5.0f, 5.0f);
        Tile tile = new Tile(Tile.Type.WALL,6,6);
        when(dungeonSpy.getNextLevelTrigger()).thenReturn(tile);
        assertFalse(levelControllerSpy.checkForTrigger(p));
    }

    @Test
    @DisplayName("renderFloor() and renderWalls() get called")
    void draw() {
        levelControllerSpy.draw();
        verify(dungeonSpy).renderFloor(isA(SpriteBatch.class));
        verify(dungeonSpy).renderWalls(isA(Integer.class), isA(Integer.class), isA(SpriteBatch.class));
    }
}

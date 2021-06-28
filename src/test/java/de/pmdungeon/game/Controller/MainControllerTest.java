package de.pmdungeon.game.Controller;

import de.pmdungeon.desktop.DesktopLauncher;
import de.pmdungeon.dungeonCreator.DungeonWorld;
import de.pmdungeon.dungeonCreator.dungeonconverter.DungeonConverter;
import de.pmdungeon.game.Controller.DummyGame.MyMain;
import de.pmdungeon.tools.Constants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class MainControllerTest {

    private MainController mainControllerSpy;
    private DungeonWorld dungeonSpy;
    private LevelController levelControllerSpy;
    private Object[] args;
    private MyMain myMain;

    @BeforeAll
    static void beforeAll() {
        DesktopLauncher.run(new MyMain());
    }

    @BeforeEach
    void setUp() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        mainControllerSpy = new MainController();
        Method onLevelLoad = mainControllerSpy.getClass().getMethod("onLevelLoad");
        levelControllerSpy = spy(new LevelController(onLevelLoad, mainControllerSpy, args));
        dungeonSpy = spy(new DungeonConverter().dungeonFromJson(Constants.STARTLEVEL));
        levelControllerSpy.loadDungeon(dungeonSpy);
    }

    @Test
    @Disabled
    void render() {
        // The parameter delta in render is not used in the method so
        // the number has no relevance
        mainControllerSpy.render(1);

    }
}

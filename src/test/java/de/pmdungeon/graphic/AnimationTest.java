package de.pmdungeon.graphic;

import com.badlogic.gdx.graphics.Texture;
import de.pmdungeon.desktop.DesktopLauncher;
import de.pmdungeon.game.Controller.DummyGame.MyMain;
import de.pmdungeon.tools.Constants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimationTest {

    private Animation animationUnderTest;
    private List<Texture> textureList = new ArrayList<>();
    private Texture knight1;
    private Texture knight2;
    private Texture knight3;
    private Texture knight4;


    @BeforeAll
    static void beforeAll() {
        DesktopLauncher.run(new MyMain());
    }

    @BeforeEach
    void setUp() {
        textureList = new ArrayList<>();
        knight1 = new Texture("assets/character/knight/knight_m_idle_anim_f0.png");
        knight2 = new Texture("assets/character/knight/knight_m_idle_anim_f1.png");
        knight3 = new Texture("assets/character/knight/knight_m_idle_anim_f2.png");
        knight4 = new Texture("assets/character/knight/knight_m_idle_anim_f3.png");

        textureList.add(knight1);
        textureList.add(knight2);
        textureList.add(knight3);
        textureList.add(knight4);
        animationUnderTest = new Animation(textureList, Constants.FRAMERATE);
    }

    @Test
    @DisplayName("Initilize animation with max integer frametime value and filled texture list")
    void AnimationInitilizationFrameTimeMaxInt() {
        Animation animation = new Animation(textureList, Integer.MAX_VALUE);
        assertNotNull(animation);
    }

    @Test
    @DisplayName("Initilize animation with 30 frametime and filled texture list")
    void AnimationInitilizationFrameTime30() {
       Animation animation = new Animation(textureList, 30);
       assertNotNull(animation);
    }

    @Test
    @DisplayName("Initilize animation with 0 frametime and filled texture list")
    void AnimationInitilizationFrameTime0() {
        Animation animation = new Animation(textureList, 0);
        assertNotNull(animation);
    }

    @Test
    @DisplayName("Initilize animation with -1 frametime and filled texture list")
    void AnimationInitilizationFrameTimeMinus1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Animation animation = new Animation(textureList, -1);
        });
    }

    @Test
    @DisplayName("Initilize animation with -30 frametime and filled texture list")
    void AnimationInitilizationFrameTimeMinus30() {
        assertThrows(IllegalArgumentException.class, () -> {
            Animation animation = new Animation(textureList, -30);
        });
    }

    @Test
    @DisplayName("Initilize animation with min integer frametime and filled texture list")
    void AnimationInitilizationFrameTimeMinInt() {
        assertThrows(IllegalArgumentException.class, () -> {
            Animation animation = new Animation(textureList, Integer.MIN_VALUE);
        });
    }


    @Test
    @DisplayName("Initilize animation with 30 frametime and empty list")
    void AnimationInitilizationWithEmptyList() {
        List<Texture> list = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            Animation animation = new Animation(list, Constants.FRAMERATE);
        });
    }

    @Test
    @DisplayName("Check if nextAnimationTexture returns first texture")
    void getNextAnimationTextureFirstTexture() {
        assertEquals(knight1, animationUnderTest.getNextAnimationTexture());
    }

    @Test
    @DisplayName("Check if nextAnimationTexture returns second texture after frametime")
    void getNextAnimationTextureSecondTexture() {
        for(int i=0; i <= Constants.FRAMERATE; i++) {
            animationUnderTest.getNextAnimationTexture();
        }
        assertEquals(knight2, animationUnderTest.getNextAnimationTexture());
    }

    @Test
    @DisplayName("Check if nextAnimationTexture returns third texture after frametime")
    void getNextAnimationTextureThirdTexture() {
        for(int i=0; i <= (Constants.FRAMERATE*2)+1; i++) {
            animationUnderTest.getNextAnimationTexture();
        }
        assertEquals(knight3, animationUnderTest.getNextAnimationTexture());
    }

    @Test
    @DisplayName("Check if nextAnimationTexture returns fourth texture after frametime")
    void getNextAnimationTextureFourthTexture() {
        for(int i=0; i <= (Constants.FRAMERATE*3)+2; i++) {
            animationUnderTest.getNextAnimationTexture();
        }
        assertEquals(knight4, animationUnderTest.getNextAnimationTexture());
    }

    @Test
    @DisplayName("Check if nextAnimationTexture returns fourth texture after frametime")
    void getNextAnimationTextureFirstTextureAgain() {
        for(int i=0; i <= (Constants.FRAMERATE*4)+3; i++) {
            animationUnderTest.getNextAnimationTexture();
        }
        assertEquals(knight1, animationUnderTest.getNextAnimationTexture());
    }

}

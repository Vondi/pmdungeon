package de.pmdungeon.dungeonCreator.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import de.pmdungeon.dungeonCreator.DungeonTextures;
import de.pmdungeon.dungeonCreator.dungeonconverter.Coordinate;
import de.pmdungeon.dungeonCreator.tiles.Tile;

public class TCornerSouthWall extends WallPattern {

    public TCornerSouthWall(ObjectMap<DungeonTextures, Texture> textureMap) {
        super(textureMap);

        this.patternList.add(new Tile.Type[][]{
                {A, A, A},
                {W, W, W},
                {A, W, A}
        });
    }

    @Override
    public void render(SpriteBatch batch, Coordinate position) {
        batch.draw(textureMap.get(DungeonTextures.WALL_MID), position.getX(), position.getY(), 1, 1);
        batch.draw(textureMap.get(DungeonTextures.WALL_SIDE_MID_RIGHT), position.getX(), position.getY(), 1, 1);
        batch.draw(textureMap.get(DungeonTextures.WALL_TOP_MID), position.getX(), position.getY() + 1f, 1, 1);
    }
}

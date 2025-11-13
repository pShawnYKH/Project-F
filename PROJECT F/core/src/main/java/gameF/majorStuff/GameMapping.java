package gameF.majorStuff;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import gameF.majorStuff.minorStuff.Tile;

public class GameMapping {
    private Tile[][] map;
    private int width, height;
    private GameGraphics graphics;

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;

    public GameMapping(int width, int height, GameGraphics graphics) {
        this.width = width;
        this.height = height;
        this.graphics = graphics;
        map = new Tile[width][height];

        //fill map with dirt tiles for now
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int variant = MathUtils.random(1, 11);
                map[x][y] = new Tile("tiles/dirt tiles/dirt" + variant + ".png", true);
            }
        }

        tiledMap = new TmxMapLoader().load("TiledEditor/world/baseworld.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, graphics.pixelSize / 16f);
    }

    public void draw(SpriteBatch batch) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                batch.draw(
                    map[x][y].getTexture(),
                    x * graphics.pixelSize,
                    y * graphics.pixelSize,
                    graphics.pixelSize, //width
                    graphics.pixelSize //height
                );
            }
        }
    }

    public void drawTiledMap() {
        graphics.camera.update();
        mapRenderer.setView(graphics.camera);
        mapRenderer.render();
    }

    public void dispose() {
        if (tiledMap != null) tiledMap.dispose();
        if (mapRenderer != null) mapRenderer.dispose();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y].dispose();
            }
        }
    }
}

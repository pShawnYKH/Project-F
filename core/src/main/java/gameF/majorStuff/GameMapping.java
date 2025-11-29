package gameF.majorStuff;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import gameF.majorStuff.minorStuff.Tile;

public class GameMapping {
    private final Tile[][] map;
    private final int width, height;
    private GameGraphics graphics;
    private final TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;

    public GameMapping(int width, int height, GameGraphics graphics) {
        this.width = width;
        this.height = height;
        this.graphics = graphics;
        map = new Tile[width][height];

        //fill map with dirt tiles randomly for the bottom most layer each render
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int variant = MathUtils.random(1, 11);
                map[x][y] = new Tile("tiles/dirt tiles/dirt" + variant + ".png", true);
            }
        }
        tiledMap = new TmxMapLoader().load("BaseWorld.tmx"); //the tiled map editor BROKEN PATH RN FIX IT AFTER MAKING THE TILE MAP
    }

    public void initializeRenderer(GameGraphics graphicsInstance) {
        this.graphics = graphicsInstance;
        int tileWidth = tiledMap.getProperties().get("tilewidth", Integer.class);
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, graphics.pixelSize / (float) tileWidth);
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
        if (mapRenderer != null) { //checks if its not null (major bug)
            mapRenderer.setView(graphics.camera);
            mapRenderer.render();
        }
    }

    public void dispose() {
        if (tiledMap != null) tiledMap.dispose();
        if (mapRenderer != null) mapRenderer.dispose();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (map[x][y] != null) {
                    map[x][y].dispose();
                }
            }
        }
    }
}

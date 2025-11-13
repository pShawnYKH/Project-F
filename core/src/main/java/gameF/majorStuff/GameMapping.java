package gameF.majorStuff;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import gameF.majorStuff.minorStuff.Tile; // Ensure Tile is imported correctly

public class GameMapping {
    private final Tile[][] map;
    private final int width, height;
    // Removed 'final' keyword so we can set it later
    private GameGraphics graphics;

    private final TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer; // Removed 'final' keyword

    // The constructor now safely handles a 'null' graphics input temporarily
    public GameMapping(int width, int height, GameGraphics graphics) {
        this.width = width;
        this.height = height;
        this.graphics = graphics; // Will be null initially from MainGame.create()
        map = new Tile[width][height];

        //fill map with dirt tiles for now (this part is fine with null graphics)
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int variant = MathUtils.random(1, 11);
                map[x][y] = new Tile("tiles/dirt tiles/dirt" + variant + ".png", true);
            }
        }

        tiledMap = new TmxMapLoader().load("TiledEditor/world/baseworld.tmx");

        // We DO NOT initialize mapRenderer here anymore, because 'graphics' is null.
    }

    // Call this new method from MainGame.create() after 'graphics' is ready
    public void initializeRenderer(GameGraphics graphicsInstance) {
        this.graphics = graphicsInstance;
        int tileWidth = tiledMap.getProperties().get("tilewidth", Integer.class);
        // We can now safely use graphics.pixelSize
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
        if (mapRenderer != null) { // Safety check
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

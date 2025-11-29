package gameF;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.graphics.Pixmap; //for crosshair
//import com.badlogic.gdx.graphics.Texture; //for crosshair

import gameF.majorStuff.*;

public class MainGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private LogicUpdateRate logicUpdateRate;
    private PlayerActions playerActions;
    private Vector2 playerPosi;
    private Vector2 previousPlayerPosi;
    private GameGraphics graphics;
    private Player player;
    private GameMapping gameMapping;
    //private Texture crosshairTexture; //crosshair debug

    @Override
    public void create() {
        batch = new SpriteBatch();
        logicUpdateRate = new LogicUpdateRate();

        gameMapping = new GameMapping(50, 40, null); //size of map
        graphics = new GameGraphics(gameMapping);
        gameMapping.initializeRenderer(graphics);

        resize(com.badlogic.gdx.Gdx.graphics.getWidth(), com.badlogic.gdx.Gdx.graphics.getHeight());

        player = new Player(graphics);
        playerActions = new PlayerActions();
        playerPosi = new Vector2(0, 0); //player initial spawnpoint
        previousPlayerPosi = new Vector2(0, 0);

        /* comment this multi line comment for crosshair debug tool
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 1, 1, 1); //color white
        pixmap.fill();
        crosshairTexture = new Texture(pixmap);
        pixmap.dispose();
        */
    }

    @Override
    public void render() {
        float deltaTime = com.badlogic.gdx.Gdx.graphics.getDeltaTime();

        if (logicUpdateRate.updateIn(deltaTime)) { //handles mechanics related/affected by updaterate
            previousPlayerPosi.set(playerPosi);
            playerPosi = playerActions.listenPlayerMovement(playerPosi);
        }

        float alpha = logicUpdateRate.getCurrTimeAccumulated() / LogicUpdateRate.UPDATES_IN_SECOND;

        float interpolatedX = previousPlayerPosi.x + (playerPosi.x - previousPlayerPosi.x) * alpha;
        float interpolatedY = previousPlayerPosi.y + (playerPosi.y - previousPlayerPosi.y) * alpha;

        player.x = interpolatedX;
        player.y = interpolatedY;
        graphics.centerPov(interpolatedX, interpolatedY);

        com.badlogic.gdx.Gdx.gl.glClearColor(0.1f, 0.1f, 0.12f, 1);
        com.badlogic.gdx.Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(graphics.camera.combined);
        batch.begin();

        graphics.gameMapping.draw(batch); //dirt
        graphics.renderMap(); //tmx layer
        player.draw(batch); //player
        //drawCrosshair(batch); //crosshair debug tool

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        graphics.viewport.update(width, height, true);
    }

    /* Uncomment
    private void drawCrosshair(SpriteBatch batch) {
        // Get the exact center of the camera's view
        float cx = graphics.camera.position.x;
        float cy = graphics.camera.position.y;

        // Use graphics.viewport for size reference
        float width = graphics.viewport.getWorldWidth();
        float height = graphics.viewport.getWorldHeight();

        // vertical line (1 unit wide, full height of viewport)
        batch.draw(crosshairTexture, cx - 0.5f, cy - height / 2f, 1, height);
        // horizontal line (full width of viewport, 1 unit high)
        batch.draw(crosshairTexture, cx - width / 2f, cy - 0.5f, width, 1);
    }
    */

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        graphics.dispose();
        //if (crosshairTexture != null) crosshairTexture.dispose();
    }
}

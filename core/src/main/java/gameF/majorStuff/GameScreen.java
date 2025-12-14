package gameF.majorStuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Pixmap; //for crosshair
import com.badlogic.gdx.graphics.Texture; //for crosshair

import gameF.MainGame;

public class GameScreen implements Screen {

    final MainGame gameF; // Reference to main game to access batch

    private LogicUpdateRate logicUpdateRate;
    private PlayerActions playerActions;
    private Vector2 playerPosi;
    private Vector2 previousPlayerPosi;
    private GameGraphics graphics;
    private Player player;
    private GameMapping gameMapping;
    private Texture crosshairTexture; //crosshair debug

    public GameScreen(MainGame gameF) {
        this.gameF = gameF;

        // Logic copied from your old create()
        logicUpdateRate = new LogicUpdateRate();

        gameMapping = new GameMapping(50, 40, null); //size of map
        graphics = new GameGraphics(gameMapping);
        gameMapping.initializeRenderer(graphics);

        // Force update viewport immediately so it doesn't glitch on first frame
        graphics.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        player = new Player(graphics);
        playerActions = new PlayerActions();
        playerPosi = new Vector2(1000, 1000); //player initial spawnpoint (in pixel units)
        previousPlayerPosi = new Vector2(1000, 1000); // Set to match current to avoid interp glitch on start

        /* comment this multi line comment for crosshair debug tool
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 1, 1, 1); //color white
        pixmap.fill();
        crosshairTexture = new Texture(pixmap);
        pixmap.dispose();
        */
    }

    @Override
    public void render(float delta) {
        // Your exact logic loop
        float deltaTime = Gdx.graphics.getDeltaTime();

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

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.12f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Use the MainGame batch (gameF.batch) instead of a local one
        gameF.batch.setProjectionMatrix(graphics.camera.combined);
        gameF.batch.begin();

        graphics.gameMapping.draw(gameF.batch); //dirt
        graphics.renderMap(); //tmx layer
        player.draw(gameF.batch); //player

        //drawCrosshair(gameF.batch); //crosshair debug tool

        gameF.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        graphics.viewport.update(width, height, true);
    }

    /* Uncomment
    private void drawCrosshair(com.badlogic.gdx.graphics.g2d.SpriteBatch batch) {
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
        // batch is disposed in MainGame, so we only dispose local assets
        player.dispose();
        graphics.dispose();
        if (crosshairTexture != null) crosshairTexture.dispose();
    }

    // Unused Screen methods
    @Override public void show() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}

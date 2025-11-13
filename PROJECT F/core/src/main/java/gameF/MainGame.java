package gameF;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import gameF.majorStuff.GameMapping;
import gameF.majorStuff.LogicUpdateRate;
import gameF.majorStuff.PlayerActions;
import gameF.majorStuff.GameGraphics;
import gameF.majorStuff.Player;

public class MainGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private LogicUpdateRate logicUpdateRate;
    private PlayerActions playerActions;
    private Vector2 playerPosi;
    private GameGraphics graphics;
    private Player player;
    private GameMapping gameMapping;

    @Override
    public void create() {
        batch = new SpriteBatch();
        logicUpdateRate = new LogicUpdateRate();
        playerActions = new PlayerActions();

        graphics = new GameGraphics();
        gameMapping = new GameMapping(50, 40, graphics);
        player = new Player(graphics);

        playerPosi = new Vector2(-1, -1); // spawn point: bottom left corner
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        if (logicUpdateRate.updateIn(deltaTime)) {

            playerPosi = playerActions.listenPlayerMovement(playerPosi);
            player.x = playerPosi.x;
            player.y = playerPosi.y;

            graphics.centerPov(player.x, player.y); //gets player position to follow the player while moving
            batch.setProjectionMatrix(graphics.camera.combined);

            Gdx.gl.glClearColor(0.1f, 0.1f, 0.12f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.setProjectionMatrix(graphics.camera.combined);
            batch.begin();
            graphics.gameMapping.draw(batch);
            graphics.renderMap();
            player.draw(batch);
            batch.end();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        graphics.dispose();
        graphics.gameMapping.dispose();
    }
}

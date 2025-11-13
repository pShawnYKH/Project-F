package gameF.majorStuff;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameGraphics {
    public final int originalPixelSize = 19;
    public final int scale = 3;
    public final int pixelSize = originalPixelSize * scale;

    public GameMapping gameMapping; //store reference map
    public OrthographicCamera camera;
    public Viewport viewport;

    public GameGraphics(GameMapping mapInstance) {
        this.gameMapping = mapInstance;
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
    }

    public void centerPov(float playerX, float playerY) {
        camera.position.set(playerX, playerY, 0); //centered exactly on player posi
        camera.update();
    }

    public void renderMap() {
        gameMapping.drawTiledMap();
    }

    public void dispose() {
        gameMapping.dispose();
    }
}

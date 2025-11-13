package gameF.majorStuff;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Gdx;

public class GameGraphics {
    public final int originalPixelSize = 19;
    public final int scale = 3;
    public final int pixelSize = originalPixelSize * scale;

    public GameMapping gameMapping;

    public OrthographicCamera camera;
    public Viewport viewport;

    public GameGraphics() {
        gameMapping = new GameMapping(50, 40, this);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 900); // match your window size
        viewport = new FitViewport(1600, 900, camera);
        camera.update();
    }

    public void centerPov(float playerX, float playerY) {
        camera.position.set(playerX + pixelSize/2f, playerY + pixelSize, 0);

        //prevent wobbly effect from the tmx file
        camera.position.x = Math.round(camera.position.x);
        camera.position.y = Math.round(camera.position.y);

        camera.update();
    }

    public void renderMap(){
        gameMapping.drawTiledMap();
    }

    public void dispose(){
        gameMapping.dispose();
    }
}

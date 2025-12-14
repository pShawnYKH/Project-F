package gameF;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gameF.majorStuff.GameMainMenuUI;

public class MainGame extends Game {
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // Start immediately with the UI
        this.setScreen(new GameMainMenuUI(this));
    }

    @Override
    public void render() {
        super.render(); // This delegates the render method to the active screen
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

package gameF.majorStuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import gameF.MainGame;

public class GameMainMenuUI implements Screen {

    final MainGame gameF;

    private Texture backgroundTexture;
    private BitmapFont font;

    private GlyphLayout layout;

    public GameMainMenuUI(MainGame gameF){
        this.gameF = gameF;

        backgroundTexture = new Texture("menumap/menu_bg.png");

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2.5f);

        layout = new GlyphLayout();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameF.batch.begin();

        gameF.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        String title = "PROJECT F";
        String prompt = "Press [ENTER] to Start";

        layout.setText(font, title);
        float titleW = layout.width;

        font.draw(gameF.batch, title, (Gdx.graphics.getWidth() - titleW) / 2, 500);

        layout.setText(font, prompt);
        float promptW = layout.width;
        font.draw(gameF.batch, prompt, (Gdx.graphics.getWidth() - promptW) / 2, 350);

        gameF.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameF.setScreen(new GameScreen(gameF));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        font.dispose();
    }
}

package gameF.majorStuff;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
    private final Texture playerTexture;
    private final GameGraphics graphics;

    public float x;
    public float y;

    public Player(GameGraphics graphics){
        this.graphics = graphics;
        playerTexture = new Texture("playerModel1.png");
        x = 0;
        y = 0;
    }

    public void draw(SpriteBatch batch){
        float drawX = x - graphics.pixelSize / 2f;
        float drawY = y;
        batch.draw(playerTexture, drawX, drawY, graphics.pixelSize, graphics.pixelSize * 2);
    }

    public void dispose(){
        playerTexture.dispose();
    }
}

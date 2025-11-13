package gameF.majorStuff;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
    private Texture playerTexture;
    private GameGraphics graphics;

    public float x;
    public float y;

    public Player(GameGraphics graphics){
        this.graphics = graphics;
        playerTexture = new Texture("playerModel1.png");
        x = 0;
        y = 0;
    }

    public void draw(SpriteBatch batch){
        batch.draw(playerTexture, x, y, graphics.pixelSize, graphics.pixelSize * 2);
    }

    public void dispose(){
        playerTexture.dispose();
    }
}

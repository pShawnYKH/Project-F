package gameF.majorStuff.minorStuff;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
    private Texture texture;
    public boolean isWalkable;

    public Tile(String texturePath, boolean isWalkable){
        this.texture = new Texture(texturePath);
        this.isWalkable = isWalkable;
    }

    public Texture getTexture(){
        return texture;
    }

    public void dispose(){
        texture.dispose();
    }
}

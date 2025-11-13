package gameF.majorStuff.minorStuff;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
    private final Texture texture;
    public boolean isWalkable; //depends on the type of tile eg. rockTile is not walkable

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

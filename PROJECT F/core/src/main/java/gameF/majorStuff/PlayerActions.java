package gameF.majorStuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class PlayerActions {

    public float DecidePlayerSpeed (){
        float speed = 600f; //base speed
        float sprintSpeed = 1000f; //speed when sprinting
        float playerSprintSpeed = speed;

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            playerSprintSpeed = sprintSpeed;
        }

        return playerSprintSpeed;
    }

    public Vector2 listenPlayerMovement(Vector2 playerPosition){
        float speed = DecidePlayerSpeed(); //check movement speed
        Vector2 playerSpeed = new Vector2(0,0); //starts at

        //movement via WASD or Arrow Keys
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerSpeed.y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerSpeed.x -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerSpeed.y -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerSpeed.x += speed;
        }
        if  (playerSpeed.len() > 0){ //remove faster diagonal
            playerSpeed.nor(); //normalize
        }

        //debug
        //System.out.println(speed);

        playerSpeed.scl(speed * Gdx.graphics.getDeltaTime()); //scale to speed
        playerPosition.add(playerSpeed); //translate speed to playerPosition var

        return playerPosition;
    }

}

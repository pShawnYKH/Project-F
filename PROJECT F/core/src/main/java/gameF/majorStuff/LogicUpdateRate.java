package gameF.majorStuff;

public class LogicUpdateRate {
    //never changes in under all circumstances
    public static final int UPDATE_LOGIC_RATE = 40; //updates logic 40 times per sec
    private static final float UPDATES_IN_SECOND = 1f / UPDATE_LOGIC_RATE;

    //it stores have much time has passed for the function to know when to update the logic
    private float currTimeAccumulated = 0f;

    public boolean updateIn(float deltaTime){
        currTimeAccumulated += deltaTime;
        if (currTimeAccumulated >= UPDATES_IN_SECOND){
            currTimeAccumulated -= UPDATES_IN_SECOND;
            return true;
        }
        return false;
    }
}

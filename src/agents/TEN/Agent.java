package agents.TEN;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

public class Agent implements MarioAgent {
    private enum STATE {
        WALK_FORWARD, WALK_BACKWARD, JUMP, ATTACK
    }

    private STATE state;
    private boolean facing_left;
    private boolean[] action;


    @Override
    public void initialize(MarioForwardModel model, MarioTimer timer) {
        action = new boolean[MarioActions.numberOfActions()];
        state = agents.TEN.Agent.STATE.WALK_FORWARD;
        facing_left = false;
    }

    @Override
    public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
        int[][] scene = model.getMarioSceneObservation();
        int[][] enemies = model.getMarioEnemiesObservation();

        /** Mario Actions that we have to set in each case
         * action[MarioActions.JUMP.getValue()] = false;
         * action[MarioActions.SPEED.getValue()] = false;
         * action[MarioActions.LEFT.getValue()] = false;
         * action[MarioActions.RIGHT.getValue()] = false;
         */
        switch(state) { //Emmy
        case WALK_FORWARD:
            /**
             * if hole...
             * if obstacle...
             * if brick...
             * if platform...
             */
            break;
        case ATTACK:
            /**
             * if enemy present
             * keep attacking or set to jump
             * else WALKING_FORWARD
             */
            break;
        case JUMP:
            /**
             * if holepresent...
             * else if mario is on ground ...
             */
            break;
        case WALK_BACKWARD:
            /**
             * need to determine when to stop walking backwards
             * maybe set difference between current position and obstacle/platform and use a decrementing counter
             *
             */
            break;
        }
        return action;
    }

    /**
     * From michal agent, I don't know why 8 is used
     * @param relX relative x position from mario
     * @param relY relative y position from mario
     * @param scene from model.getMarioSceneObservation()
     * @return int that represents the location
     */
    private int getLocation(int relX, int relY, int[][] scene) {
        int realX = 8 + relX;
        int realY = 8 + relY;

        return scene[realX][realY];
    }
    /**
     * Look in michal.agent for inspiration
     * We need functions to determine isObstaclePresent, isEnemyPresent, isBrickPresent,
     *      isHolePresent, isPlatformAvailable
     * Each should probably return the location of the respective item, null/-1 if not present
     *
     */
    /** Tanya
     * Determines if there is an obstacle in Marios path
     * @param scene from model.getMarioSceneObservation()
     * @return location as an int of the obstacle, -1 if no obstacle
     */
    public int isObstaclePresent(int[][] scene) {
         int[] inFrontOf = new int[]{getLocation(1, 0, scene), getLocation(2, 0, scene), getLocation(2, -1, scene)};

    for (int value : inFrontOf) {
        if (value == 17 || value == 23 || value == 24) {
            return true;
        }
    }

    return false;

    }
    /** Nate
     * Determines if there is a brick on screen
     * @param scene from model.getMarioSceneObservation()
     * @return location as an int of the brick, -1 if no brick
     */
    public int isBrickPresent(int[][] scene) {
        return -1;
    }
    /** Nate
     * Determines if there is a hole in Marios path
     * @param scene from model.getMarioSceneObservation()
     * @return location as an int of the hole, -1 if no hole
     */
    public int isHolePresent(int[][] scene) {
        return -1;
    }
    /**
     * Determines if there is a platform on screen
     * @param scene from model.getMarioSceneObservation()
     * @return location of left most platform as an int, -1 if no platform
     */
    public int isPlatformAvailable(int[][] scene) {
        return -1;
    }
    /** Tanya
     * Determines if there is an enemy in Marios path
     * @param enemies from model.getMarioEnemiesObservation()
     * @return location as an int of the enemy, -1 if no enemy
     * 11/08 iteration: what if we write the function to return a boolean instead of the coordinates 
     * if an enemy is there, the function can point to a "attackEnemy" function that retrieves coordinates 
     */
    public boolean isEnemyPresent(int[][] enemies) {
        for (int row = -1; row <= 0; row++) {
        for (int col = -1; col <= 1; col++) {
            if (getLocation(col, row, enemies) > 1) {
                return true;
            }
        }
    }
    return false;
    }

    @Override
    public String getAgentName() {
        return "TENAgent";
    }
}

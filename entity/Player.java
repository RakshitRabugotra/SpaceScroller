/*
 * We can control this class state, as the Player is playable
 */
package SpaceScroller.entity;

import SpaceScroller.constants.BulletClusterProps;
import SpaceScroller.constants.Constants;
import SpaceScroller.main.KeyHandler;

import java.awt.Color;

public class Player extends Entity {

    // Reference to the KeyHandler
    private KeyHandler keyH;

    // Color of the player... (default)
    protected Color color = Color.GREEN;

    // The type of bullet that we will store
    private int bulletSpawnType = 0;

    public Player(KeyHandler keyH, int posX, int posY) {
        // Call the superclass constructor 
        super(posX, posY, Constants.SCALED_TILESIZE, Constants.SCALED_TILESIZE, null);

        // Copy the reference to the KeyHandler
        this.keyH = keyH;
    }

    public void update(double dt) {
        /*
         * We will handle key-inputs here!
         */
        // Call the superclass update method
        super.update(dt);

        // Move the player around the screen
        if(keyH.upPressed) this.y--;
        if(keyH.downPressed) this.y++;

        if(keyH.leftPressed) this.x--;
        if(keyH.rightPressed) this.x++;

        // To change the bullet type
        if(keyH.switchBulletPressed) {
            this.bulletSpawnType++;
            if(this.bulletSpawnType == BulletClusterProps.CLUSTERS.length) this.bulletSpawnType = 0;
        }

        // Clamp the position of the player
        this.x = Math.min(Math.max(this.x, 0), Constants.MAX_SCREEN_COLS - 1); // -1 for the width and height
        this.y = Math.min(Math.max(this.y, 0), Constants.MAX_SCREEN_ROWS - 1); // -1 for the width and height
    }

    /*
     * Setters
     */
    // To change the spawn bullet type of the player
    public void setBulletSpawnType(int directionIndex) {
        // Check if the direction given is valid
        if(0 <= directionIndex && directionIndex < Constants.VALID_DIRECTIONS.length) {
            this.bulletSpawnType = directionIndex;
        }
    }

    /*
     * Getters
     */
    @Override
    public Color getColor() { return this.color; }

    public int getBulletSpawnType() { return this.bulletSpawnType; }
}
/*
 * We can control this class state, as the Player is playable
 */
package SpaceScroller.entity;

import SpaceScroller.constants.BulletClusterProps;
import SpaceScroller.constants.Constants;
import SpaceScroller.main.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity {

    // Reference to the KeyHandler
    private KeyHandler keyH;

    // Color of the player... (default)
    protected Color color = Color.GREEN;

    // The type of bullet that we will store
    private int bulletSpawnType = 0;

    public Player(KeyHandler keyH, int posX, int posY) {
        // Call the superclass constructor 
        super(posX, posY, Constants.SCALED_TILESIZE, Constants.SCALED_TILESIZE);

        // Copy the reference to the KeyHandler
        this.keyH = keyH;
    }

    @Override
    public void update(double dt) {
        /*
         * We will handle key-inputs here!
         */

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

    @Override
    public void draw(Graphics2D g2) {
        /*
         * We don't need a render function on this class
         * As we will render the player in TileMap class automatically
         */
        return;
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
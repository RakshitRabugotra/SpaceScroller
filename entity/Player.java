/*
 * We can control this class state, as the Player is playable
 */
package SpaceScroller.entity;

import SpaceScroller.constants.Constants;
import SpaceScroller.main.KeyHandler;
import java.awt.Graphics2D;

public class Player extends Entity {

    // Reference to the KeyHandler
    private KeyHandler keyH;

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

        // Clamp the position of the player
        this.x = Math.min(Math.max(this.x, 0), Constants.MAX_SCREEN_COLS - this.width);
        this.y = Math.min(Math.max(this.y, 0), Constants.MAX_SCREEN_ROWS - this.height);
    }

    @Override
    public void draw(Graphics2D g2) {
        /*
         * We don't need a render function on this class
         * As we will render the player in TileMap class automatically
         */
        return;
    }

}
package SpaceScroller.entity;

import SpaceScroller.constants.Constants;

import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Entity {
    
    // Check if the Enemy is active
    public boolean isActive = false;

    // Color of the enemy
    private Color color = Color.RED;

    public Enemy(int posX, int posY) {
        // Call the superclass constructor
        super(posX, posY, Constants.SCALED_TILESIZE, Constants.SCALED_TILESIZE);

        // Set the active status
        this.isActive = true;
    }

    @Override
    public void update(double dt) {
        /*
         * We will move each enemy different way so,
         * don't do anything here, just clamp the positions
         */
        this.x = Math.min(Math.max(this.x, 0), Constants.MAX_SCREEN_COLS - this.width);
        this.y = Math.min(Math.max(this.y, 0), Constants.MAX_SCREEN_ROWS - this.height);
    }

    @Override
    public void draw(Graphics2D g2) {
        // We don't need to render the Enemy too
        return;
    }

    /*
     * Getters
     */
    public Color getColor() { return this.color; }
}

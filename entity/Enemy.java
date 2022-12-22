package SpaceScroller.entity;

import SpaceScroller.constants.Constants;

import java.awt.Color;

public class Enemy extends Entity {
    // Color of the enemy
    protected Color color = Color.RED;

    // The health points of the Enemy can be specified

    public Enemy(int posX, int posY, int healthPoints) {
        // Call the superclass constructor
        super(posX, posY, Constants.SCALED_TILESIZE, Constants.SCALED_TILESIZE, null);

        // Set the healthPoints
        this.setHealthPoints(healthPoints);
    }

    public void update(double dt) {
        /*
         * We will move each enemy different way so,
         * don't do anything here, just clamp the positions
         */
        // Call the superclass update method
        super.update(dt);

        this.x = Math.min(Math.max(this.x, 0), Constants.MAX_SCREEN_COLS - 1); // -1 for width and height
        this.y = Math.min(Math.max(this.y, 0), Constants.MAX_SCREEN_ROWS - 1); // -1 for width and height
    }

    /*
     * Getters
     */
    @Override
    public Color getColor() { return this.color; }
}

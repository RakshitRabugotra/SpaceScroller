package SpaceScroller.entity;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Entity {
    
    // Every entity has some x, y
    public int x, y;

    // Each entity has some dimension to it
    public int width, height;

    // Check if the Entity is active
    public boolean isActive = false;

    // protected attribute of color
    protected Color color = Color.WHITE;

    // The health point of entities
    protected int healthPoints = 100;

    public Entity(int posX, int posY, int entityWidth, int entityHeight) {
        x = posX;
        y = posY;
        width = entityWidth;
        height = entityHeight;
        isActive = true;
    }

    // Each entity has some update method
    public void update(double dt) {

    }

    // Each entity has some draw method
    public void draw(Graphics2D g2) {

    }

    // To check a collision
    protected static boolean isColliding(Entity b, Entity e) {
        // A simple collision of positions is valid here!
        return (b.x == e.x) && (b.y == e.y);
    }

    // To check Entity is out of bounds
    public static boolean isOutOfBounds(Entity b, int lowerBoundX, int upperBoundX, int lowerBoundY, int upperBoundY) {
        // return true if the bullet is either out of bounds from the X or Y
        boolean isInBoundX = (lowerBoundX <= b.x && b.x < upperBoundX);
        boolean isInBoundY = (lowerBoundY <= b.y && b.y < upperBoundY);

        return !(isInBoundX && isInBoundY);
    }

    /*
     * Getters
     */
    public Color getColor() { return this.color; }
    public int getHealthPoints() { return this.healthPoints; }

    /*
     * Setters
     */
    public void setHealthPoints(int hp) { this.healthPoints = (hp > 0) ? hp : this.healthPoints; }
    
}

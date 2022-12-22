package SpaceScroller.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

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
    private int healthPoints = 100;

    // The attack points of the Bullet
    private int hitPoints = 1;

    // The Buffered image used to represent this entity
    protected BufferedImage entityImage;

    public Entity(int posX, int posY, int entityWidth, int entityHeight, String imageFilePath) {
        x = posX;
        y = posY;
        width = entityWidth;
        height = entityHeight;
        isActive = true;

        // Try to set the image of entity
        try {
            entityImage = imageFilePath == null ? null : createImage(imageFilePath);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    // Each entity has some update method
    public void update(double dt) {
        // If the entity has run out of HealthPoints, then make it inactive
        if(healthPoints <= 0) isActive = false;
    }

    // Each entity has some draw method
    public void draw(Graphics2D g2) {
        // If the image is null, then render a rectangle with given color
        if(entityImage == null) {
            g2.setColor(this.getColor());
            g2.drawRect(x * width, y * height, width, height);
            return;
        }

        // Else, render the image
        g2.drawImage(this.entityImage, x * width, y * height, width, height, null);
    }

    // To check a collision
    public static boolean isColliding(Entity b, Entity e) {
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
    public int getHitPoints() { return this.hitPoints; }

    /*
     * Setters
     */
    public void setHealthPoints(int healthP) { this.healthPoints = (healthP >= 0) ? healthP : this.healthPoints; }
    public void setHitPoints(int hitP) { this.hitPoints = (hitP > 0) ? hitP : this.hitPoints; }
    /*
     * Helper functions
     */
    private static BufferedImage createImage(String filepath) throws IOException {
        // Create a file from this path
        File imageFile = new File(filepath);
        // Convert this to input stream
        try {
            InputStream targetImageStream = new FileInputStream(imageFile);
            // Return a buffered image object
            return ImageIO.read(targetImageStream);

        } catch(FileNotFoundException fnfe) {

            System.out.println("Filepath '" + filepath + "' couldn't be found");
            return null;
        }
    }
}

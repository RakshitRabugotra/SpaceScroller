package SpaceScroller.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import SpaceScroller.constants.Constants;

public class Bullet extends Entity {

    // The direction in which the bullet will move
    public String direction = "N";

    // The valid directions for the bullet
    private String[] validDirections = new String[] {"N", "E", "S", "W", "NW", "NE", "SE", "SW", "NESW"};

    // A color for the bullet
    private Color color = Color.ORANGE;

    public Bullet(int posX, int posY, String direction) throws IllegalArgumentException {
        // Call the superclass constructor
        super(posX, posY, Constants.SCALED_TILESIZE, Constants.SCALED_TILESIZE);
        
        // Validate the direction
        // Valid directions are these
        for(String validDirection: validDirections) {
            if(validDirection.compareTo(direction) != 0) throw new IllegalArgumentException("Parameter 'direction' is not valid: " + direction);
        }

        // Else we will copy the direction
        this.direction = direction;
    }
    
    @Override
    public void update(double dt) {
        // Add the speed to the position
        switch(this.direction) {
            case "N":
                this.y--;
                break;

            case "S":
                this.y++;
                break;

            case "E":
                this.x++;
                break;

            case "W":
                this.x--;
                break;

            case "NE":
                this.y--;
                this.x++;
                break;

            case "NW":
                this.x--;
                this.y--;
                break;

            case "SE":
                this.y++;   
                this.x++;
                break;

            case "SW":
                this.y++;
                this.x--;
                break;

            default:
                System.out.println("Illegal value for direction: " + direction);
                break;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        // Draw method will be handled by the TileMap
    }

    public boolean isColliding(Entity e) {
        // A simple collision of positions is valid here!
        return (this.x == e.x && this.y == e.y);
    }

    /*
     * Getters
     */
    public Color getColor() { return this.color; }
}

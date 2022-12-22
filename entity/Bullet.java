package SpaceScroller.entity;

import java.awt.Color;

import SpaceScroller.constants.Constants;

public class Bullet extends Entity {

    // The direction in which the bullet will move
    public String direction = "N";

    // A color for the bullet
    private Color color = Color.ORANGE;

    // The path to the image of Bullet class
    private static final String imageFilePath = "SpaceScroller/art/bullet1.png";

    public Bullet(int posX, int posY, String direction) throws IllegalArgumentException {
        // Call the superclass constructor
        super(posX, posY, Constants.SCALED_TILESIZE, Constants.SCALED_TILESIZE, imageFilePath);

        // Else we will copy the direction
        this.direction = direction;

        // The HP will always be 1
        this.healthPoints = 1;
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

            case "NEWS":
                break;

            default:
                System.out.println("Illegal value for direction: " + direction);
                break;
        }
    }

    // To validate the direction of the Bullet
    public static boolean isValidDirection(String bulletDirection) {
        // Validate using the Constants array
        for(String dir: Constants.VALID_DIRECTIONS) { if(!dir.equals(bulletDirection)) { return false; }}
        // The direction is valid
        return true;
    }

    /*
     * Getters
     */
    @Override
    public Color getColor() { return this.color; }

    /*
     * Setters
     */
    @Override
    public void setHealthPoints(int hp) { /* Cannot change the HP of bullet */ this.healthPoints = 1;}

}

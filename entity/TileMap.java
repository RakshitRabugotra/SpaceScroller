package SpaceScroller.entity;

import SpaceScroller.constants.Constants;
import SpaceScroller.main.GamePanel;
import SpaceScroller.main.KeyHandler;

import java.awt.Graphics2D;
import java.util.ArrayList;;

public class TileMap extends Entity {

    // To store the current active Enemies
    private ArrayList<Entity> currentActiveEntities = new ArrayList<>();

    // To store the Player
    private Player player;

    // Copy the reference to the keyHandler
    private KeyHandler keyH;
    // Copy the reference to the GamePanel
    private GamePanel gp;

    // The grid used for rendering and positioning entities
    private int[][] grid = new int[Constants.MAX_SCREEN_ROWS][Constants.MAX_SCREEN_COLS];

    public TileMap(GamePanel gp, KeyHandler keyH) {
        // Call the superclass constructor
        super(0, 0, Constants.MAX_SCREEN_COLS, Constants.MAX_SCREEN_ROWS);

        // Copy the keyHandler reference
        this.keyH = keyH;
        this.gp = gp;

        // Initialize the grid with zeros
        for(int row = 0; row < this.height; row++) {
            for(int col = 0; col < this.width; col++) {
                grid[row][col] = 0;
            }
        }
    }

    // To set the player reference
    public void setPlayer(int posX, int posY) {
        this.player = new Player(this.keyH, posX, posY);
    }

    // To add an enemy
    public void addEnemy(Enemy e) {
        currentActiveEntities.add(e);
    }

    @Override
    public void update(double dt) {
        /*
         * Update the positions of the Player and enemies here!
         */

        // Remove all the inactive entities
        currentActiveEntities = removeInactiveEntities(currentActiveEntities);

        // Update all the enemies that are active
        for(Entity e: currentActiveEntities) e.update(dt);

        // Update the player
        player.update(dt);

        // If the user wants to shoot, then shoot a bullet
        if(keyH.shootPressed) {
            // Instantiate a new bullet and give it upwards direction
            currentActiveEntities.add(new Bullet(player.x, player.y-1, "N"));
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        /*
         * To render the tilemap, let's use a simple approach
         */
        for(int row = 0; row < this.height; row++) {
            for(int col = 0; col < this.width; col++) {
                // If the position is same as player, then render a green rectangle
                if(col == player.x && row == player.y) {
                    g2.setColor(player.getColor());
                    g2.drawRect(player.x * player.width, player.y * player.height, player.width, player.height);
                    continue;
                }

                // If the position is same as any enemy, then render the enemy
                for(Entity e : currentActiveEntities) {
                    if(col == e.x && row == e.y) {
                        g2.setColor(e.getColor());
                        g2.drawRect(e.x * e.width, e.y * e.height, e.width, e.height);
                    } 
                }

                // Now we will print nothing (blank spaces)
                g2.setColor(gp.getBackground());
            }
        }
    }

    /*
     * To remove all the inactive entities from the given arraylist
     */
    private static ArrayList<Entity> removeInactiveEntities(ArrayList<Entity> entityArrayList) {
        // Make a new arraylist and add only active entities to it
        ArrayList<Entity> activeEntities = new ArrayList<>();
        for(Entity e: entityArrayList) { if(e.isActive) activeEntities.add(e); }
        return activeEntities;
    }
    
}

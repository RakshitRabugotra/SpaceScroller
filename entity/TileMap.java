package SpaceScroller.entity;

import SpaceScroller.constants.Constants;
import SpaceScroller.main.GamePanel;
import SpaceScroller.main.KeyHandler;
import SpaceScroller.resource.ClusterBullet;

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

        // Check collisions of entities with other entities
        checkEntitiesCollision();

        // Remove all the inactive entities
        currentActiveEntities = removeInactiveEntities(currentActiveEntities);

        // Update all the enemies that are active
        for(Entity e: currentActiveEntities) e.update(dt);

        // Display the number of entities in the scene
        System.out.println("COUNT <ENTITIES>: " + currentActiveEntities.size());

        // Update the player
        player.update(dt);

        // If the user wants to shoot, then shoot a bullet
        if(keyH.shootPressed) {
            // Instantiate a new Bullet Cluster and add it's bullets to the entities list
            for(Bullet b: ClusterBullet.createCluster(player.x, player.y, player.getBulletSpawnType())) { currentActiveEntities.add(b); }
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

    /*
     * To check the collisions of one entity with other
     */
    private void checkEntitiesCollision() {
        // Make out of bounds or colliding bullets inactive
        for(int i = 0; i < currentActiveEntities.size(); i++) {

            // Skip the iteration if the class of this object is not Bullet
            Entity thisEntity = currentActiveEntities.get(i);

            if(!thisEntity.getClass().getName().endsWith("Bullet")) continue;

            // Check on all the items
            for(int j = 0; j < currentActiveEntities.size(); j++) {
                
                // Don't check for the same item
                if(j == i) continue;

                // If the class of object is bullet, and the class of other object is not Player
                // Then, we will make the bullet inactive
                Entity otherEntity = currentActiveEntities.get(j);
                
                // Don't check for bullet-bullet collision
                if(otherEntity.getClass().getName().endsWith("Bullet")) continue;

                // If the bullet collides this entity then, make it disappear
                if(Entity.isColliding(thisEntity, otherEntity)) {
                    currentActiveEntities.get(i).isActive = false;
                }
            }

            // If the bullet is out of bounds then also make it inactive
            if(Entity.isOutOfBounds(thisEntity, 0, Constants.MAX_SCREEN_COLS, 0, Constants.MAX_SCREEN_ROWS)) {
                currentActiveEntities.get(i).isActive = false;
            }
        }
    }
    
}

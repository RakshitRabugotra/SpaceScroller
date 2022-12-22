/*
 * File to handle a cluster of bullets
 */
package SpaceScroller.resource;

import java.util.ArrayList;

import SpaceScroller.constants.BulletClusterProps;
import SpaceScroller.entity.Bullet;

public abstract class ClusterBullet {

    // The Mapping of directions
    private static final String[][] directionMapping = new String[][] {
        {"NW", "N", "NE"},
        {"W", "NEWS", "E"},
        {"SW", "S", "SE"}
    };

    public static ArrayList<Bullet> createCluster(int posX, int posY, int clusterIndex) {
        try {
            // To refer the shape of cluster
            int[][] clusterShape = BulletClusterProps.CLUSTERS[clusterIndex];;

            // Return arraylist of bullet entities
            return initializeBullets(posX, posY, clusterShape);

        } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
            outOfBoundsException.printStackTrace();
            // Return the arraylist
            return null;
        }
    }

    /*
     * To decide how many bullets to initialize and with which direction
     */
    public static ArrayList<Bullet> initializeBullets(int posX, int posY, int[][] clusterShape) {
        // The bullets inside the cluster
        ArrayList<Bullet> clusterBullets = new ArrayList<>();
        
        // Initialize the bullets we want according to the shape
        for(int row = 0; row < clusterShape.length; row++) {
            for(int col = 0; col < clusterShape[row].length; col++) {

                // If the number in Shape is 1, then add a bullet to the arraylist
                if(clusterShape[row][col] == 0) continue;

                // Add bullet to arraylist
                clusterBullets.add(new Bullet(posX, posY, directionMapping[row][col]));
                System.out.println("Added bullet with direction: " + directionMapping[row][col]);
            }
        }

        return clusterBullets;
    }
}
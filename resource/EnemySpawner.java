package SpaceScroller.resource;

import SpaceScroller.entity.Enemy;

import java.util.Random;

public abstract class EnemySpawner {
    /*
     * Class to spawn multiple enemies
     */
    // The RNG used to generate Random Numbers
    private static final Random RNG = new Random(System.currentTimeMillis());

    public static Enemy spawnRandomEnemies(int lowerBoundX, int upperBoundX, int lowerBoundY, int upperBoundY, int healthPoints) {
        int posX = RNG.nextInt(lowerBoundX, upperBoundX);
        int posY = RNG.nextInt(lowerBoundY, upperBoundY);

        return new Enemy(posX, posY, healthPoints);
    }
}

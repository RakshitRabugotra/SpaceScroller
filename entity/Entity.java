package SpaceScroller.entity;

import java.awt.Graphics2D;

public abstract class Entity {
    
    // Every entity has some x, y
    public int x, y;

    // Each entity has some dimension to it
    public int width, height;

    public Entity(int posX, int posY, int entityWidth, int entityHeight) {
        x = posX;
        y = posY;
        width = entityWidth;
        height = entityHeight;
    }

    // Each entity has some update method
    public void update(double dt) {

    }

    // Each entity has some draw method
    public void draw(Graphics2D g2) {

    }

}

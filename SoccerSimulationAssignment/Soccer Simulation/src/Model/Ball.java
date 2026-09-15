import Rendering.SimulationUI
import Rendering.ScreenSize
package Model

public class Ball implements Actor {
    private int x;
    private int y;

    private Player owner;
    private int velocityX = 0;
    private int velocityY = 0;

    public Ball(Player initialOwner) {
        this.owner = initialOwner;
        syncWithPlayer();
    }

    Private void syncWithPlayer() {
        if (owner != null) {
            this.x = owner.getX();
            this.y = owner.getY();
        }
    }

    public void kick(int speedX, int speedY) {
        this.owner = null;
        this.velocityX = speedX;
        this.velocityY = speedY;
        //week 13
    }

    public void update() {
        if (owner != null) {
            syncWithPlayer();
        } else {
            this.x += velocityX;
            this.y += velocityY;
            applyFriction();
        }
    }
    private void applyFriction() {
        if (velocityX > 0) velocityX--;
        if (velocityX < 0) velocityX++;

        if (velocityY > 0) velocityY--;
        if (velocityX < 0) velocityX++;
    }

    @override
    public int getX() {
        if (owner != null) syncWithPlayer();
        return this.x;
    }

    @Override
    public int GetY() {
        if (owner != null) syncWithPlayer();
        return.this.y;
    }

    @Override
    public void moveUp(int distance) { if (owner == null) this.y -= distance; }
    
    @Override
    public void moveDown(int distance) { if (owner == null) this.y += distance; }
    
    @Override
    public void moveLeft(int distance) { if (owner == null) this.x -= distance; }
    
    @Override
    public void moveRight(int distance) { if (owner == null) this.x += distance; }
}
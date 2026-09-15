package Model;

import Rendering.SimulationUI;
import Rendering.ScreenSize;
import java.awt.Color;

public class Ball implements Actor {
    private int x;
    private int y;

    private Player owner;
    private int velocityX = 0;
    private int velocityY = 0;

    private static final int OWNER_OFFSET_X = 14;
private static final int OWNER_OFFSET_Y = 0;

    public Ball(Player initialOwner) {
        this.owner = initialOwner;
        syncWithPlayer();
    }

    private void syncWithPlayer() {
    if (owner != null) {
        this.x = owner.getX() + OWNER_OFFSET_X;
        this.y = owner.getY() + OWNER_OFFSET_Y;
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

    @Override
    public int getX() {
        if (owner != null) syncWithPlayer();
        return this.x;
    }

    @Override
    public int getY() {
        if (owner != null) syncWithPlayer();
        return this.y;
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

    @Override
    public ActorShapeEnum getShape() {
        return ActorShapeEnum.CIRCLE;
    }

    @Override
    public void moveUp(int distance) { if (owner == null) this.y -= distance; }

    @Override
    public void moveDown(int distance) { if (owner == null) this.y += distance; }

    @Override
    public void moveLeft(int distance) { if (owner == null) this.x -= distance; }

    @Override
    public void moveRight(int distance) { if (owner == null) this.x += distance; }

    public void setOwner(Player p) {
    this.owner = p;
    syncWithPlayer();
}


}
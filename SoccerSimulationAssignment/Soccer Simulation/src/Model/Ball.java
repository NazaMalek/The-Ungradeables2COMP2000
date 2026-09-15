package Model;

import Rendering.SimulationUI;
import Rendering.ScreenSize;
import java.awt.Color;

public class Ball implements Actor {
    private int x;
    private int y;

    private Player owner;

    // Ball velocity is stored as a double so friction can slow
    // the ball smoothly instead of removing 1 pixel every frame.
    private double velocityX = 0;
    private double velocityY = 0;

    private static final int OWNER_OFFSET_X = 14;
    private static final int OWNER_OFFSET_Y = 0;

    // The actors are drawn as 18px circles, so keep the centre at least 9px away from the edge of the pitch.
    private static final int BALL_RADIUS = 9;

    public Ball(Player initialOwner) {
        this.owner = initialOwner;
        syncWithPlayer();
    }

    private void syncWithPlayer() {
        if (owner != null) {
            this.x = owner.getX() + OWNER_OFFSET_X;
            this.y = owner.getY() + OWNER_OFFSET_Y;

            // Keep the ball visible even if its owner reaches the edge
            keepInsidePitch();
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
            this.x += (int) Math.round(velocityX);
            this.y += (int) Math.round(velocityY);

            applyFriction();
            keepInsidePitch();
        }
    }

    private void applyFriction() {
        // ease slow the ball down
        velocityX *= 0.96;
        velocityY *= 0.96;

        // clamp small vel so ball stops and doesnt go forever
        if (Math.abs(velocityX) < 0.15) {
            velocityX = 0;
        }

        if (Math.abs(velocityY) < 0.15) {
            velocityY = 0;
        }
    }

    private void keepInsidePitch() {

        if (x < BALL_RADIUS) {
            x = BALL_RADIUS;
            velocityX = 0;
        }

        if (x > ScreenSize.width - BALL_RADIUS) {
            x = ScreenSize.width - BALL_RADIUS;
            velocityX = 0;
        }

        if (y < BALL_RADIUS) {
            y = BALL_RADIUS;
            velocityY = 0;
        }

        if (y > ScreenSize.height - BALL_RADIUS) {
            y = ScreenSize.height - BALL_RADIUS;
            velocityY = 0;
        }
    }

    @Override
    public int getX() {
        if (owner != null) {
            syncWithPlayer();
        }

        return this.x;
    }

    @Override
    public int getY() {
        if (owner != null) {
            syncWithPlayer();
        }

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
    public void moveUp(int distance) {
        if (owner == null) {
            this.y -= distance;
        }
    }

    @Override
    public void moveDown(int distance) {
        if (owner == null) {
            this.y += distance;
        }
    }

    @Override
    public void moveLeft(int distance) {
        if (owner == null) {
            this.x -= distance;
        }
    }

    @Override
    public void moveRight(int distance) {
        if (owner == null) {
            this.x += distance;
        }
    }

    public void setOwner(Player p) {
        this.owner = p;

        // Once somebody controls the ball clear old velocity
        this.velocityX = 0;
        this.velocityY = 0;

        syncWithPlayer();
    }

    // Match uses this to determine of ball is in pos or not
    public Player getOwner() {
        return owner;
    }
}
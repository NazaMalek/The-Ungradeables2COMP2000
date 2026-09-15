import java.awt.color.*;

public abstract class AbstractActor implements Actor {
    protected int x;
    protected int y;
    protected color color;
    protected ActorShape shape; 

    // constructorer
    public AbstractActor(int startX, int startY, Color color, ActorShape shape) {
        this.x = startX;
        this.y = startY;
        this.color = color;
        this.shape = shape;
    }

    @override
    public int getX() {
        return this.x;
    }

    @Override
    public int GetY() {
        return this.y;
    }

    @Override
    public Color getColor() { 
        return this.color; 
    }

    @Override
    public ActorShape getShape() { 
        return this.shape; 
    }

    //  grid physics
    @Override
    public void moveUp(int distance) { 
        this.y -= distance; 
    }

    @Override
    public void moveDown(int distance) { 
        this.y += distance; 
    }

    @Override
    public void moveLeft(int distance) { 
        this.x -= distance; 
    }

    @Override
    public void moveRight(int distance) { 
        this.x += distance; 
    }
}
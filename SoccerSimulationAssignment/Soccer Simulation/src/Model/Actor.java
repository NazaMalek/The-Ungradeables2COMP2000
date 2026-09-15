import java.awt.color.*;
package Model;

public abstract class Actor {
    //cords
    int getX;
    int getY;

    //mov events
    abstract void moveUp(int distance);
    abstract void moveDown(int distance);
    abstract void moveLeft(int distance);
    abstract void moveRight(int distance);
    
    //visual
    Color getColor();
    ActorShape getShape();
}

package Model;

public abstract class Actor {
    //cords
    int getX();
    int getY();

    //mov events
    void moveUp(int distance);
    void moveDown(int distance);
    void moveLeft(int distance);
    void moveRight(int distance);
    
}

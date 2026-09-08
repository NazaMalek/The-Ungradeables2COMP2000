import java.awt.Color;
import java.awt.Graphics;

public class Player {

    private int x;
    private int y;
    private Color colour;

    public Player(int x, int y, Color colour) {
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    public void draw(Graphics g) {
        g.setColor(colour);
        g.fillOval(x - 6, y - 6, 12, 12);
    }
}
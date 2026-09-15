package Model;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.*;

public class Player extends Actor{

    private int jerseyNumber;
    private Color colour;

    public Player(int x, int y, int jerseyNumber, Color colour) {
        this.x = x;
        this.y = y;
        this.jerseyNumber = jerseyNumber;
        this.colour = colour;
    }

    public void draw(Graphics g) {
        g.setColor(colour);
        g.fillOval(x - 9, y - 9, 18, 18);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        String number = String.valueOf(jerseyNumber);
        FontMetrics metrics = g.getFontMetrics();
        int textX = x - metrics.stringWidth(number) / 2;
        int textY = y + metrics.getHeight() / 2 - metrics.getDescent();
        g.drawString(number, textX, textY);
    }
    
    @Override 
    void moveUp(int distance){

    }

    @Override 
    void moveDown(int distance){

    }

    @Override 
    void moveLeft(int distance){

    }

    @Override 
    void moveRight(int distance){

    }
}
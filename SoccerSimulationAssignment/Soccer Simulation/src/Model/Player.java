package Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Player implements Actor {
    private int x;
    private int y;
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
    
    // interface required getters
    @Override
    public int getX() { return this.x; }

    @Override
    public int getY() { return this.y; }

    @Override
    public Color getColor() { return this.colour; }

    @Override
    public ActorShape getShape() { return ActorShape.SQUARE; } // Or custom assignment

    //movement
    @Override 
    public void moveUp(int distance) {
        // boundary checking
        if (this.y - distance >= 0) {
            this.y -= distance; // Moving UP subtracts from Y
        }
    }

    @Override 
    public void moveDown(int distance) {
        // height limit is from screen size
        if (this.y + distance <= Rendering.ScreenSize.height) {
            this.y += distance; 
        }
    }

    @Override 
    public void moveLeft(int distance) {
        if (this.x - distance >= 0) {
            this.x -= distance;
        }
    }

    @Override 
    public void moveRight(int distance) {
        if (this.x + distance <= Rendering.ScreenSize.width) {
            this.x += distance;
        }
    }
}

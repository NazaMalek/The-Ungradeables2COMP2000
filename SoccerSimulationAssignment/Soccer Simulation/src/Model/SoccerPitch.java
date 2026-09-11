package Model;
import java.awt.*;
import javax.swing.*;

import Rendering.ScreenSize;

public class SoccerPitch extends JPanel {
    

    // Create the players
    private Player[] players;

    public SoccerPitch() {

        setPreferredSize(new Dimension(700, 400));
        players = new Player[] {

            // Blue team
            new Player(100, 200, 1, Color.BLUE),
            new Player(180, 100, 2, Color.BLUE),
            new Player(180, 300, 3, Color.BLUE),
            new Player(280, 150, 4, Color.BLUE),
            new Player(280, 250, 5, Color.BLUE),

            // Red team
            new Player(600, 200, 1, Color.RED),
            new Player(520, 100, 2, Color.RED),
            new Player(520, 300, 3, Color.RED),
            new Player(420, 150, 4, Color.RED),
            new Player(420, 250, 5, Color.RED)
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(50, 150, 50));
        g.fillRect(0, 0, ScreenSize.width, ScreenSize.height);

        g.setColor(Color.WHITE);

        // Outer border
        g.drawRect(0, 0, ScreenSize.width, ScreenSize.height);

        // Halfway line
        g.drawLine(ScreenSize.width / 2, 0, ScreenSize.width / 2, ScreenSize.height);

        // Centre circle
        int circleRadius = 60;
        g.drawOval(ScreenSize.width / 2 - circleRadius, 
                ScreenSize.height / 2 - circleRadius, circleRadius * 2, circleRadius * 2);

        // Centre spot
        g.fillOval(ScreenSize.width / 2 - 4, ScreenSize.height / 2 - 4, 8, 8);

        // Penalty areas
        int penaltyWidth = 100, penaltyHeight = 200;
        int penaltyY = (ScreenSize.height - penaltyHeight) / 2;
        g.drawRect(0, penaltyY, penaltyWidth, penaltyHeight);
        g.drawRect(ScreenSize.width - penaltyWidth, penaltyY, penaltyWidth, penaltyHeight);

        // Goals
        int goalWidth = 50, goalHeight = 100;
        int goalY = (ScreenSize.height - goalHeight) / 2;
        g.drawRect(0, goalY, goalWidth, goalHeight);
        g.drawRect(ScreenSize.width - goalWidth, goalY, goalWidth, goalHeight);

        // Penalty spots
        int spotOffset = 79;
        g.fillOval(spotOffset - 4, ScreenSize.height / 2 - 4, 8, 8);
        g.fillOval(ScreenSize.width - spotOffset - 4, ScreenSize.height / 2 - 4, 8, 8);
        
        for (Player player : players) {
            player.draw(g);
        }
    }

    public Player[] getPlayers(){
        return players;
    }

    public void setPlayers(Player[] players){
        this.players = players;
        repaint();
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("Soccer Simulation");

        SoccerPitch pitch = new SoccerPitch();

        frame.add(pitch);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
package Model;

import java.awt.*;
import javax.swing.*;

import Rendering.ScreenSize;

public class SoccerPitch extends JPanel {

    public SoccerPitch() {

        setPreferredSize(new Dimension(ScreenSize.width, ScreenSize.height));

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

    }

    // public static void main(String[] args) {

    //     JFrame frame = new JFrame("Soccer Simulation");

    //     SoccerPitch pitch = new SoccerPitch();

    //     frame.add(pitch);
    //     frame.pack();

    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setLocationRelativeTo(null);
    //     frame.setVisible(true);
    // }
}
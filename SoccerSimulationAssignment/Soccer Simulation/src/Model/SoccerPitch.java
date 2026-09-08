import java.awt.*;
import javax.swing.*;

public class SoccerPitch extends JPanel {

    // Create the players
    private Player[] players;

    public SoccerPitch() {

        setPreferredSize(new Dimension(700, 400));
        players = new Player[] {

            // Blue team
            new Player(100, 200, Color.BLUE),
            new Player(180, 100, Color.BLUE),
            new Player(180, 300, Color.BLUE),
            new Player(280, 150, Color.BLUE),
            new Player(280, 250, Color.BLUE),

            // Red team
            new Player(600, 200, Color.RED),
            new Player(520, 100, Color.RED),
            new Player(520, 300, Color.RED),
            new Player(420, 150, Color.RED),
            new Player(420, 250, Color.RED)
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //pitch
        g.setColor(new Color(50, 150, 50));
        g.fillRect(0, 0, 700, 400);

        //White pitch lines
        g.setColor(Color.WHITE);

        //Outer border
        g.drawRect(5, 5, 690, 390);

        //Halfway line
        g.drawLine(350, 5, 350, 395);

        //Centre circle
        g.drawOval(290, 140, 120, 120);

        //Centre spot
        g.fillOval(346, 196, 8, 8);

        //Penalty areas
        g.drawRect(5, 100, 100, 200);
        g.drawRect(595, 100, 100, 200);

        //Goals
        g.drawRect(5, 150, 50, 100);
        g.drawRect(645, 150, 50, 100);

        //Penalty spots
        g.fillOval(75, 196, 8, 8);
        g.fillOval(617, 196, 8, 8);

        
        for (Player player : players) {
            player.draw(g);
        }
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
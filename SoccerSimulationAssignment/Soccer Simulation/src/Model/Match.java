// WILLIAM IS DOING THIS
/*
 * use the display already made in the soccer pitch class,
 * the match logic, goal scoring and ref stuff is being done by other people
 * all this class will do will be to put players on the pitch in certain
 * formations
 * i also have to handle the gui for everything while simulation is running a
 * mini print log
 * execute events just be an area for stuff to happen like pull everything
 * together
 * 
 * 
 * 
 */
package Model;

import java.awt.*;
import javax.swing.*;
import Rendering.ScreenSize;
import Rendering.SimulationUI;

public class Match {
    private Player[] players;
    SoccerPitch pitch;
    int maxPlayers = 22;

    Match() {
        pitch = new SoccerPitch();
        players = new Player[20];
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
        //repaint();
    }

    public void drawPlayers(Graphics g){

        for (Player player : players) {
        player.draw(g);
        }
    }

}
// WILLIAM + RYAN IS DOING THIS
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
 * 
 */
package Model;

import java.awt.*;
import javax.swing.*;
import Rendering.ScreenSize;
import java.util.*;

public class Match {
    public SoccerPitch pitch;
    public Ball ball;

    // Main Arrays
    private ArrayList<Actor> gameActors;
    private ArrayList<Player> players;
    private boolean isRunning;
    private Thread simulationThread;

    public Match() {
        this.pitch = new SoccerPitch();
        this.gameActors = new ArrayList<>();
        this.players = new ArrayList<>();
        this.isRunning = false;
    }

    // spawn entities
    public void setupAndStartSimulation(String homeFormation, String awayFormation) {
        gameActors.clear();
        players.clear();

        // summon ball in centre
        ball = new Ball(null);
        int centerX = ScreenSize.width / 2;
        int centerY = ScreenSize.height / 2;
        
        // Force initial placement on kickoff
        ball.moveLeft(-centerX); 
        ball.moveUp(-centerY);

        // spawn and map formations
        generateTeamFormation(homeFormation, Color.BLUE, true);
        generateTeamFormation(awayFormation, Color.RED, false);

        // register everything to the render loop
        gameActors.addAll(players);
        gameActors.add(ball);

        // begin processing thread loop 
        this.isRunning = true;
        simulationThread = new Thread(this::runSimulationEngineLoop);
        simulationThread.start();
    }

    //specific grid patterns depending on chosen config
    private void generateTeamFormation(String formationType, Color teamColor, boolean isHomeTeam) {
        int centerY = ScreenSize.height / 2;
        int directionX = isHomeTeam ? 1 : -1;
        int baseLineX = isHomeTeam ? 80 : ScreenSize.width - 80;

        // main structural nodes depending on selected configuration parameters
        int defX = baseLineX + (120 * directionX);
        int midX = baseLineX + (280 * directionX);
        int fwdX = baseLineX + (420 * directionX);

        String prefix = isHomeTeam ? "Home_" : "Away_";

        // Always spawn Goalkeeper at base line
        players.add(new Player(prefix + "GK", baseLineX, centerY));

        if ("4-3-3".equals(formationType)) {
            // 4 Defenders
            players.add(new Player(prefix + "DF1", defX, centerY - 150));
            players.add(new Player(prefix + "DF2", defX, centerY - 50));
            players.add(new Player(prefix + "DF3", defX, centerY + 50));
            players.add(new Player(prefix + "DF4", defX, centerY + 150));
            // 3 Midfielders
            players.add(new Player(prefix + "MF1", midX, centerY - 100));
            players.add(new Player(prefix + "MF2", midX, centerY));
            players.add(new Player(prefix + "MF3", midX, centerY + 100));
            // 3 Forwards
            players.add(new Player(prefix + "FW1", fwdX, centerY - 120));
            players.add(new Player(prefix + "FW2", fwdX, centerY));
            players.add(new Player(prefix + "FW3", fwdX, centerY + 120));
        } else if ("3-5-2".equals(formationType)) {
            // 3 Defenders
            players.add(new Player(prefix + "DF1", defX, centerY - 120));
            players.add(new Player(prefix + "DF2", defX, centerY));
            players.add(new Player(prefix + "DF3", defX, centerY + 120));
            // 5 Midfielders
            players.add(new Player(prefix + "MF1", midX, centerY - 160));
            players.add(new Player(prefix + "MF2", midX, centerY - 80));
            players.add(new Player(prefix + "MF3", midX, centerY));
            players.add(new Player(prefix + "MF4", midX, centerY + 80));
            players.add(new Player(prefix + "MF5", midX, centerY + 160));
            // 2 Forwards
            players.add(new Player(prefix + "FW1", fwdX, centerY - 60));
            players.add(new Player(prefix + "FW2", fwdX, centerY + 60));
        } else { // Default fallback to standard "4-4-2"
            // 4 Defenders
            players.add(new Player(prefix + "DF1", defX, centerY - 150));
            players.add(new Player(prefix + "DF2", defX, centerY - 50));
            players.add(new Player(prefix + "DF3", defX, centerY + 50));
            players.add(new Player(prefix + "DF4", defX, centerY + 150));
            // 4 Midfielders
            players.add(new Player(prefix + "MF1", midX, centerY - 150));
            players.add(new Player(prefix + "MF2", midX, centerY - 50));
            players.add(new Player(prefix + "MF3", midX, centerY + 50));
            players.add(new Player(prefix + "MF4", midX, centerY + 150));
            // 2 Forwards
            players.add(new Player(prefix + "FW1", fwdX, centerY - 60));
            players.add(new Player(prefix + "FW2", fwdX, centerY + 60));
        }
    }

    // background engine checking frame
    private void runSimulationEngineLoop() {
        while (isRunning) {
            int ballX = ball.getX();
            int ballY = ball.getY();

            // player ai
            for (Player p : players) {
                if (p.getX() < ballX) p.moveRight(1);
                if (p.getX() > ballX) p.moveLeft(1);
                if (p.getY() < ballY) p.moveDown(1);
                if (p.getY() > ballY) p.moveUp(1);
            }

            ball.update();

            //PAUSE CAPTURE FOR REPAINT PHASES
            try {
                Thread.sleep(16); // Standard 60 fps
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stopSimulation() {
        this.isRunning = false;
    }

    public ArrayList<Actor> getGameActors() {
        return this.gameActors;
    }
}

// teammate logic
class Engine extends Match {
    Team teamA;
    Team teamB;
    Match match;
    int maxPlayers = 22;
    private Random random;
    ArrayList<Team> matchPlayers;

    Engine(Team a, Team b, Match m) {
        this.teamA = a;
        this.teamB = b;
        this.match = m;
        this.matchPlayers = new ArrayList<>();
        this.matchPlayers.add(a);
        this.matchPlayers.add(b);
        this.random = new Random();
    }

    void movePlayers() {
        for (Team teams : matchPlayers) {
            for (Player player : teams.getPlayers()) {
                // Shared mathematical engine logic needs to go here
            }
        }
    }
}

class Team {
    private Player[] players;
    private boolean win;

    Team(Player[] p) {
        this.players = p;
        this.win = false;
    }

    public boolean getWin() { return this.win; }
    public void setWin(boolean w) { this.win = w; }
    public Player[] getPlayers() { return players; }
    public void setPlayers(Player[] players) { this.players = players; }
}

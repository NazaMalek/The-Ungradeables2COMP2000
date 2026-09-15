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

        // push the initial state to the pitch immediately, so something
        // shows up before the simulation loop's first tick
        pitch.setActors(gameActors);

        // begin processing thread loop
        this.isRunning = true;
        simulationThread = new Thread(this::runSimulationEngineLoop);
        simulationThread.start();
    }

    // specific grid patterns depending on chosen config
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
        players.add(new Player(prefix + "GK", baseLineX, centerY, teamColor, true));

        if ("4-3-3".equals(formationType)) {
            // 4 Defenders
            players.add(new Player(prefix + "DF1", defX, centerY - 150, teamColor));
            players.add(new Player(prefix + "DF2", defX, centerY - 50, teamColor));
            players.add(new Player(prefix + "DF3", defX, centerY + 50, teamColor));
            players.add(new Player(prefix + "DF4", defX, centerY + 150, teamColor));
            // 3 Midfielders
            players.add(new Player(prefix + "MF1", midX, centerY - 100, teamColor));
            players.add(new Player(prefix + "MF2", midX, centerY, teamColor));
            players.add(new Player(prefix + "MF3", midX, centerY + 100, teamColor));
            // 3 Forwards
            players.add(new Player(prefix + "FW1", fwdX, centerY - 120, teamColor));
            players.add(new Player(prefix + "FW2", fwdX, centerY, teamColor));
            players.add(new Player(prefix + "FW3", fwdX, centerY + 120, teamColor));
        } else if ("3-5-2".equals(formationType)) {
            // 3 Defenders
            players.add(new Player(prefix + "DF1", defX, centerY - 120, teamColor));
            players.add(new Player(prefix + "DF2", defX, centerY, teamColor));
            players.add(new Player(prefix + "DF3", defX, centerY + 120, teamColor));
            // 5 Midfielders
            players.add(new Player(prefix + "MF1", midX, centerY - 160, teamColor));
            players.add(new Player(prefix + "MF2", midX, centerY - 80, teamColor));
            players.add(new Player(prefix + "MF3", midX, centerY, teamColor));
            players.add(new Player(prefix + "MF4", midX, centerY + 80, teamColor));
            players.add(new Player(prefix + "MF5", midX, centerY + 160, teamColor));
            // 2 Forwards
            players.add(new Player(prefix + "FW1", fwdX, centerY - 60, teamColor));
            players.add(new Player(prefix + "FW2", fwdX, centerY + 60, teamColor));
        } else { // Default fallback to standard "4-4-2"
            // 4 Defenders
            players.add(new Player(prefix + "DF1", defX, centerY - 150, teamColor));
            players.add(new Player(prefix + "DF2", defX, centerY - 50, teamColor));
            players.add(new Player(prefix + "DF3", defX, centerY + 50, teamColor));
            players.add(new Player(prefix + "DF4", defX, centerY + 150, teamColor));
            // 4 Midfielders
            players.add(new Player(prefix + "MF1", midX, centerY - 150, teamColor));
            players.add(new Player(prefix + "MF2", midX, centerY - 50, teamColor));
            players.add(new Player(prefix + "MF3", midX, centerY + 50, teamColor));
            players.add(new Player(prefix + "MF4", midX, centerY + 150, teamColor));
            // 2 Forwards
            players.add(new Player(prefix + "FW1", fwdX, centerY - 60, teamColor));
            players.add(new Player(prefix + "FW2", fwdX, centerY + 60, teamColor));
        }
    }

    // background engine checking frame
    private void runSimulationEngineLoop() {
        while (isRunning) {
            int ballX = ball.getX();
            int ballY = ball.getY();

            // player ai
            for (Player p : players) {
                if (p.isGoalkeeper()) {
                    updateGoalkeeper(p, ballY);
                } else {
                    if (p.getX() < ballX) p.moveRight(1);
                    if (p.getX() > ballX) p.moveLeft(1);
                    if (p.getY() < ballY) p.moveDown(1);
                    if (p.getY() > ballY) p.moveUp(1);
                }
            }
            resolvePlayerCollisions();

             // collision: whichever player is close enough takes possession
        int pickupRadius = 18; // matches the ~9px draw radius of each circle, so they visually touch
        for (Player p : players) {
            int dx = p.getX() - ball.getX();
            int dy = p.getY() - ball.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance <= pickupRadius) {
                ball.setOwner(p);
                break; // first match wins this tick
            }
        }

            ball.update();

            // push updated positions to the pitch so it actually redraws
            // this frame's state — repaint() is safe to call from a
            // background thread, it just schedules the redraw on the EDT
            pitch.setActors(gameActors);

            //PAUSE CAPTURE FOR REPAINT PHASES
            try {
                Thread.sleep(16); // Standard 60 fps
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Goalkeepers only track the ball vertically, and stay clamped inside their box
    private void updateGoalkeeper(Player gk, int ballY) {
        if (gk.getY() < ballY) gk.moveDown(1);
        if (gk.getY() > ballY) gk.moveUp(1);

        int margin = 15; // keeps them off the very edge of the box
        int minY = (ScreenSize.height - SoccerPitch.PENALTY_HEIGHT) / 2 + margin;
        int maxY = minY + SoccerPitch.PENALTY_HEIGHT - (margin * 2);

        if (gk.getY() < minY) gk.moveDown(minY - gk.getY());
        if (gk.getY() > maxY) gk.moveUp(gk.getY() - maxY);
    }

    // Match.java — new method
private void resolvePlayerCollisions() {
    int minDistance = 20; // slightly more than the ~18px draw diameter, so they touch but don't overlap

    for (int i = 0; i < players.size(); i++) {
        for (int j = i + 1; j < players.size(); j++) {
            Player a = players.get(i);
            Player b = players.get(j);

            int dx = b.getX() - a.getX();
            int dy = b.getY() - a.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance > 0 && distance < minDistance) {
                double overlap = (minDistance - distance) / 2;
                int pushX = (int) Math.round(overlap * (dx / distance));
                int pushY = (int) Math.round(overlap * (dy / distance));

                // push a and b apart along the line connecting them
                if (pushX > 0) { a.moveLeft(pushX); b.moveRight(pushX); }
                else if (pushX < 0) { a.moveRight(-pushX); b.moveLeft(-pushX); }

                if (pushY > 0) { a.moveUp(pushY); b.moveDown(pushY); }
                else if (pushY < 0) { a.moveDown(-pushY); b.moveUp(-pushY); }
            }
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
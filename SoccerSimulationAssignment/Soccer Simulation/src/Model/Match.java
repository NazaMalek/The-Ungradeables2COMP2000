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
 * 
 */
package Model;

import java.awt.*;
import javax.swing.*;
import Rendering.ScreenSize;
import Rendering.SimulationUI;
import java.util.*;



public class Match {
    SoccerPitch pitch;
    Ball ball;


    Match() {
        pitch = new SoccerPitch();
    }

    


}


    class Engine extends Match{
        Team teamA;
        Team teamB;
        Match match;
        int maxPlayers = 22;
        private Random random;
        ArrayList<Team> matchPlayers;



        Engine(Team a, Team b, Match m){
            this.teamA = a;
            this.teamB = b;
            this.match = m;
            this.matchPlayers.add(a);
            this.matchPlayers.add(b);
        }

        void movePlayers(){
            for(Team teams : matchPlayers){
                for(Player players : teams){

                }
            }
            
        }

    }

class Team {

    Player[] players;
    boolean win;


    Team(Player[] p){
        this.players = p;
        this.win = false;

    }

    public boolean getWin(){
        return this.win;
    }

    public void setWin(boolean w){
        this.win = w;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void drawPlayers(Graphics g){

        for (Player p : players) {
        p.draw(g);
        }
    }

    public void movePlayers(){
        for(Player p : players){
            
        }
    }


}
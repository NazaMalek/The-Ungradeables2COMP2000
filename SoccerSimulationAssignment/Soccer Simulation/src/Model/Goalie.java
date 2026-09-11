import java.awt.Color;
import java.awt.Graphics;

/**
 * Goalie subclass extending Player.
 * Tracks saves and defines defensive shot-stopping mechanics.
 */
public class Goalie extends Player {

    private int saves;
    private int reachRadius;

    public Goalie(int x, int y, int jerseyNumber, Color colour) {
        super(x, y, jerseyNumber, colour);
        this.saves = 0;
        this.reachRadius = 25;
    }

    public Goalie(int x, int y, int jerseyNumber, Color colour, int reachRadius) {
        super(x, y, jerseyNumber, colour);
        this.saves = 0;
        this.reachRadius = reachRadius;
    }

    public int getSaves() {
        return saves;
    }

    public void recordSave() {
        this.saves++;
    }

    public int getReachRadius() {
        return reachRadius;
    }

    public void setReachRadius(int reachRadius) {
        this.reachRadius = reachRadius;
    }

    public boolean attemptSave(double shotSpeed) {
        if (shotSpeed < (reachRadius * 2.0)) {
            recordSave();
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public String toString() {
        return "Goalie [Saves: " + saves + ", Reach: " + reachRadius + "]";
    }
}

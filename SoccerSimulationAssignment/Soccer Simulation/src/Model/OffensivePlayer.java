import java.awt.Color;
import java.awt.Graphics;

/**
 * OffensivePlayer subclass extending Player.
 * Tracks offensive metrics, stamina reserves, and shot power.
 */
public class OffensivePlayer extends Player {

    private int goals;
    private int shotPower;
    private double stamina;

    public OffensivePlayer(int x, int y, int jerseyNumber, Color colour) {
        super(x, y, jerseyNumber, colour);
        this.goals = 0;
        this.shotPower = 15;
        this.stamina = 100.0;
    }

    public OffensivePlayer(int x, int y, int jerseyNumber, Color colour, int shotPower) {
        super(x, y, jerseyNumber, colour);
        this.goals = 0;
        this.shotPower = shotPower;
        this.stamina = 100.0;
    }

    public int getGoals() {
        return goals;
    }

    public void scoreGoal() {
        this.goals++;
    }

    public int getShotPower() {
        return shotPower;
    }

    public void setShotPower(int shotPower) {
        this.shotPower = shotPower;
    }

    public double getStamina() {
        return stamina;
    }

    public void setStamina(double stamina) {
        this.stamina = Math.max(0.0, Math.min(100.0, stamina));
    }

    public int powerShoot() throws StaminaExhaustedException {
        if (stamina < 15.0) {
            throw new StaminaExhaustedException("Player is too exhausted to take a power shot!");
        }
        setStamina(stamina - 15.0);
        return shotPower * 2;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public String toString() {
        return "OffensivePlayer [Goals: " + goals + ", Power: " + shotPower + ", Stamina: " + String.format("%.1f", stamina) + "]";
    }
}

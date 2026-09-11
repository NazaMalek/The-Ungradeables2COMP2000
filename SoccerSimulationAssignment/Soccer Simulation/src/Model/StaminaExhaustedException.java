/**
 * Checked exception thrown when an offensive player attempts a high-intensity action
 * without sufficient stamina reserves.
 */
public class StaminaExhaustedException extends Exception {
    public StaminaExhaustedException(String message) {
        super(message);
    }
}

package Model;

/**
 * Observer pattern: anything that needs to react to match events (a goal,
 * full time) implements this instead of Match reaching out and calling it
 * directly. Match doesn't need to know who is listening or how many
 * listeners there are - it just announces what happened.
 *
 * We chose Observer here rather than, say, a Strategy for the AI behaviours
 * because this problem is specifically about one-to-many notification of a
 * state change (a goal happening) to parts of the system that only care
 * about "did something happen", not about how the match is simulated frame
 * to frame. A Strategy pattern would fit the player-movement decisions
 * instead (chase ball vs hold position vs guard goal), which is a natural
 * next refactor but out of scope for this submission.
 */
public interface MatchListener {
    void onGoalScored(String scoringTeam, int homeScore, int awayScore);

    void onMatchEnd(int homeScore, int awayScore);

    // Default method: tackles only matter to some listeners (MatchStats
    // counts them, the pitch doesn't draw anything for them), so giving
    // this a default empty body means SoccerPitch didn't have to change
    // when the event was added.
    default void onTackle(String team) {
    }
}

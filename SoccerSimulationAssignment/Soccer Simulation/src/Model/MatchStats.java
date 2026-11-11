package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Second observer. Keeps a running record of match events and prints a
 * summary at full time. The summary is built with the Collectors API:
 * groupingBy with a counting() downstream collector turns the raw event
 * lists into per-team totals.
 *
 * This class also proves the point of the Observer refactor: Match did not
 * change to support it beyond registering one extra listener. The same
 * events SoccerPitch uses for display, this class uses for statistics.
 */
public class MatchStats implements MatchListener {

    private final List<String> goals = new ArrayList<>();
    private final List<String> tackles = new ArrayList<>();

    @Override
    public void onGoalScored(String scoringTeam, int homeScore, int awayScore) {
        goals.add(scoringTeam);
    }

    @Override
    public void onTackle(String team) {
        tackles.add(team);
    }

    @Override
    public void onMatchEnd(int homeScore, int awayScore) {
        Map<String, Long> goalsPerTeam = goals.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> tacklesPerTeam = tackles.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("=== MATCH SUMMARY ===");
        System.out.println("Final score: HOME " + homeScore + " - " + awayScore + " AWAY");
        System.out.println("Goals: " + goalsPerTeam);
        System.out.println("Tackles won: " + tacklesPerTeam);

        // Reset so a rematch on the same Match object starts clean
        goals.clear();
        tackles.clear();
    }
}

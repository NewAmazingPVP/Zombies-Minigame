package mc.minigame.game;

import mc.minigame.Zombies;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class DisplayBoard {
    private ScoreboardManager scoreboardManager;
    private Scoreboard scoreboard;

    private Zombies mainPlugin;
    PlayerMoney playerMoney = new PlayerMoney();
    private Objective objective;

    public void board(Player player) {
        scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard board = scoreboardManager.getNewScoreboard();
        objective = board.registerNewObjective("Coins", "dummy", "Zombies Minigame");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Coins
        int coins = playerMoney.getCoins(player); // Example: Get the number of coins the player has
        Score coinsScore = objective.getScore("Coins: ");
        coinsScore.setScore(coins);

        // Time
        int time = gameTime.getTime(player); // Example: Get the time remaining in the game
        Score timeScore = objective.getScore("Time: ");
        timeScore.setScore(time);

        // Add more values as needed

        player.setScoreboard(board);
    }

    public void updateScores() {
        // Update the scores dynamically (e.g., based on game state, player stats, etc.)
        Objective objective = scoreboard.getObjective("example");
        if (objective != null) {
            objective.getScore("Your Coins: ").setScore(4);
            objective.getScore("Score 2").setScore(5);
            objective.getScore("Score 3").setScore(6);
        }
    }

    private static class gameTime {
        public static int getTime(Player player) {
            // Example implementation
            return 120; // Return a sample value for time remaining
        }
    }
}

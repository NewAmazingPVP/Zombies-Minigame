package mc.minigame.game;

import mc.minigame.Zombies;
import mc.minigame.listener.Shoot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class DisplayBoard {
    private static ScoreboardManager scoreboardManager;
    private static Objective objective;
    private static Zombies zombies;
    private static BukkitRunnable runnable; // Declare the variable here

    public DisplayBoard(Zombies zombies) {
        this.zombies = zombies;
    }

    public static void board(Player player) {
        scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard board = scoreboardManager.getNewScoreboard();
        objective = board.registerNewObjective("Zombies Minigame", "Zombies Minigame", ChatColor.DARK_PURPLE + "Zombies Minigame");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        runnable = new BukkitRunnable() { // Initialize the variable here
            @Override
            public void run() {
                try {
                    int coins = PlayerMoney.getCoins(player); // Get the number of coins the player has
                    Score coinsScore = objective.getScore(ChatColor.YELLOW + "Coins: ");
                    coinsScore.setScore(coins);

                    int level = 1;
                    Score timeScore = objective.getScore(ChatColor.AQUA + "Level: ");
                    timeScore.setScore(level);

                    player.setScoreboard(board);
                } catch (Exception e) {
                    this.cancel();
                }
            }
        };
        runnable.runTaskTimer(zombies, 0L, 1L);
    }

    /*public static void updateScores(Player player) {
        Objective objective = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
        if (objective != null) {
            int coins = PlayerMoney.getCoins(player); // Get the number of coins the player has
            Score coinsScore = objective.getScore(ChatColor.YELLOW + "Coins: " + ChatColor.GOLD + coins);
            coinsScore.setScore(0);

            int level = 1;
            Score timeScore = objective.getScore(ChatColor.AQUA + "Level: " + ChatColor.GREEN + level);
            timeScore.setScore(0);
        }
    }*/
}

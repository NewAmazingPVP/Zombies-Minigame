package mc.minigame.game;

import mc.minigame.listener.Shoot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class DisplayBoard {
    private static ScoreboardManager scoreboardManager;
    private static Objective objective;

    public static void board(Player player) {
        scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard board = scoreboardManager.getNewScoreboard();
        objective = board.registerNewObjective("Zombies Minigame", "Zombies Minigame", ChatColor.DARK_PURPLE + "Zombies Minigame");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        int coins = PlayerMoney.getCoins(player); // Get the number of coins the player has
        Score coinsScore = objective.getScore(ChatColor.YELLOW + "Coins ");
        coinsScore.setScore(coins);

        int level = 1;
        Score timeScore = objective.getScore(ChatColor.AQUA + "Level ");
        timeScore.setScore(level);

        player.setScoreboard(board);
    }

    public static void updateScores(Player player) {
        int coins = PlayerMoney.getCoins(player); // Get the number of coins the player has
        int ammo = Shoot.countArrowsInInventory(player);

        Objective objective = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
        if (objective != null) {
            Score coinsScore = objective.getScore(ChatColor.YELLOW + "Coins ");
            coinsScore.setScore(coins);

            //Score ammoScore = objective.getScore("Ammo: ");
            //ammoScore.setScore(ammo);
        }
    }
}

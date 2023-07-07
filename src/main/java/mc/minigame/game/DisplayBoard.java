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

                    int level = Rounds.getRounds();
                    Score timeScore = objective.getScore(ChatColor.AQUA + "Level: ");
                    timeScore.setScore(level);

                    int ammo = Shoot.countArrowsInInventory(player);
                    Score ammoCount = objective.getScore(ChatColor.AQUA + "Ammo: ");
                    ammoCount.setScore(ammo);

                    int seconds = (int) Rounds.getTimeLeft();
                    Score timeLeft = objective.getScore(ChatColor.GREEN + "Time Left: ");
                    timeLeft.setScore(seconds);

                    player.setScoreboard(board);
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "Your scoreboard encountered a problem and will not be updated until you relog");
                    player.sendTitle(ChatColor.RED + "Your scoreboard encountered a problem and will not be updated until you relog", "" );
                    this.cancel();
                }
            }
        };
        runnable.runTaskTimer(zombies, 0L, 1L);
    }
}

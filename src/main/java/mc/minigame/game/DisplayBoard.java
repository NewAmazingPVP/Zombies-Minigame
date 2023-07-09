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
    private static BukkitRunnable runnable;

    public DisplayBoard(Zombies zombies) {
        this.zombies = zombies;
    }

    public static void board() {


        runnable = new BukkitRunnable() { // Initialize the variable here
            @Override
            public void run() {
                for (Player p : zombies.getServer().getOnlinePlayers()) {
                    try {
                        scoreboardManager = Bukkit.getScoreboardManager();
                        Scoreboard board = scoreboardManager.getNewScoreboard();
                        objective = board.registerNewObjective("Zombies Minigame" + p, "Zombies Minigame" + p, ChatColor.DARK_PURPLE + "Zombies Minigame");
                        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

                        int coins = PlayerMoney.getCoins(p); // Get the number of coins the player has
                        Score coinsScore = objective.getScore(ChatColor.YELLOW + "Coins: ");
                        coinsScore.setScore(coins);

                        int level = Rounds.getRounds();
                        Score timeScore = objective.getScore(ChatColor.AQUA + "Level: ");
                        timeScore.setScore(level);

                        int ammo = Shoot.countArrowsInInventory(p);
                        Score ammoCount = objective.getScore(ChatColor.BLUE + "Ammo: ");
                        ammoCount.setScore(ammo);

                        int seconds = Rounds.getTimeLeft();
                        Score timeLeft = objective.getScore(ChatColor.GREEN + "Time Left: ");
                        timeLeft.setScore(seconds);

                        int zombieCount = ZombieCount.getTotalZombieCount();
                        Score count = objective.getScore(ChatColor.DARK_RED + "Zombies: ");
                        count.setScore(zombieCount);

                        p.setScoreboard(board);

                    } catch (Exception e) {
                        p.sendMessage(ChatColor.RED + "Your scoreboard encountered a problem and will not be updated until you relog");
                        p.sendTitle(ChatColor.RED + "Your scoreboard encountered a problem and will not be updated until you relog", "" );
                    }
                }
            }
        };
        runnable.runTaskTimer(zombies, 0L, 1L);
    }
}

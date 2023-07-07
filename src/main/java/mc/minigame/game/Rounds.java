package mc.minigame.game;

import mc.minigame.Zombies;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Rounds {
    private static Zombies zombies;
    private static long roundEndTime; // Added variable to store the scheduled time of the round's end

    public Rounds(Zombies zombies) {
        this.zombies = zombies;
    }

    private static int round = 1;
    public static int maxRounds;

    public static int getRounds() {
        return round;
    }

    public static void startRound() {
        Location loc1 = new Location(Bukkit.getWorld("world"), 50.0, -21.0, -120.0);
        Location loc2 = new Location(Bukkit.getWorld("world"), -40.0, -4.0, 10.0);
        Spawn.zombies(5 + (2 * round), loc1, loc2, (0.23 + (0.02*round)), (2.0 + (0.2*round)), (20.0 + (2*round)), (0.0 + (0.01*round)), (4.0 + (0.2*round)), (0.0 + (0.01*round)));

        long delay = 20 * 15 + (20 * 15 * round);
        roundEndTime = System.currentTimeMillis() + delay;

        BukkitRunnable roundTask = new BukkitRunnable() {
            @Override
            public void run() {
                endRound();
            }
        };
        roundTask.runTaskLater(zombies, delay);
    }

    private static void endRound() {
        // Check if there are more rounds to play or if the game is over
        if (round <= maxRounds) {
            // Start the next round after a delay if desired
            Bukkit.getScheduler().runTaskLater(zombies, () -> startRound(), 20);
            round += 1;
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle(ChatColor.GOLD + "YOU WON", "");
                round = 1;
                maxRounds = 0;
            }
        }
    }

    public static long getTimeLeft() {
        long currentTime = System.currentTimeMillis();
        long timeLeft = roundEndTime - currentTime;
        return Math.max(timeLeft / 1000, 0); // Convert milliseconds to seconds
    }
}

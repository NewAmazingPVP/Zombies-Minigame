package mc.minigame.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static mc.minigame.Zombies.zombies;

public class Rounds {
    private static long roundEndTime;
    private static int round = 1;
    public static int maxRounds;

    public static boolean gameOn = false;

    public static int getRounds() {
        return round;
    }

    public static void startRound() {
        ZombieCount.killAllZombies();
        gameOn = true;
        Location loc1 = new Location(Bukkit.getWorld("world"), 50.0, -21.0, -120.0);
        Location loc2 = new Location(Bukkit.getWorld("world"), -40.0, -4.0, 10.0);
        Spawn.zombies(5 + (2 * round), loc1, loc2, (0.23 + (0.02*round)), (2.0 + (0.2*round)), (20.0 + (2*round)), (0.0 + (0.01*round)), (4.0 + (0.2*round)), (0.0 + (0.01*round)));

        int delay = 20 * 15 + (20 * 15 * round);
        roundEndTime = System.currentTimeMillis() + delay*50;

        BukkitRunnable roundTask = new BukkitRunnable() {
            @Override
            public void run() {
                endRound();
            }
        };
        roundTask.runTaskLater(zombies, delay);
    }

    public static void endRound() {
        // Check if there are more rounds to play or if the game is over
        if (round < maxRounds) {
            for (Player p : zombies.getServer().getOnlinePlayers()) {
                p.sendTitle(ChatColor.GREEN + "Round over", "");
            }
            Bukkit.getScheduler().runTaskLater(zombies, () -> startRound(), 20);
            round += 1;
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle(ChatColor.GOLD + "YOU WON", "");
                round = 1;
                maxRounds = 0;
            }
            ZombieCount.killAllZombies();
            gameOn = false;
        }
    }

    public static int getTimeLeft() {
        long currentTime = System.currentTimeMillis();
        long timeLeft = roundEndTime - currentTime;
        return (int) Math.max(timeLeft / 1000, 0); // Convert milliseconds to seconds
    }

    public static int getMaxRounds(){
        return maxRounds;
    }

    public static int round(){
        return round;
    }

}

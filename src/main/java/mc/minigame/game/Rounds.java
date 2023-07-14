package mc.minigame.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import mc.minigame.variables.Loc;

import static mc.minigame.Zombies.zombies;

public class Rounds {
    public static long roundEndTime;
    private static int round = 1;
    public static int maxRounds;
    public static boolean gameOn = false;

    public static List<Player> deadPlayers = new ArrayList<>();
    public static Map<Player, ItemStack[]> savedInventories = new HashMap<>();

    private static BukkitRunnable roundTask;

    public static int getRounds() {
        return round;
    }

    public static void startRound() {
        gameOn = true;
        Iterator<Player> iterator = deadPlayers.iterator();
        while (iterator.hasNext()) {
            Player p = iterator.next();
            p.teleport(Loc.startLoc);
            iterator.remove();
        }
        ZombieCount.killAllZombies();
        Spawn.zombies(5 + (2 * round), Loc.mapEdgeloc1, Loc.mapEdgeloc2, (0.23 + (0.02 * round)), (2.0 + (0.2 * round)), (20.0 + (2 * round)), (0.0 + (0.01 * round)), (4.0 + (0.2 * round)), (0.0 + (0.01 * round)));

        int delay = 20 * 15 + (20 * 15 * round);
        roundEndTime = System.currentTimeMillis() + delay * 50;

        roundTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (gameOn) {
                    endRound();
                }
            }
        };
        roundTask.runTaskLater(zombies, delay);
    }

    public static void endRound() {
        // Check if there are more rounds to play or if the game is over
        boolean allDead = new HashSet<>(deadPlayers).containsAll(zombies.getServer().getOnlinePlayers());
        if (round < maxRounds && !allDead) {
            int nextRound = round + 1;
            for (Player p : zombies.getServer().getOnlinePlayers()) {
                p.sendTitle(ChatColor.GREEN + "Round " + nextRound, "");
            }
            startRound();
            round++;
        } else if (allDead) {
            gameOn = false;
            for (Player p : zombies.getServer().getOnlinePlayers()) {
                p.sendTitle(ChatColor.RED + "YOU LOST!", "");
                p.teleport(Loc.lobby);
                saveInventory(p); // Save player inventory
                p.getInventory().clear();
                PlayerMoney.setCoins(p, 0);
            }
            round = 1;
            maxRounds = 0;
            roundEndTime = 0;
            ZombieCount.killAllZombies();
        } else {
            gameOn = false;
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle(ChatColor.GOLD + "YOU WON", "");
                player.teleport(Loc.lobby);
                saveInventory(player); // Save player inventory
                player.getInventory().clear();
                PlayerMoney.setCoins(player, 0);
            }
            round = 1;
            maxRounds = 0;
            roundEndTime = 0;
            ZombieCount.killAllZombies();
        }
    }

    public static int getTimeLeft() {
        long currentTime = System.currentTimeMillis();
        long timeLeft = roundEndTime - currentTime;
        return (int) Math.max(timeLeft / 1000, 0); // Convert milliseconds to seconds
    }

    public static int getMaxRounds() {
        return maxRounds;
    }

    public static int round() {
        return round;
    }

    public static void pauseRound() {
        if (gameOn) {
            gameOn = false;
            for (Player player : Bukkit.getOnlinePlayers()) {
                saveInventory(player);
                player.setGameMode(GameMode.SPECTATOR);
                player.sendTitle(ChatColor.YELLOW + "Round Paused", "");
            }
            if (roundTask != null) {
                roundTask.cancel();
            }
        }
    }

    public static void unpauseRound() {
        if (!gameOn) {
            gameOn = true;
            for (Player player : Bukkit.getOnlinePlayers()) {
                restoreInventory(player);
                player.setGameMode(GameMode.SURVIVAL);
                player.sendTitle(ChatColor.GREEN + "Round Resumed", "");
            }
            if (roundTask != null) {
                long currentTime = System.currentTimeMillis();
                long remainingTime = roundEndTime - currentTime;
                if (remainingTime > 0) {
                    roundTask = new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (gameOn) {
                                endRound();
                            }
                        }
                    };
                    roundTask.runTaskLater(zombies, remainingTime / 50);
                }
            }
        }
    }

    private static void saveInventory(Player player) {
        savedInventories.put(player, player.getInventory().getContents().clone());
        player.getInventory().clear();
    }

    private static void restoreInventory(Player player) {
        ItemStack[] savedInventory = savedInventories.get(player);
        if (savedInventory != null) {
            player.getInventory().setContents(savedInventory.clone());
            savedInventories.remove(player);
        }
    }
}

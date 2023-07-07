package mc.minigame.game;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerMoney {
    private static HashMap<Player, Integer> playerMap = new HashMap<>();


    public static int getCoins(Player player) {
        return playerMap.getOrDefault(player, 0);
    }

    public static void addCoins(Player player, int amount) {
        int currentCoins = getCoins(player);
        playerMap.put(player, currentCoins + amount);
    }

    public static void removeCoins(Player player, int amount) {
        int currentCoins = getCoins(player);
        if (currentCoins > amount) {
            playerMap.put(player, currentCoins - amount);
        } else {
            playerMap.remove(player);
        }
    }

    public static void setCoins(Player player, int amount) {
        if (amount > 0) {
            playerMap.put(player, amount);
        } else {
            playerMap.remove(player);
        }
    }

    public static void clearCoins() {
        playerMap.clear();
    }
}

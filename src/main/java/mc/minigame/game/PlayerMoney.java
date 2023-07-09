package mc.minigame.game;

import org.bukkit.entity.Player;

import static mc.minigame.Zombies.economy;


public class PlayerMoney {

    public static int getCoins(Player player) {
        return (int) economy.getBalance(player);
    }

    public static void addCoins(Player player, int amount) {
        economy.depositPlayer(player, amount);
    }

    public static void removeCoins(Player player, int amount) {
        int currentCoins = (int) economy.getBalance(player);
        if (currentCoins > amount) {
            economy.withdrawPlayer(player, amount);
        } else {
            economy.withdrawPlayer(player, currentCoins);
        }
    }

    public static void setCoins(Player player, int amount) {
        double currentBalance = economy.getBalance(player);
        double difference = amount - currentBalance;

        if (difference > 0) {
            economy.depositPlayer(player, difference);
        } else if (difference < 0) {
            economy.withdrawPlayer(player, -difference);
        }
    }

    /*public static void clearCoins() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        economy = rsp.getProvider();
        for (Player player : playerMap.keySet()) {
            playerMap.remove(player);
            economy.withdrawPlayer(player, economy.getBalance(player));
        }
    }*/
}

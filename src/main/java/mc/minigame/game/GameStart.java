package mc.minigame.game;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static mc.minigame.Zombies.zombies;

public class GameStart {

    public static void onStart() {
        for (Player p : zombies.getServer().getOnlinePlayers()) {
            PlayerMoney.setCoins(p, 0);
            Location loc = new Location(p.getWorld(), -38.0, -19.0, -58.0);
            p.teleport(loc);
            p.sendTitle(ChatColor.DARK_PURPLE + "Game Started. Good Luck!", "");
            p.getInventory().clear();
            ItemStack weapon = new ItemStack(Material.STONE_HOE, 1);
            p.getInventory().addItem(weapon);
            ItemStack ammo = new ItemStack(Material.ARROW, 64);
            p.getInventory().addItem(ammo);
            p.setGameMode(GameMode.SURVIVAL);
        }
    }

    public static void start(Integer amount){
        Rounds.maxRounds = amount;
        Rounds.startRound();
    }
}

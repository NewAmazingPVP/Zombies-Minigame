package mc.minigame.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WaterDamage implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();

        if (to.getBlock().getType() == Material.WATER) {
            player.damage(2.0);
            player.sendMessage(ChatColor.DARK_RED + "WARNING WATER RADIOACTIVE!");
        }
    }
}
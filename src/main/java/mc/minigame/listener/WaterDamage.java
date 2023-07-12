package mc.minigame.listener;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WaterDamage implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        World world = player.getWorld();

        if (to.getBlock().getType() == Material.WATER && to.getBlock().getRelative(0, -1, 0).getType() != Material.WATER) {
            player.damage(2.0);
            player.sendMessage("Damage!");
        }
    }
}
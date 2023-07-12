package mc.minigame.listener;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class WaterDamage implements Listener {

    @EventHandler
    public void onPlayerMove(EntityToggleSwimEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            player.damage(2.0);
            player.sendMessage("Hello mother fucker");
        }
    }
}
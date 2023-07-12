package mc.minigame.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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
            player.sendMessage("[\"\",{\"text\":\"\\u26a0 \\u2622 \",\"color\":\"dark_red\"},{\"text\":\"WARNING WATER RADIOACTIVE\",\"bold\":true,\"color\":\"dark_red\"},{\"text\":\" \\u2622 \\u26a0\",\"color\":\"dark_red\"}]");
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        World world = player.getWorld();

        if (to.getBlock().getType() == Material.WATER) {
            player.damage(2.0);
            player.sendMessage("[\"\",{\"text\":\"\\u26a0 \",\"color\":\"dark_red\"},{\"text\":\"WARNING WATER RADIOACTIVE!\",\"bold\":true,\"color\":\"dark_red\"},{\"text\":\" \\u26a0\",\"color\":\"dark_red\"}]");
        }
    }
}
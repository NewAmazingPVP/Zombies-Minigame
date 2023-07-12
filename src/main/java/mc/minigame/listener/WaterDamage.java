package mc.minigame.listener;

import mc.minigame.game.PlayerMoney;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import static mc.minigame.Zombies.zombies;

public class WaterDamage implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        World world = player.getWorld();

        if (to.getBlock().getType() == Material.WATER && to.getBlock().getRelative(0, -1, 0).getType() != Material.WATER) {
            // Player stepped into water
            player.damage(2.0);
            player.sendMessage("You took damage from stepping into water!");
        }
    }
}
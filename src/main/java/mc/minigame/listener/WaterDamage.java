package mc.minigame.listener;

import mc.minigame.game.PlayerMoney;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import static mc.minigame.Zombies.zombies;

public class WaterDamage implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        if (event.getItem().getItemStack().getType() == Material.GOLD_INGOT) {
            Player player = event.getPlayer();
            Integer amount = event.getItem().getItemStack().getAmount();
            String message = ChatColor.GOLD + "+" + ChatColor.BOLD + amount + " Pickup Coins";
            TextComponent textComponent = new TextComponent(message);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, textComponent);
            PlayerMoney.addCoins(player, amount);

            Bukkit.getScheduler().runTaskLater(zombies, () -> {
                ItemStack goldIngot = new ItemStack(Material.GOLD_INGOT, amount);
                player.getInventory().removeItem(goldIngot);
            }, 1);
        }
    }
}
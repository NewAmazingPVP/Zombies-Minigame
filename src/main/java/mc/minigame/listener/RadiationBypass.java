package mc.minigame.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class RadiationBypass implements Listener {

    public List<Material> armorList = Arrays.asList(
            Material.NETHERITE_CHESTPLATE,
            Material.NETHERITE_HELMET,
            Material.NETHERITE_LEGGINGS,
            Material.NETHERITE_BOOTS
    );

    @EventHandler
    public void onPotionChange(EntityPotionEffectEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        if (event.getNewEffect() == null || event.getNewEffect().getType() == PotionEffectType.POISON) {
            return;
        }

        List<ItemStack> armor = List.of(player.getInventory().getArmorContents());

        for (ItemStack item : armor) {
            if (item != null && armorList.contains(item.getType()) ) {
                event.setCancelled(true);
            }
        }
    }
}

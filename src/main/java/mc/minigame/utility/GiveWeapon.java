package mc.minigame.utility;

import mc.minigame.variables.Weapons;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class GiveWeapon {
    public static void weapon(Material gear, Player player) {
        ItemStack weapon = new ItemStack(gear);
        ItemMeta meta = weapon.getItemMeta();
        String weaponName = Weapons.getName(gear);
        meta.setDisplayName(weaponName);

        AttributeModifier attackSpeedModifier = new AttributeModifier(
                UUID.randomUUID(),
                "generic.attackSpeed",
                Weapons.getCooldownDuration(gear),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlot.HAND
        );
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeedModifier);

        AttributeModifier attackDamageModifier = new AttributeModifier(
                UUID.randomUUID(),
                "generic.attackDamage",
                Weapons.calculateDamageAmount(gear),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlot.HAND
        );
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackDamageModifier);

        weapon.setItemMeta(meta);

        player.getInventory().addItem(weapon);
    }
}

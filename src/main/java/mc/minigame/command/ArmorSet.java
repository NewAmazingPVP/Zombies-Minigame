package mc.minigame.command;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import mc.minigame.variables.Weapons;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

public class ArmorSet implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("giveset")) {
            if (args.length == 1) {
                try {
                    String armorType = args[0];
                    if (armorType.equalsIgnoreCase("leather")) {
                        for (Material stack : Weapons.getLeatherArmorTypes()) {
                            Player player = (Player) sender;
                            ItemStack armor = new ItemStack(stack);
                            ItemMeta meta = armor.getItemMeta();
                            if (meta != null) {
                                // Add an armor attribute modifier to the armor
                                meta.addAttributeModifier(
                                        Attribute.GENERIC_ARMOR, new AttributeModifier(
                                                UUID.randomUUID(),
                                                "generic.armor",
                                                20.0,
                                                AttributeModifier.Operation.ADD_NUMBER,
                                                EquipmentSlot.CHEST
                                        )
                                );
                                armor.setItemMeta(meta);

                                // Give the armor to the player
                                player.getInventory().addItem(armor);
                            }
                        }
                    }

                } catch (NumberFormatException e) {
                    sender.sendMessage("Invalid amount specified.");
                }
            }

            return true;
        }

        return false;
    }
}

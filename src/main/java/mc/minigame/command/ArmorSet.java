package mc.minigame.command;

import mc.minigame.variables.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ArmorSet implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("giveset")) {
            if (args.length == 1) {
                try {
                    String armorType = args[0];
                    if (armorType.equalsIgnoreCase("leather")) {
                        for (Material stack : LeatherArmor.getLeatherArmorTypes()) {
                            Player player = (Player) sender;
                            ItemStack armor = new ItemStack(stack);
                            ItemMeta meta = armor.getItemMeta();
                            meta.setDisplayName(ChatColor.BLUE + "Leather Juggernaut");
                            if (meta != null) {
                                // Add an armor attribute modifier to the armor
                                meta.addAttributeModifier(
                                        Attribute.GENERIC_ARMOR, new AttributeModifier(
                                                UUID.randomUUID(),
                                                "generic.armor",
                                                LeatherArmor.defensePoints(stack),
                                                AttributeModifier.Operation.ADD_NUMBER,
                                                LeatherArmor.slot(stack)
                                        )
                                );
                                armor.setItemMeta(meta);

                                // Give the armor to the player
                                player.getInventory().addItem(armor);
                            }
                        }
                    } else if (armorType.equalsIgnoreCase("diamond")) {
                        for (Material stack : DiamondArmor.getDiamondArmorTypes()) {
                            Player player = (Player) sender;
                            ItemStack armor = new ItemStack(stack);
                            ItemMeta meta = armor.getItemMeta();
                            meta.setDisplayName(ChatColor.DARK_RED + "Assassin Suit");
                            if (meta != null) {
                                // Add an armor attribute modifier to the armor
                                meta.addAttributeModifier(
                                        Attribute.GENERIC_ARMOR, new AttributeModifier(
                                                UUID.randomUUID(),
                                                "generic.armor",
                                                DiamondArmor.defensePoints(stack),
                                                AttributeModifier.Operation.ADD_NUMBER,
                                                DiamondArmor.slot(stack)
                                        )
                                );
                                armor.setItemMeta(meta);

                                // Give the armor to the player
                                player.getInventory().addItem(armor);
                            }
                        }
                    } else if (armorType.equalsIgnoreCase("netherite")) {
                        for (Material stack : NetheriteArmor.getNetheriteArmorTypes()) {
                            Player player = (Player) sender;
                            ItemStack armor = new ItemStack(stack);
                            ItemMeta meta = armor.getItemMeta();
                            meta.setDisplayName(ChatColor.MAGIC + "Netherite Juggernaut");
                            meta.setLore(Collections.singletonList(ChatColor.DARK_PURPLE + "RADIATION PROOF IF FULL SET"));
                            if (meta != null) {
                                // Add an armor attribute modifier to the armor
                                meta.addAttributeModifier(
                                        Attribute.GENERIC_ARMOR, new AttributeModifier(
                                                UUID.randomUUID(),
                                                "generic.armor",
                                                NetheriteArmor.defensePoints(stack),
                                                AttributeModifier.Operation.ADD_NUMBER,
                                                NetheriteArmor.slot(stack)
                                        )
                                );
                                armor.setItemMeta(meta);

                                // Give the armor to the player
                                player.getInventory().addItem(armor);
                            }
                        }
                    } else if (armorType.equalsIgnoreCase("iron")) {
                        for (Material stack : IronArmor.getIronArmorTypes()) {
                            Player player = (Player) sender;
                            ItemStack armor = new ItemStack(stack);
                            ItemMeta meta = armor.getItemMeta();
                            meta.setDisplayName(ChatColor.GRAY + "Ironman Armor");
                            if (meta != null) {
                                // Add an armor attribute modifier to the armor
                                meta.addAttributeModifier(
                                        Attribute.GENERIC_ARMOR, new AttributeModifier(
                                                UUID.randomUUID(),
                                                "generic.armor",
                                                IronArmor.defensePoints(stack),
                                                AttributeModifier.Operation.ADD_NUMBER,
                                                IronArmor.slot(stack)
                                        )
                                );
                                armor.setItemMeta(meta);

                                // Give the armor to the player
                                player.getInventory().addItem(armor);
                            }
                        }
                    } else if (armorType.equalsIgnoreCase("gold")) {
                        for (Material stack : GoldenArmor.getGoldenArmorTypes()) {
                            Player player = (Player) sender;
                            ItemStack armor = new ItemStack(stack);
                            ItemMeta meta = armor.getItemMeta();
                            meta.setDisplayName(ChatColor.GOLD + "King's Wear");
                            if (meta != null) {
                                // Add an armor attribute modifier to the armor
                                meta.addAttributeModifier(
                                        Attribute.GENERIC_ARMOR, new AttributeModifier(
                                                UUID.randomUUID(),
                                                "generic.armor",
                                                GoldenArmor.defensePoints(stack),
                                                AttributeModifier.Operation.ADD_NUMBER,
                                                GoldenArmor.slot(stack)
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

package mc.minigame.command;

import mc.minigame.variables.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static org.bukkit.inventory.ItemFlag.*;

public class ArmorSet implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("giveset")) {
            if (args.length == 1) {
                try {
                    String armorType = args[0];
                    if (armorType.equalsIgnoreCase("leather")) {
                        List<ItemStack> leatherArmorContents = new ArrayList<>();

                        for (Material stack : LeatherArmor.getLeatherArmorTypes()) {
                            ItemStack armor = new ItemStack(stack);
                            ItemMeta meta = armor.getItemMeta();
                            meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);
                            meta.setDisplayName(ChatColor.BLUE + "Leather Juggernaut");

                            if (meta != null) {
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
                                leatherArmorContents.add(armor);
                            }
                        }

                        Player player = (Player) sender;
                        player.getInventory().setArmorContents(leatherArmorContents.toArray(new ItemStack[0]));
                    } else if (armorType.equalsIgnoreCase("diamond")) {
                        List<ItemStack> diamondArmorContents = new ArrayList<>();

                        for (Material stack : DiamondArmor.getDiamondArmorTypes()) {
                            ItemStack armor = new ItemStack(stack);
                            ItemMeta meta = armor.getItemMeta();
                            meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);
                            meta.setDisplayName(ChatColor.DARK_RED + "Assassin Suit");

                            if (meta != null) {
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
                                diamondArmorContents.add(armor);
                            }
                        }

                        Player player = (Player) sender;
                        player.getInventory().setArmorContents(diamondArmorContents.toArray(new ItemStack[0]));
                    } else if (armorType.equalsIgnoreCase("netherite")) {
                        List<ItemStack> netheriteArmorContents = new ArrayList<>();

                        for (Material stack : NetheriteArmor.getNetheriteArmorTypes()) {
                            ItemStack armor = new ItemStack(stack);
                            ItemMeta meta = armor.getItemMeta();
                            meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);
                            meta.setDisplayName(ChatColor.MAGIC + "Netherite Juggernaut");
                            meta.setLore(Collections.singletonList(ChatColor.DARK_PURPLE + "RADIATION PROOF IF FULL SET"));

                            if (meta != null) {
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
                                netheriteArmorContents.add(armor);
                            }
                        }

                        Player player = (Player) sender;
                        player.getInventory().setArmorContents(netheriteArmorContents.toArray(new ItemStack[0]));
                    } else if (armorType.equalsIgnoreCase("iron")) {
                        List<ItemStack> armorContents = new ArrayList<>();

                        for (Material stack : IronArmor.getIronArmorTypes()) {
                            ItemStack armor = new ItemStack(stack);
                            ItemMeta meta = armor.getItemMeta();
                            meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);
                            meta.setDisplayName(ChatColor.GRAY + "Ironman Armor");
                            if (meta != null) {
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
                                armorContents.add(armor);
                            }
                            Player player = (Player) sender;
                            player.getInventory().setArmorContents(armorContents.toArray(new ItemStack[0]));
                        }
                    } else if (armorType.equalsIgnoreCase("gold")) {
                        List<ItemStack> goldArmorContents = new ArrayList<>();

                        for (Material stack : GoldenArmor.getGoldenArmorTypes()) {
                            ItemStack armor = new ItemStack(stack);
                            ItemMeta meta = armor.getItemMeta();
                            meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);
                            meta.setDisplayName(ChatColor.GOLD + "King's Wear");

                            if (meta != null) {
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
                                goldArmorContents.add(armor);
                            }
                        }

                        Player player = (Player) sender;
                        player.getInventory().setArmorContents(goldArmorContents.toArray(new ItemStack[0]));

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

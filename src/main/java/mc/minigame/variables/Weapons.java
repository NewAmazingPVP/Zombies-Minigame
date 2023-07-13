package mc.minigame.variables;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Arrays;
import java.util.List;

public class Weapons {

    public static List<Material> weaponList = Arrays.asList(
            Material.WOODEN_HOE,
            Material.WOODEN_SHOVEL,
            Material.STONE_HOE,
            Material.STONE_SHOVEL,
            Material.SHEARS,
            Material.IRON_HOE,
            Material.IRON_SHOVEL,
            Material.GOLDEN_HOE,
            Material.GOLDEN_SHOVEL,
            Material.GOLDEN_PICKAXE,
            Material.FLINT_AND_STEEL,
            Material.DIAMOND_HOE,
            Material.DIAMOND_PICKAXE
    );


    public static double getCooldownDuration(Material weaponType) {
        switch (weaponType) {
            case WOODEN_HOE: // Pistol
                return 0.7;
            case WOODEN_SHOVEL: // Pistol I
                return 0.5;
            case STONE_HOE: // Supreme Pistol
                return 0.4;
            case STONE_SHOVEL: // Rifle
                return 0.2;
            case SHEARS: // Superb Rifle
                return 0.2;
            case IRON_HOE: // Shotgun
                return 1.4;
            case IRON_SHOVEL: // Ultimate Shotgun
                return 1.0;
            case GOLDEN_HOE: // Rocket Launcher
                return 2.0;
            case GOLDEN_SHOVEL: // Supreme Rocket Launcher
                return 1.5;
            case GOLDEN_PICKAXE: // Sniper
                return 1.5;
            case FLINT_AND_STEEL: // Flamethrower
                return 0.1;
            case DIAMOND_HOE: // Venerable Gun
                return 0.9;
            case DIAMOND_PICKAXE: // Superb Venerable Gun
                return 1.5;
            default:
                return 0.0;
        }
    }

    public static double calculateDamageAmount(Material weaponType) {
        switch (weaponType) {
            case WOODEN_HOE: // Pistol
                return 6.0;
            case WOODEN_SHOVEL: // Pistol I
                return 6.0;
            case STONE_HOE: // Supreme Pistol
                return 6.0;
            case STONE_SHOVEL: // Rifle
                return 6.0;
            case SHEARS: // Superb Rifle
                return 8.0;
            case IRON_HOE: // Shotgun
                return 4.5 * 10;
            case IRON_SHOVEL: // Ultimate Shotgun
                return 4.5 * 10;
            case GOLDEN_HOE: // Rocket Launcher
                return 15.0;
            case GOLDEN_SHOVEL: // Supreme Rocket Launcher
                return 20.0;
            case GOLDEN_PICKAXE: // Sniper
                return 20.0;
            case FLINT_AND_STEEL: // Flamethrower
                return 2.0;
            case DIAMOND_HOE: // Venerable Gun
                return 15.0;
            case DIAMOND_PICKAXE: // Superb Venerable Gun
                return 30.0;
            default:
                return 0.0;
        }
    }

    public static String getName(Material weaponType) {
        switch (weaponType) {
            case WOODEN_HOE:
                return ChatColor.GREEN + "Pistol";
            case WOODEN_SHOVEL:
                return ChatColor.YELLOW + "Pistol I";
            case STONE_HOE:
                return ChatColor.GOLD + "Supreme Pistol";
            case STONE_SHOVEL:
                return ChatColor.GREEN + "Rifle";
            case SHEARS:
                return ChatColor.YELLOW + "Superb Rifle";
            case IRON_HOE:
                return ChatColor.RED + "Shotgun";
            case IRON_SHOVEL:
                return ChatColor.DARK_RED + "Ultimate Shotgun";
            case GOLDEN_HOE:
                return ChatColor.LIGHT_PURPLE + "Rocket Launcher";
            case GOLDEN_SHOVEL:
                return ChatColor.DARK_PURPLE + "Supreme Rocket Launcher";
            case GOLDEN_PICKAXE:
                return ChatColor.AQUA + "Sniper";
            case FLINT_AND_STEEL:
                return ChatColor.RED + "Flamethrower";
            case DIAMOND_HOE:
                return ChatColor.BLUE + "Venerable Gun";
            case DIAMOND_PICKAXE:
                return ChatColor.DARK_BLUE + "Superb Venerable Gun";
            default:
                return ChatColor.GRAY + "Unknown Weapon";
        }
    }


    public Weapons() {
    }
}

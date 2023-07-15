package mc.minigame.variables;

import org.bukkit.ChatColor;
import org.bukkit.Material;

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
        return switch (weaponType) {
            case WOODEN_HOE -> // Pistol
                    6.0;
            case WOODEN_SHOVEL -> // Pistol I
                    6.0;
            case STONE_HOE -> // Supreme Pistol
                    6.0;
            case STONE_SHOVEL -> // Rifle
                    6.0;
            case SHEARS -> // Superb Rifle
                    8.0;
            case IRON_HOE -> // Shotgun
                    12.0;
            case IRON_SHOVEL -> // Ultimate Shotgun
                    16.0;
            case GOLDEN_HOE -> // Rocket Launcher
                    7.50;
            case GOLDEN_SHOVEL -> // Supreme Rocket Launcher
                    11.0;
            case GOLDEN_PICKAXE -> // Sniper
                    20.0;
            case FLINT_AND_STEEL -> // Flamethrower
                    2.0;
            case DIAMOND_HOE -> // Venerable Gun
                    15.0;
            case DIAMOND_PICKAXE -> // Superb Venerable Gun
                    30.0;
            default -> 0.0;
        };
    }

    public static String getName(Material weaponType) {
        return switch (weaponType) {
            case WOODEN_HOE -> ChatColor.GREEN + "Pistol";
            case WOODEN_SHOVEL -> ChatColor.YELLOW + "Pistol I";
            case STONE_HOE -> ChatColor.GOLD + "Supreme Pistol";
            case STONE_SHOVEL -> ChatColor.GREEN + "Rifle";
            case SHEARS -> ChatColor.YELLOW + "Superb Rifle";
            case IRON_HOE -> ChatColor.RED + "Shotgun";
            case IRON_SHOVEL -> ChatColor.DARK_RED + "Ultimate Shotgun";
            case GOLDEN_HOE -> ChatColor.LIGHT_PURPLE + "Rocket Launcher";
            case GOLDEN_SHOVEL -> ChatColor.DARK_PURPLE + "Supreme Rocket Launcher";
            case GOLDEN_PICKAXE -> ChatColor.AQUA + "Sniper";
            case FLINT_AND_STEEL -> ChatColor.RED + "Flamethrower";
            case DIAMOND_HOE -> ChatColor.BLUE + "Venerable Gun";
            case DIAMOND_PICKAXE -> ChatColor.DARK_BLUE + "Superb Venerable Gun";
            default -> ChatColor.GRAY + "Unknown Weapon";
        };
    }

    public static double explosionRadius(Material weaponType){
        return switch (weaponType) {
            case GOLDEN_HOE -> 2.5;
            case GOLDEN_SHOVEL -> 5.0;
            default -> 0.0;
        };
    }

    public Weapons() {
    }
}

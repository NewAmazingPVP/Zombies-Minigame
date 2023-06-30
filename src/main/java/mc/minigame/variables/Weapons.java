package mc.minigame.variables;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class Weapons {

    public List<Material> weaponList = Arrays.asList(
            Material.WOODEN_HOE,
            Material.STONE_HOE,
            Material.IRON_HOE,
            Material.GOLDEN_HOE,
            Material.DIAMOND_HOE,
            Material.NETHERITE_HOE
    );


    public double getCooldownDuration(Material weaponType) {
        // Add custom cooldown duration based on the weapon type
        switch (weaponType) {
            case WOODEN_HOE:
                return 0.5; // 0.5 seconds
            case STONE_HOE:
                return 0.6; // 0.6 seconds
            case IRON_HOE:
                return 0.7; // 0.7 seconds
            case GOLDEN_HOE:
                return 0.8; // 0.8 seconds
            case DIAMOND_HOE:
                return 0.9; // 0.9 seconds
            case NETHERITE_HOE:
                return 1.0; // 1.0 second
            default:
                return 0.0;
        }
    }

    public double calculateDamageAmount(Material weaponType) {
        // Add custom damage calculation based on the weapon type
        switch (weaponType) {
            case WOODEN_HOE:
                return 4.0;
            case STONE_HOE:
                return 5.0;
            case IRON_HOE:
                return 6.0;
            case GOLDEN_HOE:
                return 7.0;
            case DIAMOND_HOE:
                return 8.0;
            case NETHERITE_HOE:
                return 9.0;
            default:
                return 0.0;
        }
    }

    public Weapons(){



    }
}

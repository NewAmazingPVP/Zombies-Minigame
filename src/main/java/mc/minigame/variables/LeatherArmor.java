package mc.minigame.variables;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class LeatherArmor {

    public static List<Material> getLeatherArmorTypes() {
        return new ArrayList<>(List.of(
                Material.LEATHER_BOOTS,
                Material.LEATHER_LEGGINGS,
                Material.LEATHER_CHESTPLATE,
                Material.LEATHER_HELMET
        ));
    }


    public static double defensePoints(Material material) {
        return switch (material) {
            case LEATHER_BOOTS -> 1.0;
            case LEATHER_HELMET -> 1.0;
            case LEATHER_CHESTPLATE -> 1.0;
            case LEATHER_LEGGINGS -> 1.0;
            default -> 0.0;
        };
    }

    public static EquipmentSlot slot(Material material) {
        return switch (material) {
            case LEATHER_BOOTS -> EquipmentSlot.FEET;
            case LEATHER_HELMET -> EquipmentSlot.HEAD;
            case LEATHER_CHESTPLATE -> EquipmentSlot.CHEST;
            case LEATHER_LEGGINGS -> EquipmentSlot.LEGS;
            default -> null;
        };
    }
}

package mc.minigame.variables;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class IronArmor {
    public static List<Material> getIronArmorTypes() {
        return new ArrayList<>(List.of(
                Material.IRON_BOOTS,
                Material.IRON_LEGGINGS,
                Material.IRON_CHESTPLATE,
                Material.IRON_HELMET
        ));
    }
    public static double defensePoints(Material material) {
        return switch (material) {
            case IRON_BOOTS -> 1.5;
            case IRON_HELMET -> 2.5;
            case IRON_CHESTPLATE -> 5.0;
            case IRON_LEGGINGS -> 3.0;
            default -> 0.0;
        };
    }

    public static EquipmentSlot slot(Material material) {
        switch(material) {
            case IRON_BOOTS:
                return EquipmentSlot.FEET;
            case IRON_HELMET:
                return EquipmentSlot.HEAD;
            case IRON_CHESTPLATE:
                return EquipmentSlot.CHEST;
            case IRON_LEGGINGS:
                return EquipmentSlot.LEGS;
            default:
                return null;
        }
    }

}

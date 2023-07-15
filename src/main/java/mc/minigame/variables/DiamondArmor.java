package mc.minigame.variables;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class DiamondArmor {
    public static List<Material> getDiamondArmorTypes() {
        return new ArrayList<>(List.of(
                Material.DIAMOND_BOOTS,
                Material.DIAMOND_LEGGINGS,
                Material.DIAMOND_CHESTPLATE,
                Material.DIAMOND_HELMET
        ));
    }


    public static double defensePoints(Material material) {
        return switch (material) {
            case DIAMOND_BOOTS -> 2.0;
            case DIAMOND_HELMET -> 3.0;
            case DIAMOND_CHESTPLATE -> 6.0;
            case DIAMOND_LEGGINGS -> 5.0;
            default -> 0.0;
        };
    }

    public static EquipmentSlot slot(Material material) {
        switch(material) {
            case DIAMOND_BOOTS:
                return EquipmentSlot.FEET;
            case DIAMOND_HELMET:
                return EquipmentSlot.HEAD;
            case DIAMOND_CHESTPLATE:
                return EquipmentSlot.CHEST;
            case DIAMOND_LEGGINGS:
                return EquipmentSlot.LEGS;
            default:
                return null;
        }
    }

}

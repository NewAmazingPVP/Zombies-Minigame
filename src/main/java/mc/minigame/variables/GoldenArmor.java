package mc.minigame.variables;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class GoldenArmor {
    public static List<Material> getGoldenArmorTypes() {
        return new ArrayList<>(List.of(
                Material.GOLDEN_BOOTS,
                Material.GOLDEN_LEGGINGS,
                Material.GOLDEN_CHESTPLATE,
                Material.GOLDEN_HELMET
        ));
    }


    public static double defensePoints(Material material) {
        switch(material) {
            case GOLDEN_BOOTS:
                return 1.0;
            case GOLDEN_HELMET:
                return 1.5;
            case GOLDEN_CHESTPLATE:
                return 3.0;
            case GOLDEN_LEGGINGS:
                return 2.5;
            default:
                return 0.0;
        }
    }

    public static EquipmentSlot slot(Material material) {
        return switch (material) {
            case GOLDEN_BOOTS -> EquipmentSlot.FEET;
            case GOLDEN_HELMET -> EquipmentSlot.HEAD;
            case GOLDEN_CHESTPLATE -> EquipmentSlot.CHEST;
            case GOLDEN_LEGGINGS -> EquipmentSlot.LEGS;
            default -> null;
        };
    }

}

package mc.minigame.variables;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class DiamondArmor {
    public static List<Material> getDiamondArmorTypes() {
        return new ArrayList<>(List.of(
                Material.DIAMOND_BOOTS,
                Material.DIAMOND_CHESTPLATE,
                Material.DIAMOND_HELMET,
                Material.DIAMOND_LEGGINGS
        ));
    }

    public static double defensePoints(Material material) {
        switch(material) {
            case DIAMOND_BOOTS:
                return 2.0;
            case DIAMOND_HELMET:
                return 3.0;
            case DIAMOND_CHESTPLATE:
                return 6.0;
            case DIAMOND_LEGGINGS:
                return 5.0;
            default:
                return 0.0;
        }
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

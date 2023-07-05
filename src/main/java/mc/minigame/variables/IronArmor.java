package mc.minigame.variables;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class IronArmor {
    public static List<Material> getIronArmorTypes() {
        return new ArrayList<>(List.of(
                Material.IRON_BOOTS,
                Material.IRON_CHESTPLATE,
                Material.IRON_HELMET,
                Material.IRON_LEGGINGS
        ));
    }
    public static double defensePoints(Material material) {
        switch(material) {
            case IRON_BOOTS:
                return 1.5;
            case IRON_HELMET:
                return 2.5;
            case IRON_CHESTPLATE:
                return 5.0;
            case IRON_LEGGINGS:
                return 3.0;
            default:
                return 0.0;
        }
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

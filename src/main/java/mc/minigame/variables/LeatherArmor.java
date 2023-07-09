package mc.minigame.variables;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class LeatherArmor {

    public static List<Material> getLeatherArmorTypes() {
        return new ArrayList<>(List.of(
                Material.LEATHER_BOOTS,
                Material.LEATHER_CHESTPLATE,
                Material.LEATHER_HELMET,
                Material.LEATHER_LEGGINGS
        ));
    }

    public static double defensePoints(Material material) {
        switch(material) {
            case LEATHER_BOOTS:
                return 1.0;
            case LEATHER_HELMET:
                return 1.0;
            case LEATHER_CHESTPLATE:
                return 1.0;
            case LEATHER_LEGGINGS:
                return 1.0;
            default:
                return 0.0;
        }
    }

    public static EquipmentSlot slot(Material material) {
        switch(material) {
            case LEATHER_BOOTS:
                return EquipmentSlot.FEET;
            case LEATHER_HELMET:
                return EquipmentSlot.HEAD;
            case LEATHER_CHESTPLATE:
                return EquipmentSlot.CHEST;
            case LEATHER_LEGGINGS:
                return EquipmentSlot.LEGS;
            default:
                return null;
        }
    }
}

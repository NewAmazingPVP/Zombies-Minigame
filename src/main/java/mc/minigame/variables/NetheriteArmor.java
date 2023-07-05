package mc.minigame.variables;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class NetheriteArmor {

    public static List<Material> getNetheriteArmorTypes() {
        return new ArrayList<>(List.of(
                Material.NETHERITE_BOOTS,
                Material.NETHERITE_CHESTPLATE,
                Material.NETHERITE_HELMET,
                Material.NETHERITE_LEGGINGS
        ));
    }
    public static double defensePoints(Material material) {
        switch(material) {
            case NETHERITE_BOOTS:
                return 2.5;
            case NETHERITE_HELMET:
                return 3.5;
            case NETHERITE_CHESTPLATE:
                return 8.0;
            case NETHERITE_LEGGINGS:
                return 6.0;
            default:
                return 0.0;
        }
    }

    public static EquipmentSlot slot(Material material) {
        switch(material) {
            case NETHERITE_BOOTS:
                return EquipmentSlot.FEET;
            case NETHERITE_HELMET:
                return EquipmentSlot.HEAD;
            case NETHERITE_CHESTPLATE:
                return EquipmentSlot.CHEST;
            case NETHERITE_LEGGINGS:
                return EquipmentSlot.LEGS;
            default:
                return null;
        }
    }

}

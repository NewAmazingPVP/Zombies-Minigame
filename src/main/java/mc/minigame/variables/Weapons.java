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
            Material.NETHERITE_HOE,
            Material.WOODEN_AXE,
            Material.STONE_AXE,
            Material.IRON_AXE,
            Material.GOLDEN_AXE,
            Material.DIAMOND_AXE,
            Material.WOODEN_SWORD,
            Material.STONE_SWORD,
            Material.IRON_SWORD,
            Material.GOLDEN_SWORD,
            Material.DIAMOND_SWORD,
            Material.NETHERITE_SWORD,
            Material.WOODEN_SHOVEL,
            Material.STONE_SHOVEL,
            Material.IRON_SHOVEL,
            Material.GOLDEN_SHOVEL,
            Material.DIAMOND_SHOVEL,
            Material.NETHERITE_SHOVEL
    );


    public double getCooldownDuration(Material weaponType) {
        switch (weaponType) {
            // Handguns
            case WOODEN_HOE: // Glock
                return 0.6; // 0.6 seconds
            case STONE_HOE: // Beretta 92
                return 0.8; // 0.8 seconds
            case IRON_HOE: // Smith & Wesson M&P
                return 1.0; // 1.0 seconds
            case GOLDEN_HOE: // Sig Sauer P320
                return 0.5; // 0.5 seconds
            case DIAMOND_HOE: // Bolt-Action Rifle (Remington 700)
                return 1.2; // 1.2 seconds
            case NETHERITE_HOE: // Semi-Automatic Rifle (AR-15)
                return 1.5; // 1.5 seconds
            case WOODEN_AXE: // Pump-Action Shotgun (Remington 870)
                return 1.0; // 1.0 seconds
            case STONE_AXE: // Semi-Automatic Shotgun (Benelli M2)
                return 1.2; // 1.2 seconds
            case IRON_AXE: // Light Machine Gun (M249 SAW)
                return 1.5; // 1.5 seconds
            case GOLDEN_AXE: // Medium Machine Gun (M240)
                return 2.0; // 2.0 seconds
            case DIAMOND_AXE: // Heavy Machine Gun (Browning M2)
                return 2.5; // 2.5 seconds
            case WOODEN_SWORD: // Machete
                return 0.8; // 0.8 seconds
            case STONE_SWORD: // Katana
                return 1.0; // 1.0 seconds
            case IRON_SWORD: // Longsword
                return 1.2; // 1.2 seconds
            case GOLDEN_SWORD: // Rapier
                return 0.6; // 0.6 seconds
            case DIAMOND_SWORD: // Claymore
                return 1.5; // 1.5 seconds
            case NETHERITE_SWORD: // Zweihander
                return 1.8; // 1.8 seconds
            case WOODEN_SHOVEL: // Derringer
                return 0.7; // 0.7 seconds
            case STONE_SHOVEL: // Lever-Action Rifle
                return 0.9; // 0.9 seconds
            case IRON_SHOVEL: // Bolt-Action Shotgun
                return 1.2; // 1.2 seconds
            case GOLDEN_SHOVEL: // Sawed-Off Shotgun
                return 0.5; // 0.5 seconds
            case DIAMOND_SHOVEL: // Pump-Action Shotgun
                return 1.5; // 1.5 seconds
            case NETHERITE_SHOVEL: // Semi-Automatic Rifle
                return 2.0; // 2.0 seconds

            default:
                return 0.0;
        }
    }

    public double calculateDamageAmount(Material weaponType) {
        switch (weaponType) {
            // Handguns
            case WOODEN_HOE: // Glock
                return 8.0 / 3.0;
            case STONE_HOE: // Beretta 92
                return 10.0 / 3.0;
            case IRON_HOE: // Smith & Wesson M&P
                return 12.0 / 3.0;
            case GOLDEN_HOE: // Sig Sauer P320
                return 6.0 / 3.0;
            case DIAMOND_HOE: // Bolt-Action Rifle (Remington 700)
                return 25.0 / 3.0;
            case NETHERITE_HOE: // Semi-Automatic Rifle (AR-15)
                return 20.0 / 3.0;
            case WOODEN_AXE: // Pump-Action Shotgun (Remington 870)
                return 18.0 / 3.0;
            case STONE_AXE: // Semi-Automatic Shotgun (Benelli M2)
                return 16.0 / 3.0;
            case IRON_AXE: // Light Machine Gun (M249 SAW)
                return 10.0 / 3.0;
            case GOLDEN_AXE: // Medium Machine Gun (M240)
                return 8.0 / 3.0;
            case DIAMOND_AXE: // Heavy Machine Gun (Browning M2)
                return 35.0 / 3.0;
            case WOODEN_SWORD: // Machete
                return 12.0 / 3.0;
            case STONE_SWORD: // Katana
                return 15.0 / 3.0;
            case IRON_SWORD: // Longsword
                return 18.0 / 3.0;
            case GOLDEN_SWORD: // Rapier
                return 9.0 / 3.0;
            case DIAMOND_SWORD: // Claymore
                return 22.0 / 3.0;
            case NETHERITE_SWORD: // Zweihander
                return 25.0 / 3.0;
            case WOODEN_SHOVEL: // Derringer
                return 5.0 / 3.0;
            case STONE_SHOVEL: // Lever-Action Rifle
                return 8.0 / 3.0;
            case IRON_SHOVEL: // Bolt-Action Shotgun
                return 10.0 / 3.0;
            case GOLDEN_SHOVEL: // Sawed-Off Shotgun
                return 3.0 / 3.0;
            case DIAMOND_SHOVEL: // Pump-Action Shotgun
                return 16.0 / 3.0;
            case NETHERITE_SHOVEL: // Semi-Automatic Rifle
                return 20.0 / 3.0;

            default:
                return 0.0;
        }
    }

    public Weapons(){
    }
}

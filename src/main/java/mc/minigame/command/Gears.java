package mc.minigame.command;

import mc.minigame.utility.GiveWeapon;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gears implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            Player player = (Player) sender;
            if (player != null) {
                String weapon = args[0];
                weapon = weapon.toUpperCase();
                try {
                    Material material = Material.valueOf(weapon);
                    GiveWeapon.weapon(material, player);
                } catch (IllegalArgumentException e) {
                    sender.sendMessage("Invalid weapon specified.");
                }
            } else {
                sender.sendMessage("Player not found.");
            }
        }
        return true;
    }
}

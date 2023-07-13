package mc.minigame.command;

import mc.minigame.variables.Loc;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Jail implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 3) {
                double x = Double.parseDouble(args[0]);
                double y = Double.parseDouble(args[1]);
                double z = Double.parseDouble(args[2]);
                player.teleport(player.getWorld().getHighestBlockAt((int) x, (int) z).getLocation().add(0.5, 1, 0.5));
                player.sendMessage("Teleported to " + x + ", " + y + ", " + z);
            } else {
                player.sendMessage("Usage: /tp <x> <y> <z>");
            }
        } else {
            sender.sendMessage("Only players can use this command.");
        }
        return true;
    }
}

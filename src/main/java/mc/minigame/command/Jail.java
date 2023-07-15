package mc.minigame.command;

import mc.minigame.variables.Loc;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Jail implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            assert player != null;
            player.teleport(Loc.jail);
            player.sendMessage(ChatColor.RED + "Welcome to jail ;)");
        } else {
            sender.sendMessage("Specify player");
        }
        return true;
    }
}

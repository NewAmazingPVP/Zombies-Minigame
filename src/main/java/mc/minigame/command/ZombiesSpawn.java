package mc.minigame.command;

import mc.minigame.Zombies;
import mc.minigame.game.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ZombiesSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("spawnzombie")) {
            if (args.length == 1) {
                try {
                    int amount = Integer.parseInt(args[0]);
                    if(amount > 1000){
                        sender.sendMessage("Dont lag the server");
                        return false;
                    }
                    Player player = (Player) sender;
                    Location loc1 = new Location(player.getWorld(), 50.0, -21.0, -120.0);
                    Location loc2 = new Location(player.getWorld(), -40.0, -4.0, 10.0);

                    Spawn.zombies(amount + (2 * 1), loc1, loc2, (0.23 + (0.02*1)), (2.0 + (0.2*1)), (20.0 + (2*1)), (0.0 + (0.01*1)), (4.0 + (0.2*1)), (0.0 + (0.01*1)));

                } catch (NumberFormatException e) {
                    sender.sendMessage("Invalid amount specified.");
                }
            } else {
                sender.sendMessage("Please specify an amount.");
            }
        }

        return true;
    }
}

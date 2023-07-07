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
                    Player player = (Player) sender;
                    Location loc1 = new Location(player.getWorld(), 50.0, -21.0, -120.0);
                    Location loc2 = new Location(player.getWorld(), -40.0, -4.0, 10.0);

                    //Spawn.zombies(amount, loc1, loc2);

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

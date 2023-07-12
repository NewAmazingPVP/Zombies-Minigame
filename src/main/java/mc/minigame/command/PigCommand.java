package mc.minigame.command;


import mc.minigame.game.PlayerMoney;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("spawnpig")) {
            if (args.length == 2) {
                try {
                    Player player = Bukkit.getPlayer(args[0]);
                    if (player != null) {
                        int amount = Integer.parseInt(args[1]);
                        PlayerMoney.addCoins(player, amount);
                        sender.sendMessage("Successfully gave money to " + player.getName());
                    } else {
                        sender.sendMessage("Player not found.");
                    }
                } catch (NumberFormatException e) {
                    sender.sendMessage("Invalid amount specified.");
                }
            } else {
                sender.sendMessage("Please specify a player and an amount.");
            }
        }

        return true;
    }
}

package mc.minigame.command;

import mc.minigame.game.PlayerMoney;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class MoneySet implements CommandExecutor {
    PlayerMoney playerMoney = new PlayerMoney();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setmoney")) {
            if (args.length == 1) {
                try {
                    int amount = Integer.parseInt(args[0]);
                    playerMoney.setCoins((Player) sender, amount);

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

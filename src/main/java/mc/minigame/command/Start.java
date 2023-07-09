package mc.minigame.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import mc.minigame.game.GameStart;

public class Start implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("startgame")) {
            try {
                if (args.length == 1) {
                    int amount = Integer.parseInt(args[0]);
                    GameStart.start(amount);
                    GameStart.onStart();
                }
            } catch (NumberFormatException e) {
                sender.sendMessage("Invalid amount of rounds specified.");
            }
        } else {
            sender.sendMessage("Type /startgame");
        }

        return true;
    }
}

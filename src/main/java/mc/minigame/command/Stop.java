package mc.minigame.command;

import mc.minigame.game.Rounds;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Stop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("stopgame")) {
            Rounds.maxRounds = Rounds.round();
            Rounds.endRound();
        } else {
            sender.sendMessage("Type /stopgame");
        }
        return true;
    }
}

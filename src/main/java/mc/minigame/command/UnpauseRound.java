package mc.minigame.command;

import mc.minigame.game.Rounds;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnpauseRound  implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Rounds.unpauseRound();
        return true;
    }
}

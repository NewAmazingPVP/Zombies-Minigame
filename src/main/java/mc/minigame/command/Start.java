package mc.minigame.command;

import mc.minigame.Zombies;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import mc.minigame.game.GameStart;

public class Start implements CommandExecutor {
    private Zombies zombies;

    public Start(Zombies zombies) {
        this.zombies = zombies;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("startgame")) {
            try {
                if (args.length == 1) {
                    int amount = Integer.parseInt(args[0]);
                    GameStart gameStart = new GameStart(zombies);
                    gameStart.start(amount);
                    gameStart.onStart();
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

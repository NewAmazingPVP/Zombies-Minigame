package mc.minigame.command;

import mc.minigame.Zombies;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import mc.minigame.game.GameStart;
import org.bukkit.entity.Player;

public class Start implements CommandExecutor {
    private Zombies zombies;

    public Start(Zombies zombies) {
        this.zombies = zombies;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("startgame")) {
            try {
                GameStart gameStart = new GameStart(zombies); // Moved initialization here
                for (Player player : Bukkit.getOnlinePlayers()){
                    gameStart.onStart(player);
                }
                gameStart.start();
            } catch (NumberFormatException e) {
                sender.sendMessage("Game couldn't start.");
            }
        } else {
            sender.sendMessage("Type /startgame");
        }

        return true;
    }
}

package mc.minigame.command;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import mc.minigame.Zombies;

public class TimerCommand implements CommandExecutor {

    private Zombies zombies;

    public TimerCommand(Zombies zombies){
        this.zombies =zombies;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(ChatColor.GREEN + "Timer started!");

        // Start a new timer task
        new TimerTask(player).runTaskTimer(zombies, 0L, 20L); // 20 ticks = 1 second

        return true;
    }

    private static class TimerTask extends BukkitRunnable {

        private final Player player;
        private long startTime;

        public TimerTask(Player player) {
            this.player = player;
            this.startTime = System.currentTimeMillis() / 1000; // Convert to seconds
        }

        @Override
        public void run() {
            long elapsedTime = (System.currentTimeMillis() / 1000) - startTime;
            long minutes = elapsedTime / 60;
            long seconds = elapsedTime % 60;

            String message = (ChatColor.YELLOW + "Elapsed Time: " + minutes + "m " + seconds + "s");
            TextComponent textComponent = new TextComponent(message);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, textComponent);
        }
    }
}

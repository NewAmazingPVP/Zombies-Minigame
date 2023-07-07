package mc.minigame.game;

import mc.minigame.Zombies;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GameStart {
    private Zombies zombies;

    public GameStart(Zombies zombies) {
        this.zombies = zombies;
    }

    public void onStart(Player player) {
        PlayerMoney.clearCoins();
        DisplayBoard.board(player);
        Location loc = new Location(player.getWorld(), -38.0, -19.0, -58.0);
        player.teleport(loc);
        player.sendTitle(ChatColor.GREEN + "Game Started. Good Luck!", "");
    }

    public void start(){
        Rounds.maxRounds = 5;
        Rounds.startRound();
    }
}

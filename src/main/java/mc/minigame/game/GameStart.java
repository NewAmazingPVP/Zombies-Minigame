package mc.minigame.game;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static mc.minigame.Zombies.zombies;

public class GameStart {

    public static void onStart() {
        for (Player p : zombies.getServer().getOnlinePlayers()) {
            PlayerMoney.setCoins(p, 0);
            Location loc = new Location(p.getWorld(), -38.0, -19.0, -58.0);
            p.teleport(loc);
            p.sendTitle(ChatColor.DARK_PURPLE + "Game Started. Good Luck!", "");
        }
    }

    public static void start(Integer amount){
        Rounds.maxRounds = amount;
        Rounds.startRound();
    }
}

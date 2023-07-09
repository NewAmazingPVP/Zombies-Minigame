package mc.minigame.game;

import mc.minigame.Zombies;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GameStart {
    private Zombies zombies;

    public GameStart(Zombies zombies) {
        this.zombies = zombies;
    }

    public void onStart() {
        for (Player p : zombies.getServer().getOnlinePlayers()) {
            PlayerMoney.setCoins(p, 0);
            Location loc = new Location(p.getWorld(), -38.0, -19.0, -58.0);
            p.teleport(loc);
            p.sendTitle(ChatColor.DARK_PURPLE + "Game Started. Good Luck!", "");
        }
    }

    public void start(Integer amount){
        Rounds.maxRounds = amount;
        Rounds.startRound();
    }
}

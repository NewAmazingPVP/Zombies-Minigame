package mc.minigame.game;

import mc.minigame.Zombies;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;

public class GameStart {
    private Zombies zombies;

    public GameStart(Zombies zombies) {
        this.zombies = zombies;
    }

    public void onStart() {
        for (Player p : zombies.getServer().getOnlinePlayers()) {
            PlayerMoney.clearCoins();
            DisplayBoard.board();
            Location loc = new Location(p.getWorld(), -38.0, -19.0, -58.0);
            p.teleport(loc);
            p.sendTitle(ChatColor.GREEN + "Game Started. Good Luck!", "");
        }
    }

    public void start(Integer amount){
        Rounds.maxRounds = amount;
        Rounds.startRound();
    }
}

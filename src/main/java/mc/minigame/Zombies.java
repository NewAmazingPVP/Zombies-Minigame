package mc.minigame;

import mc.minigame.command.*;
import mc.minigame.game.DisplayBoard;
import mc.minigame.game.PlayerMoney;
import mc.minigame.game.Rounds;
import mc.minigame.listener.PickCoins;
import mc.minigame.listener.RadiationBypass;
import mc.minigame.listener.Saturation;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import mc.minigame.listener.Shoot;
import mc.minigame.game.GameStart;

public class Zombies extends JavaPlugin implements Listener {
    public GameStart gameStart = new GameStart(this);
    public PlayerMoney playerMoney = new PlayerMoney();
    public DisplayBoard displayBoard = new DisplayBoard(this);
    public Rounds rounds = new Rounds(this);

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Shoot(), this);
        getServer().getPluginManager().registerEvents(new PickCoins(this), this);
        getServer().getPluginManager().registerEvents(new RadiationBypass(), this);
        getServer().getPluginManager().registerEvents(new Saturation(this), this);
        getCommand("givemoney").setExecutor(new GiveMoney());
        getCommand("removemoney").setExecutor(new RemoveMoney());
        getCommand("giveset").setExecutor(new ArmorSet());
        getCommand("spawnzombie").setExecutor(new ZombiesSpawn());
        getCommand("setTimer").setExecutor(new TimerCommand(this));
        getCommand("startgame").setExecutor(new Start(this));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendTitle(ChatColor.DARK_PURPLE + "Welcome to the zombie game ;)", "");
        player.getWorld().strikeLightningEffect(event.getPlayer().getLocation());
        PotionEffect newEffect = new PotionEffect(PotionEffectType.BLINDNESS, 5*20, 1, false, false);
        player.addPotionEffect(newEffect);
        PlayerMoney.setCoins(player, 0);
        DisplayBoard.board();
    }

}

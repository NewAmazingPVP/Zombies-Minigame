package mc.minigame;

import mc.minigame.command.*;
import mc.minigame.game.DisplayBoard;
import mc.minigame.game.PlayerMoney;
import mc.minigame.listener.PickCoins;
import mc.minigame.listener.RadiationBypass;
import mc.minigame.listener.Saturation;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import mc.minigame.listener.Shoot;


public class Zombies extends JavaPlugin implements Listener {
    public static Economy economy;
    public static Zombies zombies;

    @Override
    public void onEnable() {
        zombies = this;
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Shoot(), this);
        getServer().getPluginManager().registerEvents(new PickCoins(), this);
        getServer().getPluginManager().registerEvents(new RadiationBypass(), this);
        getServer().getPluginManager().registerEvents(new Saturation(), this);
        getCommand("givemoney").setExecutor(new GiveMoney());
        getCommand("removemoney").setExecutor(new RemoveMoney());
        getCommand("giveset").setExecutor(new ArmorSet());
        getCommand("spawnzombie").setExecutor(new ZombiesSpawn());
        getCommand("setTimer").setExecutor(new TimerCommand());
        getCommand("startgame").setExecutor(new Start());
        setupEconomy();
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

    public static void setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            economy = rsp.getProvider();
        }
    }

}

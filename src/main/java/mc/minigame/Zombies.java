package mc.minigame;

import mc.minigame.command.*;
import mc.minigame.game.Rounds;
import mc.minigame.game.ZombieCount;
import mc.minigame.game.PlayerMoney;
import mc.minigame.listener.*;
import mc.minigame.scoreboard.FastBoard;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Zombies extends JavaPlugin implements Listener {
    public static Economy economy;
    public static Zombies zombies;
    private final Map<UUID, FastBoard> boards = new HashMap<>();

    @Override
    public void onEnable() {
        zombies = this;
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Shoot(), this);
        getServer().getPluginManager().registerEvents(new PickCoins(), this);
        getServer().getPluginManager().registerEvents(new RadiationBypass(), this);
        getServer().getPluginManager().registerEvents(new Saturation(), this);
        getServer().getPluginManager().registerEvents(new GameEvents(), this);
        getCommand("givemoney").setExecutor(new GiveMoney());
        getCommand("removemoney").setExecutor(new RemoveMoney());
        getCommand("giveset").setExecutor(new ArmorSet());
        getCommand("spawnzombie").setExecutor(new ZombiesSpawn());
        getCommand("setTimer").setExecutor(new TimerCommand());
        getCommand("startgame").setExecutor(new Start());
        getCommand("stopgame").setExecutor(new Stop());
        setupEconomy();
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : this.boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendTitle(ChatColor.DARK_PURPLE + "Welcome to the zombie game ;)", "");
        player.getWorld().strikeLightningEffect(event.getPlayer().getLocation());
        PotionEffect newEffect = new PotionEffect(PotionEffectType.BLINDNESS, 5*20, 1, false, false);
        player.addPotionEffect(newEffect);
        PlayerMoney.setCoins(player, 0);
        FastBoard board = new FastBoard(player);
        board.updateTitle(ChatColor.DARK_PURPLE + "Zombies Minigame");
        this.boards.put(player.getUniqueId(), board);
        if(Rounds.gameOn){
            if(!Rounds.deadPlayers.contains(player)){
                Rounds.deadPlayers.add(player);
            }
            player.getInventory().clear();
            ItemStack weapon = new ItemStack(Material.STONE_HOE, 1);
            player.getInventory().addItem(weapon);
            ItemStack ammo = new ItemStack(Material.ARROW, 64);
            player.getInventory().addItem(ammo);
            player.setGameMode(GameMode.SURVIVAL);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        FastBoard board = this.boards.remove(player.getUniqueId());
        if (board != null) {
            board.delete();
        }
    }

    public static void setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            economy = rsp.getProvider();
        }
    }

    private void updateBoard(FastBoard board) {
        int coins = PlayerMoney.getCoins(board.getPlayer());
        int level = Rounds.getRounds();
        int ammo = Shoot.countArrowsInInventory(board.getPlayer());
        int seconds = Rounds.getTimeLeft();
        int zombieCount = ZombieCount.getTotalZombieCount();

        board.updateLines(
                "",
                ChatColor.YELLOW + "Coins: " + coins,
                ChatColor.AQUA + "Level: " + level,
                ChatColor.BLUE + "Ammo: " + ammo,
                ChatColor.GREEN + "Time Left: " + seconds,
                ChatColor.DARK_RED + "Zombies: " + zombieCount
        );
    }
}

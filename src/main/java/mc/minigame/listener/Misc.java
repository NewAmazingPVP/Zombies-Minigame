package mc.minigame.listener;

import mc.minigame.game.PlayerMoney;
import mc.minigame.game.Rounds;
import mc.minigame.variables.Loc;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.HashSet;

import static mc.minigame.Zombies.zombies;
import static mc.minigame.game.Rounds.deadPlayers;

public class Misc implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if(Rounds.gameOn){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDie(PlayerDeathEvent event){
        if(Rounds.gameOn){
            Player player = event.getEntity();
            deadPlayers.add(player);
            boolean allDead = new HashSet<>(deadPlayers).containsAll(zombies.getServer().getOnlinePlayers());
            if (allDead) {
                player.teleport(Loc.lobby);
                player.getInventory().clear();
                PlayerMoney.setCoins(player, 0);
                zombies.getServer().dispatchCommand(zombies.getServer().getConsoleSender(), "stopgame");
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (Rounds.gameOn) {
            event.setRespawnLocation(Loc.deadLobby);
        }
    }

}

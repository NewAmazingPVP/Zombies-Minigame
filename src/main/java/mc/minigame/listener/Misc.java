package mc.minigame.listener;

import mc.minigame.game.Rounds;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Misc implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if(Rounds.gameOn){
            event.setCancelled(true);
        }
    }
}

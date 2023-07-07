package mc.minigame.listener;

import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class ZombieCount implements Listener {
    @EventHandler
    public void onZombieDeath(EntityDeathEvent event){
        if (event.getEntity() instanceof Zombie)
        {

        }
    }

}

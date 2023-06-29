package mc.minigame;


import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Zombies extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register the Zombies class as an event listener
        getServer().getPluginManager().registerEvents(this, this);
    }
}

package plugin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import runnableBinds.DefaultAction;

public class IBListener implements Listener{//handles targetting of entities in mommode. it stops the mobs from targetting her unless she's hit them first
	public IBListener(InventoryBinder plugin){
		this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
    	System.out.println("Beginning of onPlayerJoin for IBListener");
    	InventoryBinder.bm.registerPlayer(event.getPlayer());
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
    	System.out.println("Beginning of onPlayerQuit for IBListener");
    }
    @EventHandler
    public void onPlayerItemChange(PlayerItemHeldEvent event) {
    	System.out.println("Beginning of onPlayerItemChange for IBListener");
    	if(event.getNewSlot()==0){
    		return;//could change this to be the default slot of the player and get that through the bind manager
    	}
    	System.out.println("Trying to change to slot "+event.getNewSlot());
    	if(event.getNewSlot()!=0){
    		event.setCancelled(true);
    	}
    	InventoryBinder.bm.runBind(event.getNewSlot(), event.getPlayer());
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        System.out.println("about to schedule the task...");
        scheduler.scheduleSyncDelayedTask(plugin, new DefaultAction(event.getPlayer()), 20L);
    }
    private Plugin plugin;
}

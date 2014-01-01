package plugin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class IdleListener implements Listener{//does things when the player becomes idle. idle time id determined by a private variable
    public IdleListener(MomMode plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
		MomMode.isIdle=false;
		MomMode.idleseconds=0;
    	//this next part starts the repeating task that checks for idleness
    	IdleThread idlecheck=new IdleThread();
    	idlecheck.setId(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, idlecheck, 0, 20));
    }
    public void onPlayerQuit(PlayerQuitEvent event) {
    	System.out.println("Beginning of onPlayerQuit for idleListener");
    	if(event.getPlayer().getDisplayName().equals("gandalf615")){
    		MomMode.isIdle=false;
    		MomMode.idleseconds=0;
    		idlecheck.cancel();
    		unregister();
    	}
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){//probably have to use this
    	if(event.getPlayer().getDisplayName().equals("gandalf615")){
    		MomMode.idleseconds=0;
    		MomMode.isIdle=false;
    	}
    }
    private void unregister(){
    	HandlerList.unregisterAll(this);
    }
    private Plugin plugin;
    private IdleThread idlecheck;
}

package plugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import plugin.MomMode;

//the MomMode listener handle's the idleness for now
public class MomListener implements Listener {
    private final MomMode plugin;
    public MomListener(MomMode plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
    	System.out.println("Beginning of onPlayerJoin");
    	if(event.getPlayer().getDisplayName().equals("gandalf615")){
    		MomMode.isMomOn=true;
    		MomMode.momPlayer=event.getPlayer();
    		new TargetListener(plugin);
    		//new IdleListener(plugin);
    	}
        event.getPlayer().sendMessage(this.plugin.getConfig().getString("This is the onPlayerJoin message from momListener!"));
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
    	System.out.println("Beginning of onPlayerQuit");
    	if(event.getPlayer().getDisplayName().equals("gandalf615")){
    		MomMode.isMomOn=false;
    		MomMode.momPlayer=null;
    	}
    }
}

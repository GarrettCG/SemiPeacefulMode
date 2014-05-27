package plugin;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class TargetListener implements Listener{//handles targetting of entities in mommode. it stops the mobs from targetting her unless she's hit them first
	public TargetListener(MomMode plugin){
		this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        targets =new MomTargets();
	}
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
    	System.out.println("Beginning of onPlayerJoin for targetListener");
    	if(event.getPlayer().getDisplayName().equals("gandalf615")){
            if(targets==null){
            	targets=new MomTargets();
            }
    	}
        event.getPlayer().sendMessage(this.plugin.getConfig().getString("This is the onPlayerJoin message from targetListener!"));
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
    	System.out.println("Beginning of onPlayerQuit for targetListener");
    	if(event.getPlayer().getDisplayName().equals("gandalf615")){
            targets =null;
            unregister();
    	}
    }
    @EventHandler
    public void onMobTarget(EntityTargetEvent event){
    	if(MomMode.isMomOn){
    		if(event.getTarget()==MomMode.momPlayer){
    			if(targets.isOnList(event.getEntity())){
    				targets.update();
    				System.out.println("not cancelling targeting of mom by "+event.getEntity()+" cuz it's on the list");
    			}else{
    				System.out.println("cancelling targeting of mom by "+event.getEntity());
    	   			event.setCancelled(true);
    			}
    		}
    	}
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onMomAttacksEntityDamage(EntityDamageByEntityEvent event){
    	//when Mom attacks entity, add them to the list of entities that are exempt from the targeting cancel
    	System.out.println("onMomAttacks");
    	if(event.getDamager()==(Entity)MomMode.momPlayer){
    		System.out.println("mom Attacked someone so i'm going to add that entity to the queue");
    		targets.update(event.getEntity());
    	}    	
    }
    private void unregister(){
    	HandlerList.unregisterAll(this);
    }
    private MomTargets targets;
    private Plugin plugin;
}

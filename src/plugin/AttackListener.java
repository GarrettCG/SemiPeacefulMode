package plugin;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;



public class AttackListener implements Listener{//handles targetting of entities in mommode. it stops the mobs from targetting her unless she's hit them first
	public AttackListener(InventoryBinder plugin){
		this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
    @EventHandler
    public void onEntityExplosion(EntityExplodeEvent event) {
    	event.setYield(0);
    }
    private Plugin plugin;
}

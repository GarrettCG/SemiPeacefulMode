package inventory;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import plugin.InventoryBinder;
import runnableBinds.DefaultAction;

public class BindManager {
	public BindManager(InventoryBinder plugin){
		IDtoPlayerBindings=new HashMap<UUID,PlayerBindings>();
		this.plugin=plugin;
	}
	public void changeSlot(int slot,Player player){//should not be invoked in most cases
		IDtoPlayerBindings.get(player.getUniqueId()).setSlot(slot);
	}
	public void returnToCorrectSlot(Player player){
		player.getInventory().setHeldItemSlot(IDtoPlayerBindings.get(player.getUniqueId()).getDefaultSlot());
	}
	public void runBind(int slot, Player player){
		IDtoPlayerBindings.get(player.getUniqueId()).useBind(slot);
	}
	public void registerPlayer(Player player) {
		if(IDtoPlayerBindings.containsKey(player.getUniqueId())){
			System.out.println("Already contains player, returning from registerPlayer.");
		}else{
			System.out.println("registering player with default settings");
			IDtoPlayerBindings.put(player.getUniqueId(), new PlayerBindings(plugin));
			PlayerBindings pb=IDtoPlayerBindings.get(player.getUniqueId());
			for(int i=0;i<9;i++){
				pb.addBind(i, new DefaultAction(player));
			}
		}
	}
	public void addBind(int slot,Player player,Runnable run){
		IDtoPlayerBindings.get(player.getUniqueId()).addBind(slot, run);
	}
	private InventoryBinder plugin;
	private HashMap<UUID,PlayerBindings> IDtoPlayerBindings;
}

package inventory;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import plugin.InventoryBinder;

public class PlayerBindings {
	public PlayerBindings(InventoryBinder instance){
		defaultslot=0;
		currentslot=defaultslot;
		intendedItems=new ArrayList<ItemStack>(9);
		bindings=new ArrayList<Runnable>(9);
		for (int i=0;i<9;i++){
			intendedItems.add(null);
			bindings.add(null);
		}
		plugininstance=instance;
	}

	public void setSlot(int slot) {
		currentslot=slot;		
	}
	public int getCurrentSlot(){
		return currentslot;
	}
	public void addBind(int slot,Runnable run){
		bindings.set(slot, run);
	}
	public void useBind(int slot){
		if(slot==defaultslot){
			System.out.println("exiting because returning to default slot");
		}
		if(bindings.size()<9){
			System.out.println("can't use bind because nothings been registered");
		}
		bindings.get(slot).run();
	}
	public int getDefaultSlot(){
		return defaultslot;
	}
	private InventoryBinder plugininstance;
	private ArrayList <ItemStack> intendedItems;
	private ArrayList <Runnable> bindings;
	private int defaultslot,currentslot;
}

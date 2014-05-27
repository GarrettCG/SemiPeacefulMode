package plugin;
import inventory.BindManager;

import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryBinder extends JavaPlugin{
	@Override
	public void onEnable(){
		System.out.println("onEnable InventoryBind");
		getLogger().info("onEnable has been invoked for Inventory!");
        saveDefaultConfig();
        bm=new BindManager(this);
        // Create the Listener
        new IBListener(this);
        new AttackListener(this);
        // set the command executor for my commands
        IBCommandExecutor ibc=new IBCommandExecutor(this);
        this.getCommand("test").setExecutor(ibc);
        this.getCommand("speed").setExecutor(ibc);
        this.getCommand("equip").setExecutor(ibc);
        
        res=new ResourceManager();
	}
 
	public void onDisable(){
		System.out.println("onDisable MomMode");
		getLogger().info("onDisable has been invoked for MomMode!");
	}
	public static BindManager bm;
	public static ResourceManager res;
}
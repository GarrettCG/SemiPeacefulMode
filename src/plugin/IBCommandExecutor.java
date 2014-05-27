package plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import runnableBinds.ExplosionLine;
import runnableBinds.FloatingIsland;
import runnableBinds.SpeedToggle;
import runnableTypes.AttackDetails;

public class IBCommandExecutor implements CommandExecutor {
    private final InventoryBinder plugin;

    public IBCommandExecutor(InventoryBinder plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("test")){ // If the player typed /test then do the following...
			System.out.println("test:adding bind to 3:explosion line");
			Player p=(Player)sender;
			InventoryBinder.bm.addBind(3, p, new ExplosionLine(new AttackDetails(null, null, p.getWorld(), p), plugin));
			InventoryBinder.bm.addBind(2, p, new FloatingIsland(new AttackDetails(null, null, p.getWorld(), p), plugin));
			return true;
		}else if(cmd.getName().equalsIgnoreCase("speed")){ // If the player typed /test then do the following...
			System.out.println("speed:adding bind to 4:speed toggle");
			Player p=(Player)sender;
			InventoryBinder.bm.addBind(4, p, new SpeedToggle(p));//missing runnable, need to implement now
			return true;
		}else if(cmd.getName().equalsIgnoreCase("equip")){ // If the player typed /test then do the following...
			System.out.println("equip:adding bind to 5:equip");
			Player p=(Player)sender;
			//InventoryBinder.bm.addBind(5, p, );missing runnable parameter, will fill in later when i implement the equip
			return true;
		}else{
			return false;
		}
		
	}

}
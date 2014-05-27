package plugin;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MomCommandExecutor implements CommandExecutor {
    private final MomMode plugin;

    public MomCommandExecutor(MomMode plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		System.out.println("onCommand");
		return false; 
	}

}
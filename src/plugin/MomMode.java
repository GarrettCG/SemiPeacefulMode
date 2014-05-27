package plugin;
/*A note on the design of this plugin.
 * First of all, it's horribly designed. My initial choice of using a priority queue turned out to be the wrong choice. Unfortunately I stubbornly continued
 * to use the approach. I ended up writing a comparable method that violated the contract which caused problems when using algorithms that used .equals. In 
 * the end I just wrote a method to compare them manually as a band aid fix. I don't believe that it should be a slow algorithm in the end though, which is why
 * I'm just going to release it as is. It may not be the best code but at least it works.
 * 
 *  If i were to rewrite this using another approach then i'd probably just use a regular queue or linked list to start out with.
 * 
 */
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class MomMode extends JavaPlugin{
	@Override
	public void onEnable(){
		System.out.println("onEnable MomMode");
		getLogger().info("onEnable has been invoked for MomMode!");
        saveDefaultConfig();

        // Create the Listener
        new MomListener(this);
        // set the command executor for my commands
        this.getCommand("class").setExecutor(new MomCommandExecutor(this));
        isMomOn=false;
        momPlayer=null;
        idleseconds=0;
        isIdle=false;
	}
 
	public void onDisable(){
		System.out.println("onDisable MomMode");
		getLogger().info("onDisable has been invoked for MomMode!");
        isMomOn=false;
        momPlayer=null;
        idleseconds=0;
        isIdle=false;
	}
	//globals variables
	static boolean isMomOn;
	static Player momPlayer;
	static int idleseconds;
	static boolean isIdle;
}
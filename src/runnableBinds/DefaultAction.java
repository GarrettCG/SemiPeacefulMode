package runnableBinds;

import org.bukkit.entity.Player;
import plugin.InventoryBinder;
import runnableTypes.BasicRunnable;

//the default action just returns the slot pointer to default
public class DefaultAction extends BasicRunnable {

	public DefaultAction( Player player) {/////////////////the slot parameter seems useless here, i should excise it soon
		
		super( player);
	}

	@Override
	public void run() {
		System.out.println("default action beginning");
		InventoryBinder.bm.returnToCorrectSlot(player);
		System.out.println("default action ending");
	}

}

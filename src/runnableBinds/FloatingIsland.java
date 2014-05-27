package runnableBinds;

import org.bukkit.entity.Player;

import plugin.InventoryBinder;

import runnableTypes.AttackDetails;
import runnableTypes.AttackRunnable;
import scheduledTasks.ExplosionLineTask;
import scheduledTasks.FloatingIslandTask;

public class FloatingIsland extends AttackRunnable{

	public FloatingIsland(AttackDetails ad,InventoryBinder plugin) {
		super(ad);
		player=ad.getPlayer();
		this.plugin=plugin;
	}

	@Override
	public void run() {
		System.out.println("executing FloatingIsland");
		details=new AttackDetails(player.getLocation(), player.getTargetBlock(null, 100).getLocation(), player.getWorld(), player);
		new FloatingIslandTask(details).runTaskTimer(plugin, 0, 1L);		
	}
	Player player;
	InventoryBinder plugin;
}

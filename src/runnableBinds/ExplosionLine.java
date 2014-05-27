package runnableBinds;

import org.bukkit.entity.Player;

import plugin.InventoryBinder;
import runnableTypes.AttackDetails;
import runnableTypes.AttackRunnable;
import scheduledTasks.ExplosionLineTask;

public class ExplosionLine extends AttackRunnable{

	public ExplosionLine(AttackDetails ad,InventoryBinder plugin) {
		super(ad);
		details=ad;
		this.plugin=plugin;
	}

	@Override
	public void run() {
		Player p=details.getPlayer();
		details=new AttackDetails(p.getLocation(), p.getTargetBlock(null, 100).getLocation(), p.getWorld(), p);
		new ExplosionLineTask(details).runTaskTimer(plugin, 0, 1L);
	}
	public void setDetails(AttackDetails details){
		this.details=details;
	}
	AttackDetails details;
	InventoryBinder plugin;
}

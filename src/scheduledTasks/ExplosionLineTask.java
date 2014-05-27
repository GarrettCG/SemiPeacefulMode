package scheduledTasks;

import java.util.LinkedList;
import java.util.Queue;

import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import plugin.InventoryBinder;

import runnableTypes.AttackDetails;
import runnableTypes.ExploData;


public class ExplosionLineTask extends BukkitRunnable {

	@Override
	public void run() {
		if(locationQueue.isEmpty()){
			cancel();
			return;
		}else
		{
			 InventoryBinder.res.addToExploList(new ExploData(locationQueue.peek(),details.getPlayer(),true,true,false));
			 Location nextexplo=locationQueue.remove();
			 details.getWorld().createExplosion(nextexplo,3);
			 details.getPlayer().playEffect(EntityEffect.WOLF_SMOKE);
		}
	}
	public ExplosionLineTask(AttackDetails strike){
		details=strike;
		Vector directionvector=details.getPlayer().getEyeLocation().getDirection();
		Location vectlocation=new Location( strike.getWorld(),directionvector.getX(), directionvector.getY(), directionvector.getZ());
		vectlocation=vectlocation.multiply(100);
		for(double i=1;i<120;i++){
			locationQueue.add((vectlocation.clone().multiply(i/120)).add(strike.getStart().clone()));
		}
	}
	private AttackDetails details;
	public Queue<Location> locationQueue=new LinkedList<Location>();
}


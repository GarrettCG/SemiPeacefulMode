package scheduledTasks;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import plugin.InventoryBinder;

import runnableTypes.AttackDetails;

public class FloatingIslandTask extends BukkitRunnable{

	@Override
	public void run() {
		System.out.println("executing FloatingIslandTask, counter="+counter);
		if (counter>40||isDone){
			for(FallingBlock fb : fblocks){
				if(fb==null)continue;
				fb.eject();    	
				bounceBlock(fb);
			}
			cancel();
			return;	
		}
		counter++;
	}
	public FloatingIslandTask(AttackDetails strike){
		details=strike;
		Byte blockData = 0x0;
		fblocks=new ArrayList<FallingBlock>();
		for(Vector v: InventoryBinder.res.floatingGrid){
			pos=details.getEnd().clone();
			if(pos.add(v).getBlock().getType()!=Material.AIR){
				//System.out.println("pos:"+pos);
				mat=pos.getBlock().getType();
				pos.getBlock().setType(Material.AIR);
				fblocks.add(details.getWorld().spawnFallingBlock(pos, mat, blockData));
				fblocks.get(fblocks.size()-1).setVelocity(velocity);
			}else{
				fblocks.add(null);
			}
		}
		//getNearbyEntities
		if(fblocks.get(0)==null){
			System.out.println("NO FALLING BLOCK AT TARGET LOCATION");
			isDone=true;
			return;
		}
		entList=fblocks.get(0).getNearbyEntities(2, 2, 2);//have a feeling it might nullpointer cuz fblocks[0] might somehow be null
		if(!entList.isEmpty()){
			ListIterator<Entity> litr = entList.listIterator();
			while(litr.hasNext()) {
				Entity element = litr.next();
				if(element instanceof FallingBlock||!(element instanceof LivingEntity)){
					litr.remove();
					continue;
				}
				Vector reverseGrid=element.getLocation().toVector().subtract(details.getEnd().toVector());
				reverseGrid.setX(reverseGrid.getBlockX());
				if(((int)reverseGrid.getY())==1||((int)reverseGrid.getY())==2){
					reverseGrid.setY(0);
				}else{
					reverseGrid.setY(reverseGrid.getBlockY());
				}
				reverseGrid.setZ(reverseGrid.getBlockZ());
				System.out.println("entity at reversegrid position:"+reverseGrid);

				for(int i=0;i<InventoryBinder.res.floatingGrid.size();i++){
					if(InventoryBinder.res.floatingGrid.get(i)==null) continue;
					if(InventoryBinder.res.floatingGrid.get(i).equals(reverseGrid)){
						System.out.println("The damn thing is equal and im gonna set him as a passenger now v:"+InventoryBinder.res.floatingGrid.get(i)+" reverseGrid:"+reverseGrid);
						/*if(!fblocks.containsKey(v)){
							System.out.println("Doesn't contain the key!"+v);
						}else{
							System.out.println("contains the key"+v);
						}*/
						if(fblocks.get(i)!=null){
							System.out.println("Plugina.InventoryBinder.res.floatingGrid.get(i)!=null");
							System.out.println("Plugina.InventoryBinder.res.floatingGrid.get(i)="+InventoryBinder.res.floatingGrid.get(i));
							System.out.println("let's see what element is:"+element);
							fblocks.get(i).setPassenger(element);
						}else{
							System.out.println("Plugina.InventoryBinder.res.floatingGrid.get(i)=null!!!!!!!!");
						}

					}
					else{
						System.out.println("unequal v:"+InventoryBinder.res.floatingGrid.get(i)+" reverseGrid:"+reverseGrid);
					}
				}
			}
			pos=details.getEnd().clone();
		}
		counter=0;
	}
	public void bounceBlock(FallingBlock b){
		if(b == null) return;
		System.out.println("inside bounceblock after null check");
		FallingBlock fb = b;   
		float x = (float) -1 + (float) (Math.random() * ((1 - -1) + 1));
		float y = 2;//(float) -5 + (float)(Math.random() * ((5 - -5) + 1));
		float z = (float) -0.3 + (float)(Math.random() * ((0.3 - -0.3) + 1));
		fb.setVelocity(new Vector(x, y, z));
	}
	private List<Entity> entList;
	private int counter;
	private Vector velocity=new Vector(0,2.0f,0);
	private ArrayList<FallingBlock> fblocks;
	private Material mat;
	private Location pos;
	private boolean isDone;
	private AttackDetails details;
}

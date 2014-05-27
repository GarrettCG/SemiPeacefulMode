package plugin;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

import org.bukkit.entity.Entity;

public class MomTargets {
	public MomTargets(){
		momTargets=new PriorityBlockingQueue<TimedEntity>();
	}
	public void update(Entity e){
		//insert into priority queue or reinsert if already there
		//first we must check and see if it's already in the queue
		update();

		if(trueContains(e)){
			//now we reinsert
			System.out.println("reinserting "+e.getEntityId()+". removed target? "+trueRemove(e));//can't remove entity, must remove timedEntity, so i need a trueRemove now...or just change it so it doesn't reinsert...
			
			momTargets.add(new TimedEntity(e,System.currentTimeMillis() / 1000L));
		}else{
			//now we just regularly insert
			System.out.println("inserting "+e.getEntityId());
			TimedEntity nte=new TimedEntity(e,System.currentTimeMillis() / 1000L);
			momTargets.add(nte);
			System.out.println("now we immediately check to see if it's in the list :"+momTargets.contains(e)+" and the size of the list is:"+momTargets.size()+" and the hash for e is:"+e.hashCode()+" and the hash for the TimedEntity is:"+nte.hashCode()+ " and the result of e==nte: "+(nte==e));
		}
	}
	public void update(){
		currentTime = System.currentTimeMillis() / 1000L;
		if(momTargets==null) return;
		while(momTargets.size()>0){
			if(currentTime-momTargets.peek().getTimestamp()>=threshold){
				momTargets.poll();
			}else{
				break;
			}
		}
	}
	public boolean isOnList(Entity e){
		if(momTargets==null){
			return false;
		}else{
			return trueContains(e);
		}
	}
	private boolean trueContains(Entity e){//i have to write this function because i violate the contract when implementing the timedEntity comparable method
		Iterator <TimedEntity>it=momTargets.iterator();
		while(it.hasNext()){
			TimedEntity te=it.next();
			if(te.equals(e)){
				return true;
			}
		}
		return false;
	}
	private boolean trueRemove(Entity e){
		Iterator <TimedEntity>it=momTargets.iterator();
		while(it.hasNext()){
			TimedEntity te=it.next();
			if(te.equals(e)){
				it.remove();
				return true;
			}
		}
		return false;
	}
	private long currentTime;
	private final long threshold=15;
	private PriorityBlockingQueue<TimedEntity> momTargets;
}

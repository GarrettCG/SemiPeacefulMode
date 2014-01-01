package plugin;

import org.bukkit.entity.Entity;



public class TimedEntity implements Comparable<TimedEntity>{
	public TimedEntity(Entity e, long ts){
		time=ts;
		ent=e;
	}
	public Entity getEntity(){
		return ent;
	}
	public long getTimestamp(){
		return time;
	}
	public int compareTo(TimedEntity ti) {
		if (ti.time > time) return -1;
		if (ti.time == time) return 0;
	    return 1;
	}
	@Override 
    public boolean equals(Object other) {
        boolean result = false;

        if (other instanceof Entity) {

            Entity that = (Entity) other;
            result = (this.ent==that);
        }else if(other instanceof TimedEntity){

        	TimedEntity that=(TimedEntity) other;
        	result=(this==that);
        }

        return result;
    }
	@Override
    public int hashCode() {
        return (ent.hashCode());
    }
	
	private long time;
	private Entity ent;
}

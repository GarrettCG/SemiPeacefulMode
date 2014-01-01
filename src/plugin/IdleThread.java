package plugin;

import org.bukkit.Bukkit;


public class IdleThread implements Runnable {
		private int id;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public IdleThread(){
		}
	   public void run() {
		   if(!MomMode.isMomOn){
			   cancel();
		   }else{
			   MomMode.idleseconds+=1;
			   System.out.println("idle for "+MomMode.idleseconds+" seconds");
			   if(MomMode.idleseconds>=30){
				   MomMode.isIdle=true;
				   System.out.println("now idle!");
			   }
		   }
	   }
	   public void cancel() {
		    Bukkit.getScheduler().cancelTask(id);
	   }
}
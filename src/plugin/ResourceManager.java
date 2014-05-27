package plugin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.bukkit.util.Vector;

import runnableTypes.ExploData;

public class ResourceManager {
	public ResourceManager(){
		explosionlist=new LinkedList<ExploData>();
		floatingGrid=new ArrayList<Vector>();
        floatingGrid.add(new Vector(0,0,0));//0
        floatingGrid.add(new Vector(0,0,1));//1
        floatingGrid.add(new Vector(1,0,0));//2
        floatingGrid.add(new Vector(1,0,1));//3
        floatingGrid.add(new Vector(0,0,-1));//4
        floatingGrid.add(new Vector(-1,0,0));//5
        floatingGrid.add(new Vector(-1,0,-1));//6
        floatingGrid.add(new Vector(1,0,-1));//7
        floatingGrid.add(new Vector(-1,0,1));//8
        floatingGrid.add(new Vector(2,0,0));//9
        floatingGrid.add(new Vector(0,0,2));//10
        floatingGrid.add(new Vector(-2,0,0));//11
        floatingGrid.add(new Vector(0,0,-2));//12
        floatingGrid.add(new Vector(0,-1,0));//13
        floatingGrid.add(new Vector(0,-1,1));//14
        floatingGrid.add(new Vector(1,-1,0));//15
        floatingGrid.add(new Vector(0,-1,-1));//16
        floatingGrid.add(new Vector(-1,-1,0));//17
        
        floatingGrid.add(new Vector(0,1,0));//0
        floatingGrid.add(new Vector(0,1,1));//1
        floatingGrid.add(new Vector(1,1,0));//2
        floatingGrid.add(new Vector(1,1,1));//3
        floatingGrid.add(new Vector(0,1,-1));//4
        floatingGrid.add(new Vector(-1,1,0));//5
        floatingGrid.add(new Vector(-1,1,-1));//6
        floatingGrid.add(new Vector(1,1,-1));//7
        floatingGrid.add(new Vector(-1,1,1));//8
        floatingGrid.add(new Vector(2,1,0));//9
        floatingGrid.add(new Vector(0,1,2));//10
        floatingGrid.add(new Vector(-2,1,0));//11
        floatingGrid.add(new Vector(0,1,-2));//12
	}
	public void addToExploList(ExploData e){
		explosionlist.add(e);
	}
	public ExploData pollExplo(){
		return explosionlist.poll();
	}
	public Queue<ExploData> explosionlist;
	public ArrayList<Vector> floatingGrid;
}

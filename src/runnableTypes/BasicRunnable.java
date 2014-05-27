package runnableTypes;

import org.bukkit.entity.Player;

public abstract class BasicRunnable implements Runnable {
	public BasicRunnable(Player player){
		this.player=player;
	}
	protected int slot;
	protected Player player;
}

package runnableBinds;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import runnableTypes.BasicRunnable;

public class SpeedToggle extends BasicRunnable{

	public SpeedToggle(Player player) {
		super(player);
		isOn=false;
	}
	private boolean isOn;
	@Override
	public void run() {
		if(!isOn){
			isOn=true;
			//player.setWalkSpeed(0.6f);
			PotionEffect potion=new PotionEffect(PotionEffectType.JUMP,90000000,2,true);
			PotionEffect potion2=new PotionEffect(PotionEffectType.SPEED,90000000,3,true);
			player.addPotionEffect(potion);
			player.addPotionEffect(potion2);
		}else{
			isOn=false;
			player.setWalkSpeed(originalwalkspeed);
			player.removePotionEffect(PotionEffectType.JUMP);
			player.removePotionEffect(PotionEffectType.SPEED);
		}
	}
	float originalwalkspeed=.3f;
}

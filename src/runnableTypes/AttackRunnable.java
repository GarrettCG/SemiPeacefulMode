package runnableTypes;

public abstract class AttackRunnable implements Runnable{
	public AttackRunnable(AttackDetails ad){
		details=ad;
	}
	protected AttackDetails details;
}

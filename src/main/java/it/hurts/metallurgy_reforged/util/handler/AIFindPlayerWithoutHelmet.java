package it.hurts.metallurgy_reforged.util.handler;

import java.lang.reflect.Field;

import javax.annotation.Nullable;

import it.hurts.metallurgy_reforged.item.armor.ModArmors;
import it.hurts.metallurgy_reforged.util.handler.EventHandler;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class AIFindPlayerWithoutHelmet extends EntityAIBase{

	private final EntityAIBase findplayerClass;
	
	public AIFindPlayerWithoutHelmet(EntityAIBase findplayerClass) {
		this.findplayerClass = findplayerClass;
	}
	
	
	//this method is identical to the original one but it checks if the player is wearing the eximite_helmet too
	public boolean shouldExecute() {
		boolean shouldExecute = findplayerClass.shouldExecute();
		if(shouldExecute) {
			EntityPlayer pl = getPlayer();
			shouldExecute = pl != null ? !EventHandler.isPlayerWearingSpecificArmorPiece(pl, 3, ModArmors.eximite_helmet) : false;
		}
		return shouldExecute;
	}
	
	
	//this method is identical to the original one
	public void resetTask() {
		findplayerClass.resetTask();
	}
	//this method is identical to the original one
	public void startExecuting() {
		findplayerClass.startExecuting();
	}
	//this method is identical to the original one
	public boolean shouldContinueExecuting() {
		return findplayerClass.shouldContinueExecuting();
	}
	//this method is identical to the original one
	public void updateTask() {
		findplayerClass.updateTask();
	}

	
	//get the player from the original AI Classe
	@Nullable
	public EntityPlayer getPlayer()
	{
		try
		{
			Field aiClass = findplayerClass.getClass().getDeclaredField("player");
			aiClass.setAccessible(true);
			return (EntityPlayer) aiClass.get(findplayerClass);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
}

/*
 * -------------------------------------------------------------------------------------------------------
 * Class: EximiteHelmetEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.effects;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.AbstractMetallurgyEffect;
import it.hurts.metallurgy_reforged.entity.ai.AIFindPlayerWithoutHelmet;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntityEnderman;

import javax.annotation.Nullable;
import java.util.Iterator;

public class EximiteHelmetEffect extends AbstractMetallurgyEffect {

	public EximiteHelmetEffect()
	{
		super(ModMetals.EXIMITE);
	}

	@Override
	protected boolean isEnabled()
	{
		return ArmorEffectsConfig.eximiteArmorEffect;
	}

	@Override
	protected boolean isToolEffect()
	{
		return false;
	}

	@Nullable
	@Override
	protected EnumTools getToolClass()
	{
		return null;
	}

	@Override
	public void onEntityEnteringChunk(Entity entity)
	{
		//check if spawned entity is an enderman
		if (entity instanceof EntityEnderman)
		{

			EntityEnderman end = (EntityEnderman) entity;
			EntityAIBase aifindPlayer = null;
			int priority = 0;
			Iterator<EntityAITasks.EntityAITaskEntry> entries = end.targetTasks.taskEntries.iterator();
			while (entries.hasNext())
			{
				EntityAITasks.EntityAITaskEntry entry = entries.next();
				if (entry.action instanceof EntityAINearestAttackableTarget)
					//checks if the AI Class is the AIFindPlayer Class(The Class Used to check if player is watching an enderman)
					if (entry.action.getClass().getName().contains("EntityEnderman$AIFindPlayer"))
					{
						aifindPlayer = entry.action;
						priority = entry.priority;
					}

			}
			//if the AI class isn't null it will replace the original AI with the AIFindPlayerWithoutHelmet( a new custom AI similar to the original one)
			if (aifindPlayer != null)
			{
				end.targetTasks.removeTask(aifindPlayer);
				end.targetTasks.addTask(priority, new AIFindPlayerWithoutHelmet(aifindPlayer));
			}
		}
	}

}

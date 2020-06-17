/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MithrilArmorEffect
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
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MithrilArmorEffect extends AbstractMetallurgyEffect {

	private static List<UUID> glowingEntities = new ArrayList<>();

	public MithrilArmorEffect()
	{
		super(ModMetals.MITHRIL);
	}

	@Override
	protected boolean isEnabled()
	{
		return ArmorEffectsConfig.mithrilArmorEffect;
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

	@SideOnly(Side.CLIENT)
	@Override
	public void onEntitiesRender(EntityLivingBase entity, RenderLivingBase<EntityLivingBase> renderer, float partialRenderTicks, double x, double y, double z)
	{
		EntityPlayer clientPlayer = Minecraft.getMinecraft().player;

		//Check if player exists and the Rendered Entity isn't the player himself
		if (clientPlayer != null && !entity.equals(clientPlayer))
		{
			//checks if the player wears The Mithril Armor, the rendered entity is not glowing and it's within 30 blocks from the player, the effect is not disabled in the config
			if (EventUtils.isPlayerWearingArmor(clientPlayer, ModMetals.MITHRIL)
					&& entity.getDistance(clientPlayer) < 30D
					&& !entity.isGlowing()
					&& ArmorEffectsConfig.mithrilArmorEffect)
			{
				entity.setGlowing(true);
				glowingEntities.add(entity.getUniqueID());
			}
			//Removes the effect only if metallurgy adds it
			else if (glowingEntities.contains(entity.getUniqueID()))
			{
				entity.setGlowing(false);
				glowingEntities.remove(entity.getUniqueID());
			}
		}
	}

}

/*==============================================================================
 = Class: EtheriumArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.ProgressiveDataBundle;
import it.hurts.metallurgy_reforged.config.EffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EtheriumArmorEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

	private static final Set<String> blockBlacklist;

	static
	{
		blockBlacklist = Arrays.stream(EffectsConfig.etheriumEffectArmorBlacklist).collect(Collectors.toSet());
	}

	public EtheriumArmorEffect()
	{
		super(ModMetals.ETHERIUM);
	}


	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}


	@Override
	public void onStep(World world, EntityPlayer entity, ItemStack effectStack, int maxSteps, int step)
	{
		//System.out.println(step);

		//Timer Expires
		if (step == maxSteps)
		{
			entity.noClip = false;

			assert metal.getArmorSet() != null;
			for (ItemArmorBase armorItem : metal.getArmorSet())
				entity.getCooldownTracker().setCooldown(armorItem, 400);
		}
	}

	@SubscribeEvent
	public void livingEvent(LivingEvent.LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer entity = (EntityPlayer) event.getEntityLiving();

			if (!entity.isSneaking() || !canBeApplied(entity))
				return;

			if (entity.getCooldownTracker().getCooldown(getArmorRepr(entity).getItem(), 0) != 0)
				return;

			ProgressiveDataBundle bundle = entity.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null).etheriumArmorBundle;

			AxisAlignedBB box = entity.getEntityBoundingBox().grow(0.1D, 0D, 0.1D);

			List<AxisAlignedBB> collisions = entity.world.getCollisionBoxes(entity, box);
			System.out.println(collisions);
			if (!collisions.isEmpty())
			{
				//Resume the timer
				if (!bundle.isEffectInProgress())
					bundle.setPaused(false, entity);

				//If the timer is still stopped:
				//Kick-start the timer
				if (!bundle.isEffectInProgress())
					bundle.incrementStep(entity);

				entity.noClip = true;
				entity.motionY = 0D;
			}
			else
			{
				bundle.setPaused(true, entity);
			}

			BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
			for (int x = (int) box.minX; x <= MathHelper.ceil(box.maxX); x++)
			{
				for (int y = entity.getPosition().getY(); y <= MathHelper.ceil(box.maxY); y++)
				{
					for (int z = (int) box.minZ; z <= MathHelper.ceil(box.maxZ); z++)
					{
						pos.setPos(x, y, z);

						if (box.intersects(x, y, z, x + 1D, y + 1D, z + 1D) && blockBlacklist.contains(entity.world.getBlockState(pos).getBlock().getRegistryName().toString()))
						{
							Vec3d vec = entity.getPositionVector().subtract(x + 0.5D, y + 0.5D, z + 0.5D);
							double length = vec.length();
							double velocity = length * 0.1D;
							Vec3d nor = vec.normalize();
							entity.velocityChanged = true;
							entity.motionX += nor.x * velocity;
							entity.motionZ += nor.z * velocity;
						}

					}
				}
			}
		}
	}


	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void setupFogWhileInsideBlocks(EntityViewRenderEvent.RenderFogEvent event)
	{
		Entity entity = event.getEntity();

		if (entity.isSneaking() && entity instanceof EntityLivingBase && canBeApplied(((EntityLivingBase) entity)))
		{
			if (!entity.world.getCollisionBoxes(entity, entity.getEntityBoundingBox()).isEmpty())
			{
				////This doesn't work kek
				//float[] colorComps = metal.getStats().getColorRGBValues();
				//net.minecraft.client.renderer.GlStateManager.color(1, colorComps[1], colorComps[2]);

				net.minecraft.client.renderer.GlStateManager.setFog(net.minecraft.client.renderer.GlStateManager.FogMode.EXP2);
				net.minecraft.client.renderer.GlStateManager.setFogDensity(0.25F);

				//net.minecraft.client.renderer.GlStateManager.clearColor(1, colorComps[1], colorComps[2], 1);
			}
		}
	}

}

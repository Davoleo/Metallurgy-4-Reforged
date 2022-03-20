/*==============================================================================
 = Class: EventUtils
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.effect.all.TartariteEffect;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import jline.internal.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EventUtils {

	/**
	 * @param entity The entity who is wearing the armor
	 * @param metal  The metal the armor is made of
	 * @return whether a player is wearing the complete armor set
	 */
	public static boolean isWearingFullArmorSet(EntityLivingBase entity, Metal metal)
	{
		boolean fullArmored = true;

		for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
		{
			if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR && entity.getItemStackFromSlot(slot).getItem() != metal.getArmorPiece(slot))
				fullArmored = false;
		}

		return fullArmored;
	}

	/**
	 * @param player     The player who may be wearing the armor piece
	 * @param slot       The slot in which the player may be wearing a specific armor piece
	 * @param armorEquip The armor item the player may be wearing in the specified slot
	 * @return whether the player is wearing a specific armor item in a specific Equipment Slot
	 */
	public static boolean isPlayerWearingSpecificArmorPiece(EntityPlayer player, EntityEquipmentSlot slot, Item armorEquip)
	{
		return player.inventory.armorInventory.get(slot.getIndex()).getItem() == armorEquip;
	}

	/**
	 * @param entity EntityLivingBase
	 * @param metal  The metal you need to count the number of armor piece of
	 * @return The number of pieces of armor worn by the player
	 */
	public static int getArmorPiecesCount(EntityLivingBase entity, Metal metal)
	{
		int count = 0;

		for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
			if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
				if (entity.getItemStackFromSlot(slot).getItem().equals(metal.getArmorPiece(slot)))
					count++;

		return count;
	}

	/**
	 * @param tool  The item tool you're using to harvest the block
	 * @param block The Block you're harvesting
	 * @return whether you can harvest a certain block with a specific tool
	 */
	public static boolean canHarvest(ItemStack tool, IBlockState block)
	{
		EnumTools toolType = EnumTools.byInstance(tool.getItem());
		String blockToolClass = block.getBlock().getHarvestTool(block);

		if (block.getBlock() == Blocks.BEDROCK)
			return false;

		if (blockToolClass == null)
			return true;

		if (toolType == null)
			return false;

		if (!blockToolClass.equals(toolType.getName()))
			return false;

		return block.getBlock().getHarvestLevel(block) <= tool.getItem().getHarvestLevel(tool, toolType.getName(), null, block);
	}

	private static final Metal[] metal_array = ModMetals.metalMap.values().stream()
			.filter(metal -> metal != null && metal.hasArmorSet() && canMetalBeEquippedByMob(metal))
			.toArray(Metal[]::new);


	private static boolean canMetalBeEquippedByMob(Metal metal)
	{
		for (String metalString : GeneralConfig.metalsThatCannotBeEquipped)
			if (metal.getStats().getName().equalsIgnoreCase(metalString))
				return false;
		return true;
	}

	public static ItemStack getRandomEquipmentPiece(Metal metal, EntityLivingBase entity)
	{
		final List<ItemStack> equip = new ArrayList<>();

		for (ItemStack stack : entity.getEquipmentAndArmor())
		{
			if (ItemUtils.isMadeOfMetal(metal, stack.getItem()) || TartariteEffect.getParagonMetal(stack) == metal)
				equip.add(stack);
		}

		return equip.get(Utils.random.nextInt(equip.size()));
	}

	@Nullable
	public static Metal getRandomMetalBasedOnDifficulty(World world)
	{
		//Some math Reminders
		//(1 * 1 - 0) * 5 = 5;
		//(2 * 2 - 1) * 5 = 15;
		//(3 * 3 - 2) * 5 = 35;
		//(3^0) * 5 = 5 - 5 * difficulty + 1 = 0;
		//(3^1) * 5 = 15 - 5 * difficulty + 1 = 5;
		//(3^2) * 5 = 45 - 5 * difficulty + 1 = 15;
		//(3^3) * 5 = 135 - 5 * difficulty + 1  = 30;
		float chance = 0;
		switch (world.getDifficulty().getId())
		{
			case 0:
				chance = 0;
				break;
			case 1:
				chance = 5;
				break;
			case 2:
				chance = 10;
				break;
			case 3:
				chance = 15;
				break;
		}

		if ((Utils.random.nextFloat() * 100) < chance)
		{
			/*
			int targetWeight = Utils.random.nextInt(totale);

			int sumOfWeights = 0;

			Metal pickedMetal = null;
			int index = 0;

			while(pickedMetal == null && index < metal_array.length)
			{
				Metal metal = metal_array[index];
				sumOfWeights += 6 - metal.getStats().getOreHarvest();
				if(targetWeight <= sumOfWeights)
					pickedMetal = metal;
				++index;
			}
			return pickedMetal;
			*/
			return metal_array[Utils.random.nextInt(metal_array.length)];
		}

		return null;
	}

	/**
	 * Based on {@link ModifiableAttributeInstance#computeValue()}
	 *
	 * @return base value modified with the attribute modifier
	 */
	@SuppressWarnings("JavadocReference")
	public static double applyAttributeModifier(double base, AttributeModifier modifier)
	{
		switch (modifier.getOperation())
		{
			case 0:
				return base + modifier.getAmount();
			case 1:
				return base + base * modifier.getAmount();
			case 2:
				return base * (1D + modifier.getAmount());
			default:
				return base;
		}
	}

	/**
	 * @param maxClamp is the float value to clamp-lerp to
	 */
	public static float getDarknessLevel(Entity entity, float maxClamp)
	{
		BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
		//check if it is day
		boolean isDay = entity.world.provider.isDaytime();
		//get sky light level,if it is night the light will be 0
		int lightSky = Math.min(isDay ? entity.world.getLightFor(EnumSkyBlock.SKY, pos) : 0, 14);
		//get light emitted by a block(like a torch)
		int lightBlock = Math.min(entity.world.getLightFor(EnumSkyBlock.BLOCK, pos), 14);
		//get the light based on the lightSky and the lightBlock
		int light = Math.max(lightSky, lightBlock);

		//14 is the max Light possible
		return maxClamp - (light * maxClamp / 14F);
	}

}

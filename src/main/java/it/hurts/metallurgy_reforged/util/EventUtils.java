/*==============================================================================
 = Class: EventUtils
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import jline.internal.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EventUtils {

	/**
	 * @param entity The entity who is wearing the armor
	 * @param metal  The metal the armor is made of
	 *
	 * @return whether a player is wearing the complete armor set
	 */
	public static boolean isEntityWearingArmor(EntityLivingBase entity, Metal metal)
	{
		boolean fullArmored = true;

		for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
		{
			if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR && entity.getItemStackFromSlot(slot).getItem() != metal.getArmorPiece(slot))
			{
				fullArmored = false;
			}
		}

		return fullArmored;
	}

	/**
	 * @param player     The player who may be wearing the armor piece
	 * @param slot       The slot in which the player may be wearing a specific armor piece
	 * @param armorEquip The armor item the player may be wearing in the specified slot
	 *
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

	private static final Metal[] metalllarray = ModMetals.metalMap.values().stream()
			.filter(metal -> metal != null && metal.hasArmorSet())
			.toArray(Metal[]::new);
	private static final int metalIndex = Utils.random.nextInt(metalllarray.length);

	public static List<ItemStack> getEquipmentList(Metal metal, EntityLivingBase entity)
	{
		final List<ItemStack> equip = new ArrayList<>();

        for (ItemStack stack : entity.getEquipmentAndArmor())
        {
            if (ItemUtils.isMadeOfMetal(metal, stack.getItem()))
            {
                equip.add(stack);
            }
        }

        return equip;
    }

    public static ItemStack getRandomEquipmentPiece(Metal metal, EntityLivingBase entity)
    {
        final List<ItemStack> equip = new ArrayList<>();

        for (ItemStack stack : entity.getEquipmentAndArmor())
        {
            if (ItemUtils.isMadeOfMetal(metal, stack.getItem()))
            {
                equip.add(stack);
            }
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
                chance = 20;
                break;
        }

		if ((Utils.random.nextFloat() * 100) < chance)
			return metalllarray[metalIndex];

		return null;
	}

}

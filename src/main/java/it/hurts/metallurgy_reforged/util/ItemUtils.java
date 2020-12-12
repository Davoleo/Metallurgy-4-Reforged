/*==============================================================================
 = Class: ItemUtils
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Multimap;
import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.item.ItemMetal;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.material.ToolStats;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ItemUtils {

	/**
	 * Initializes the basic fields of an Item
	 * - translation key
	 * - registry name
	 * - creative tab
	 *
	 * @param item the instance of the item that is being initialized
	 * @param name The name of the item
	 * @param tab  The creative tab this item will be placed in
	 */
	public static void initItem(Item item, String name, CreativeTabs tab)
	{
		item.setTranslationKey(Metallurgy.MODID + "." + name);
		item.setRegistryName(Metallurgy.MODID, name);
		item.setCreativeTab(tab);
	}

	//method to check if stack is a specific tool Material
    @Deprecated
    public static boolean isItemStackASpecificToolMaterial(Metal metal, ItemStack toolStack, String... except) {

        Item item = toolStack.getItem();
        if (!toolStack.isEmpty() && item instanceof ItemTool) {
            ItemTool tool = (ItemTool) toolStack.getItem();
            boolean valid = tool.getToolMaterialName().equalsIgnoreCase(metal.getToolMaterial().name());
            for (String type : except) {
				String toolName = metal.getStats().getName() + "_" + type;
				if (tool.getTranslationKey().equalsIgnoreCase(toolName))
					valid = false;
			}
			return valid;
		}
		return false;
	}

	public static boolean equalsWildcard(ItemStack wild, ItemStack check)
	{
		if (wild.isEmpty() || check.isEmpty())
		{
			return check.equals(wild);
		}

		return wild.getItem() == check.getItem()
				&& (wild.getItemDamage() == OreDictionary.WILDCARD_VALUE
				|| check.getItemDamage() == OreDictionary.WILDCARD_VALUE
				|| wild.getItemDamage() == check
				.getItemDamage());
	}

	public static void editInventoryStackSize(NonNullList<ItemStack> inventory, int slot, int amount)
	{
		if (slot >= 0 && slot < inventory.size() && !inventory.get(slot).isEmpty())
		{
			inventory.get(slot).grow(amount);
			if (inventory.get(slot).getCount() <= 0)
				inventory.set(slot, ItemStack.EMPTY);
		}
	}

    /**
     * checks if an itemstack is made of a specific armor material
     *
     * @deprecated you can use {@link ItemUtils#getMetalFromItem(Item)} to achieve the same thing
     */
    @Deprecated
    public static boolean isItemStackSpecificArmorMaterial(Metal metal, ItemStack armor) {
        return !armor.isEmpty() && armor.getItem() instanceof ItemArmorBase && ((ItemArmorBase) armor.getItem()).getArmorMaterial().getName().equalsIgnoreCase(metal.getArmorMaterial().getName());
    }

	@SideOnly(Side.CLIENT)
	public static void registerCustomItemModel(Item item, int meta)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	public static void registerCustomItemModel(Item item, int meta, String subdir)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy.MODID + ":" + subdir + (!subdir.equals("") ? "/" : "") + item.getRegistryName().getPath(), "inventory"));
	}

	/**
	 * Gets the instance of a Metal from an Item
	 *
	 * @param item An Item instance
	 *
	 * @return The metal the parameter item is made of (null if it isn't made of any metal)
	 */
	public static Metal getMetalFromItem(Item item)
	{
		if (item instanceof ItemMetal)
		{
			ItemMetal metalItem = ((ItemMetal) item);

			for (Map.Entry<String, Metal> entry : ModMetals.metalMap.entrySet())
			{
				if (metalItem.getMetalStats().getName().equals(entry.getKey()))
				{
					return entry.getValue();
				}
			}
		}

		if (item instanceof ItemBlock)
		{
			return BlockUtils.getMetalFromBlock(((ItemBlock) item).getBlock());
		}

		return null;
	}

	/**
	 * Replace a modifier in the {@link Multimap} with a copy that's had {@code multiplier} applied to its value.
	 *
	 * @param modifierMultimap The MultiMap
	 * @param attribute        The attribute being modified
	 * @param id               The ID of the modifier
	 * @param amount           The Amount to add
	 *
	 * @author Choonster
	 */
	public static void editModifier(Multimap<String, AttributeModifier> modifierMultimap, IAttribute attribute, UUID id, double amount)
	{
		// Get the modifiers for the specified attribute
		final Collection<AttributeModifier> modifiers = modifierMultimap.get(attribute.getName());

		// Find the modifier with the specified ID, if any
		final Optional<AttributeModifier> modifierOptional = modifiers.stream().filter(attributeModifier -> attributeModifier.getID().equals(id)).findFirst();

		if (modifierOptional.isPresent())
		{ // If it exists,
			final AttributeModifier modifier = modifierOptional.get();
			modifiers.remove(modifier); // Remove it
			modifiers.add(new AttributeModifier(modifier.getID(), modifier.getName(), modifier.getAmount() + amount, modifier.getOperation())); // Add the new modifier
		}
	}

	public static void setToolAttributes(@Nonnull EntityEquipmentSlot equipmentSlot, Multimap<String, AttributeModifier> multimap, MetalStats metalStats)
	{
		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
		{
			ToolStats toolStats = metalStats.getToolStats();

			multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(Constants.ModAttributes.MAX_HEALTH, "Metallurgy Axe Max Health", toolStats.getMaxHealth(), 0));
			multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(Constants.ModAttributes.MOVEMENT_SPEED, "Metallurgy Axe Movement Speed", toolStats.getMovementSpeed(), 0));
			ItemUtils.editModifier(multimap, SharedMonsterAttributes.ATTACK_DAMAGE, Constants.ModAttributes.ATTACK_DAMAGE, toolStats.getAttackDamageAttribute());
			ItemUtils.editModifier(multimap, SharedMonsterAttributes.ATTACK_SPEED, Constants.ModAttributes.ATTACK_SPEED, toolStats.getAttackSpeed());
			multimap.put(EntityPlayer.REACH_DISTANCE.getName(), new AttributeModifier(Constants.ModAttributes.REACH_DISTANCE, "Metallurgy Axe Reach Distance", toolStats.getReachDistance(), 0));
		}
	}

	/**
	 * Checks an ItemStack has an oredicted Metallurgy Metal and returns it
	 *
	 * @param stack The ItemStack we're performing the check on
	 *
	 * @return The Metal that the stack is made of
	 */
	public static Metal getMetalFromOreDictStack(ItemStack stack)
	{
		if (stack.isEmpty())
			return null;

		int[] ids = OreDictionary.getOreIDs(stack);

		for (int id : ids)
		{
			String ore = OreDictionary.getOreName(id);

			String snakeOre = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, ore);
			String[] snakeArray = snakeOre.split("_");
			String metalName = String.join("_", ArrayUtils.removeElement(snakeArray, snakeArray[0]));
			return ModMetals.metalMap.get(metalName);
		}

		return null;
	}

}

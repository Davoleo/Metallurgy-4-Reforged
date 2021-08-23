/*==============================================================================
 = Class: ItemUtils
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Multimap;
import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import it.hurts.metallurgy_reforged.effect.all.TartariteEffect;
import it.hurts.metallurgy_reforged.item.IMetalItem;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.material.ToolStats;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

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
	public static boolean isItemStackASpecificToolMaterial(Metal metal, ItemStack toolStack, String... except)
	{

		Item item = toolStack.getItem();
		if (!toolStack.isEmpty() && item instanceof ItemTool)
		{
			ItemTool tool = (ItemTool) toolStack.getItem();
			boolean valid = tool.getToolMaterialName().equalsIgnoreCase(metal.getToolMaterial().name());
			for (String type : except)
			{
				String toolName = metal.getStats().getName() + "_" + type;
				if (tool.getTranslationKey().equalsIgnoreCase(toolName))
					valid = false;
			}
			return valid;
		}
		return false;
	}

	@SideOnly(Side.CLIENT)
	public static void buildEffectTooltip(List<String> tooltip, Set<BaseMetallurgyEffect> effects, ItemStack stack, @Nullable EnumTools toolType)
	{
		if (!effects.isEmpty())
		{
			boolean anyEnabled = false;

			for (BaseMetallurgyEffect effect : effects)
			{
				if (effect.isEnabled())
				{
					//System.out.println(effect.getTooltip().getLeft());
					tooltip.add(effect.getTooltip().getLeft());
					if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
						tooltip.add(effect.getTooltip().getRight());
					anyEnabled = true;
				}
			}

			Metal paragon = TartariteEffect.getParagonMetal(stack);
			if (paragon != null)
			{
				MetallurgyEffects.effects.row(paragon).forEach((category, effect) -> {

					boolean allEffect = category == EnumEffectCategory.ALL;
					boolean armorEffect = category == EnumEffectCategory.ARMOR && toolType == null;
					boolean toolEffect = ArrayUtils.contains(category.getTools(), toolType);

					if (allEffect || armorEffect || toolEffect)
					{
						tooltip.add(effect.getTooltip().getLeft());
						if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
							tooltip.add(effect.getTooltip().getRight());
					}
				});
			}

			if (anyEnabled && !Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
				tooltip.add(Utils.localizeEscapingCustomSequences("tooltip.metallurgy.press_ctrl"));
		}
	}

	private enum HarvestLevelFormatting {
		_1("\u2B51", TextFormatting.RED),
		_2("\u2B51\u2B51", TextFormatting.RED),
		_3("\u2B51\u2B51\u2B51", TextFormatting.GOLD),
		_4("\u2B51\u2B51\u2B51\u2B51", TextFormatting.YELLOW),
		_5("\u2B51\u2B51\u2B51\u2B51\u2B51", TextFormatting.DARK_GREEN),
		_6("\u2B51\u2B51\u2B51\u2B51\u2B51\u2B51", TextFormatting.AQUA),
		_7("\u2B51\u2B51\u2B51\u2B51\u2B51\u2B51\u2B51", TextFormatting.LIGHT_PURPLE);

		String stars;
		TextFormatting format;

		HarvestLevelFormatting(String stars, TextFormatting format)
		{
			this.stars = stars;
			this.format = format;
		}
	}

	@SideOnly(Side.CLIENT)
	public static void buildStatsTooltip(List<String> tooltip, EnumTools toolType, ToolStats stats, ItemStack toolStack)
	{
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			tooltip.add(Utils.localizeEscapingCustomSequences("tooltip.metallurgy.press_shift"));
		else
		{
			if (toolType == EnumTools.PICKAXE)
			{
				int harvest = stats.getHarvestLevel();
				HarvestLevelFormatting harvestFormatting = HarvestLevelFormatting.values()[harvest - 1];
				tooltip.add(Utils.localizeWithParameters("tooltip.metallurgy.stats.harvest_level", harvestFormatting.format + harvestFormatting.stars));
			}

			int maxDurability = toolStack.getMaxDamage();
			float useRatio = (maxDurability - toolStack.getItemDamage()) / (float) maxDurability;
			TextFormatting color;
			if (useRatio < 0.33F)
				color = TextFormatting.RED;
			else if (useRatio < 0.66)
				color = TextFormatting.YELLOW;
			else
				color = TextFormatting.GREEN;
			tooltip.add(Utils.localizeWithParameters("tooltip.metallurgy.stats.durability", color + String.valueOf(toolStack.getMaxDamage() - toolStack.getItemDamage()) + '/' + maxDurability));
		}
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
	 */
	public static boolean isItemStackSpecificArmorMaterial(Metal metal, ItemStack armor)
	{
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
	 * Checks if an item is made of a specific metal
	 *
	 * @param metal the metal the item could be made of
	 * @param item  the item to check
	 */
	@SafeVarargs
	public static boolean isMadeOfMetal(Metal metal, @Nonnull Item item, Class<? extends IMetalItem>... filters)
	{
		if (item instanceof IMetalItem)
		{
			MetalStats itemStats = ((IMetalItem) item).getMetalStats();
			if (itemStats != null)
			{
				if (itemStats.getName().equals(metal.toString()))
				{
					if (filters.length == 0)
						return true;
					else
					{
						for (Class<? extends IMetalItem> clazz : filters)
						{
							if (clazz.isInstance(item))
								return true;
						}
					}
				}
				else
					return false;
			}
		}

		return false;
	}

	/**
	 * Gets the instance of a Metal from an Item
	 *
	 * @param item An Item instance
	 *
	 * @return The metal the parameter item is made of (null if it isn't made of any metal)
	 */
	@Nullable
	public static Metal getMetalFromItem(Item item)
	{
		if (item instanceof IMetalItem)
		{
			MetalStats metalStats = ((IMetalItem) item).getMetalStats();
			if (metalStats != null)
				return ModMetals.metalMap.get(metalStats.getName());
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

	public static void removeEnchantment(Enchantment enchantment, ItemStack item)
	{
		int enchLevel = EnchantmentHelper.getEnchantmentLevel(enchantment, item);
		if (enchLevel > 0)
		{
			final Iterator<NBTBase> enchantIter = item.getEnchantmentTagList().iterator();
			while (enchantIter.hasNext())
			{
				if (Enchantment.getEnchantmentByID(((NBTTagCompound) enchantIter.next()).getShort("id")) == enchantment)
					enchantIter.remove();
			}
		}
	}

	/**
	 * Compacts ItemStacks in a list merging stacks with the same item and META<br>
	 * Resulting itemstack will have:
	 * <ul>
	 * <li>the sum of all the sizes as size</li>
	 * <li>NBT Tag compound which is a merged result of all the NBT compounds</li>
	 * </ul>
	 *
	 * @param itemStackList the list to be compacted
	 */
	public static List<ItemStack> compactStackList(List<ItemStack> itemStackList)
	{
		List<ItemStack> newList = new ArrayList<>(itemStackList.size());
		itemStackList.forEach(stack -> {
			boolean wasFound = false;
			for (ItemStack cachedStack : newList)
			{
				if (ItemStack.areItemsEqual(stack, cachedStack))
				{
					cachedStack.setCount(cachedStack.getCount() + stack.getCount());

					//If they both have NBT -> merge the NBT Data
					if (cachedStack.getTagCompound() != null && stack.getTagCompound() != null)
					{
						cachedStack.getTagCompound().merge(stack.getTagCompound());
					}
					else if (stack.getTagCompound() != null)
					{
						//If only the new stack has NBT data set that to the cached stack
						cachedStack.setTagCompound(stack.getTagCompound());
					}
					wasFound = true;
					break;
				}
			}

			if (!wasFound)
			{
				newList.add(stack);
			}
		});

		return newList;
	}

}

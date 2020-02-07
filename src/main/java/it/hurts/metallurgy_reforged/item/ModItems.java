/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModItems
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.item.gadget.ItemIgnatiusLighter;
import it.hurts.metallurgy_reforged.item.gadget.ItemInvisibilityShield;
import it.hurts.metallurgy_reforged.item.gadget.ItemPotashFertilizer;
import it.hurts.metallurgy_reforged.item.gadget.ItemVulcaniteLighter;
import it.hurts.metallurgy_reforged.item.gadget.gauntlet.ItemGauntlet;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ModItems {

	public static List<Item> itemList = new ArrayList<>();

	//Vanilla dust
	public static ItemOreDict dustGold = new ItemOreDict("gold_dust", "dustGold", MetallurgyTabs.tabDust, ModItems.itemList);
	public static ItemOreDict dustIron = new ItemOreDict("iron_dust", "dustIron", MetallurgyTabs.tabDust, ModItems.itemList);

	//Metallurgy dusts
	public static ItemOreDict bitumen = new ItemOreDict("bitumen", "dustBitumen", MetallurgyTabs.tabDust, ModItems.itemList).setTooltip(Constants.BITUMEN);
	public static ItemOreDict tar = new ItemOreDict("tar", "slimeball", MetallurgyTabs.tabDust, ModItems.itemList);
	public static ItemOreDict potash = new ItemOreDict("potash", "dustPotash", MetallurgyTabs.tabDust, ModItems.itemList);
	public static ItemOreDict sulfur = new ItemOreDict("sulfur_dust", "dustSulfur", MetallurgyTabs.tabDust, ModItems.itemList);
	public static ItemOreDict dustThermite = new ItemOreDict("thermite_dust", "dustThermite", MetallurgyTabs.tabDust, ModItems.itemList);
	public static ItemOreDict phosphorus = new ItemOreDict("phosphorus", "dustPhosphorus", MetallurgyTabs.tabDust, ModItems.itemList);

	public static ItemPotashFertilizer dustPotash = new ItemPotashFertilizer();

	//Gadgets
	public static ItemIgnatiusLighter flintAndIgnatius = new ItemIgnatiusLighter("flint_and_ignatius");
	public static ItemVulcaniteLighter flintAndVulcanite = new ItemVulcaniteLighter("flint_and_vulcanite");
	public static ItemGauntlet gauntlet = new ItemGauntlet("rubracium_gauntlet");
	public static ItemInvisibilityShield invisibilityShield = new ItemInvisibilityShield();

	//Wiki Link Item ------------------------------------------------
	public static Item wiki = new Item() {
		@Override
		public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn)
		{
			if (worldIn.isRemote)
			{
				TextComponentString link = new TextComponentString("https://github.com/Davoleo/Metallurgy-4-Reforged/wiki");
				playerIn.sendMessage(link.setStyle(link.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Davoleo/Metallurgy-4-Reforged/wiki")).setColor(TextFormatting.BLUE)));
			}
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
	};

	static
	{
		ItemUtils.initItem(wiki, "wiki", MetallurgyTabs.tabSpecial, itemList);
	}

	public static void register(IForgeRegistry<Item> registry)
	{
		for (Item item : itemList)
			registry.register(item);
	}

	@SideOnly(Side.CLIENT)
	public static void registerModels()
	{
		for (Item item : itemList)
		{
			if (item instanceof IHasModel)
				ItemUtils.registerCustomItemModel(item, 0, ((IHasModel) item).getCategory());
		}

		ItemUtils.registerCustomItemModel(wiki, 0);
	}

}
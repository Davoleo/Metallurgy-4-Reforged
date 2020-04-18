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

import it.hurts.metallurgy_reforged.item.gadget.*;
import it.hurts.metallurgy_reforged.item.gadget.gauntlet.ItemGauntlet;
import it.hurts.metallurgy_reforged.util.Constants;
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

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ModItems {

	public static List<Item> itemList = new ArrayList<>();

	//Vanilla dust
	public static ItemBase dustGold = new ItemBase("gold_dust", MetallurgyTabs.tabDust);
	public static ItemBase dustIron = new ItemBase("iron_dust", MetallurgyTabs.tabDust);

	//Metallurgy dusts
	//TODO : INITIALIZE OREDICT KEYS CORRECTLY (Interface (?))
	public static ItemBase bitumen = new ItemBase("bitumen", MetallurgyTabs.tabDust).setTooltip(Constants.BITUMEN);
	public static ItemBase tar = new ItemBase("tar", MetallurgyTabs.tabDust);
	public static ItemBase potash = new ItemBase("potash", MetallurgyTabs.tabDust);
	public static ItemBase sulfur = new ItemBase("sulfur_dust", MetallurgyTabs.tabDust);
	public static ItemBase dustThermite = new ItemBase("thermite_dust", MetallurgyTabs.tabDust).setTooltip(Constants.THERMITE_DUST);
	public static ItemBase phosphorus = new ItemBase("phosphorus", MetallurgyTabs.tabDust);

	public static ItemPotashFertilizer dustPotash = new ItemPotashFertilizer();

	//Gadgets
	public static ItemIgnatiusLighter flintAndIgnatius = new ItemIgnatiusLighter("flint_and_ignatius");
	public static ItemVulcaniteLighter flintAndVulcanite = new ItemVulcaniteLighter("flint_and_vulcanite");
	public static ItemGauntlet gauntlet = new ItemGauntlet("rubracium_gauntlet");
	public static ItemInvisibilityShield invisibilityShield = new ItemInvisibilityShield();
	public static ItemOreDetector oreDetector = new ItemOreDetector();

	//Wiki Link Item ------------------------------------------------
	public static ItemBase wiki = new ItemBase("wiki", MetallurgyTabs.tabSpecial) {
		@Override
		public ActionResult<ItemStack> onItemRightClick(World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
		{
			if (worldIn.isRemote)
			{
				TextComponentString link = new TextComponentString("https://github.com/Davoleo/Metallurgy-4-Reforged/wiki");
				playerIn.sendMessage(link.setStyle(link.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Davoleo/Metallurgy-4-Reforged/wiki")).setColor(TextFormatting.BLUE)));
			}
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
	};

	@SideOnly(Side.CLIENT)
	public static void registerModels()
	{
		for (Item item : itemList)
		{
			//ItemUtils.registerCustomItemModel(item, 0, ((IHasModel) item).getCategory());
		}

		ItemUtils.registerCustomItemModel(wiki, 0);
	}

}
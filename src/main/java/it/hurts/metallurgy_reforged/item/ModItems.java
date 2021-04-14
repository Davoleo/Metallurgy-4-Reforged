/*==============================================================================
 = Class: ModItems
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.item.gadget.*;
import it.hurts.metallurgy_reforged.item.gadget.gauntlet.ItemGauntlet;
import it.hurts.metallurgy_reforged.item.gadget.shield.*;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static List<ItemExtra> extraItems = new ArrayList<>();

    //Vanilla dust
    public static ItemBase dustGold = new ItemExtra("gold_dust", MetallurgyTabs.tabDust);
    public static ItemBase dustIron = new ItemExtra("iron_dust", MetallurgyTabs.tabDust);

    //Metallurgy dusts
    public static ItemBase bitumen = new ItemExtra("bitumen", MetallurgyTabs.tabDust).setTooltip(Constants.BITUMEN);
    public static ItemBase tar = new ItemExtra("tar", MetallurgyTabs.tabDust);
    public static ItemBase potash = new ItemExtra("potash", MetallurgyTabs.tabDust);
    public static ItemBase sulfur = new ItemExtra("sulfur_dust", MetallurgyTabs.tabDust);
    public static ItemBase dustThermite = new ItemExtra("thermite_dust", MetallurgyTabs.tabDust).setTooltip(Constants.THERMITE_DUST);
    public static ItemBase phosphorus = new ItemExtra("phosphorus", MetallurgyTabs.tabDust);

	//Gadgets
	public static ItemPotashFertilizer dustPotash = new ItemPotashFertilizer();
	public static ItemIgnatiusLighter flintAndIgnatius = new ItemIgnatiusLighter("flint_and_ignatius");
	public static ItemVulcaniteLighter flintAndVulcanite = new ItemVulcaniteLighter("flint_and_vulcanite");
	public static ItemGauntlet gauntlet = new ItemGauntlet("rubracium_gauntlet");
	public static ItemOreDetector oreDetector = new ItemOreDetector();
	public static ItemEtheriumMonocle etheriumMonocle = new ItemEtheriumMonocle();
	public static ItemKnuckles brassKnuckles = new ItemKnuckles("brass_knuckles");
	public static ItemElectrumMagnet electrumMagnet = new ItemElectrumMagnet();
	//Shields
	public static ItemLemuriteShield invisibilityShield = new ItemLemuriteShield();
	//public static ItemSanguiniteShield witherShield = new ItemSanguiniteShield();
	public static ItemZincBuckler zincBuckler = new ItemZincBuckler();
	public static ItemVulcaniteBuckler explosiveBuckler = new ItemVulcaniteBuckler();
	public static ItemCeruclaseShield ceruclaseShield = new ItemCeruclaseShield();
	public static ItemOrichalcumBuckler orichalcumBuckler = new ItemOrichalcumBuckler();

    //Wiki Link Item ------------------------------------------------
    public static ItemBase wiki = new ItemExtra("wiki", MetallurgyTabs.tabSpecial) {
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

}
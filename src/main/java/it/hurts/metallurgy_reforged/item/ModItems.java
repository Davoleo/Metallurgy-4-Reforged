/*==============================================================================
 = Class: ModItems
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
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
    public static final ItemBase GOLD_DUST = new ItemExtra("gold_dust", MetallurgyTabs.tabDust);
    public static final ItemBase IRON_DUST = new ItemExtra("iron_dust", MetallurgyTabs.tabDust);

    //Metallurgy dusts
    public static final ItemBase BITUMEN = new ItemExtra("bitumen", MetallurgyTabs.tabDust).setTooltip(Constants.BITUMEN);
    public static final ItemBase TAR = new ItemExtra("tar", MetallurgyTabs.tabDust);
    public static final ItemBase POTASH = new ItemExtra("potash", MetallurgyTabs.tabDust);
    public static final ItemBase SULFUR = new ItemExtra("sulfur_dust", MetallurgyTabs.tabDust);
    public static final ItemBase THERMITE_DUST = new ItemExtra("thermite_dust", MetallurgyTabs.tabDust).setTooltip(Constants.THERMITE_DUST);

    public static final ItemBase IGNATIUS_FUEL = new ItemExtra("ignatius_fuel", MetallurgyTabs.tabSpecial).setTooltip(Constants.IGNATIUS_FUEL);

    public static final ItemBase PHOSPHORUS = new ItemExtra("phosphorus", MetallurgyTabs.tabDust);

    //Gadgets
    public static final ItemPotashFertilizer DUST_POTASH = new ItemPotashFertilizer();
    public static final ItemIgnatiusLighter FLINT_AND_IGNATIUS = new ItemIgnatiusLighter("flint_and_ignatius");
    public static final ItemVulcaniteLighter FLINT_AND_VULCANITE = new ItemVulcaniteLighter("flint_and_vulcanite");
    public static final ItemGauntlet GAUNTLET = new ItemGauntlet("rubracium_gauntlet");
    public static final ItemOreDetector ORE_DETECTOR = new ItemOreDetector();
    public static final ItemEtheriumMonocle ETHERIUM_MONOCLE = new ItemEtheriumMonocle();
    public static final ItemKnuckles BRASS_KNUCKLES = new ItemKnuckles("brass_knuckles");
    public static final ItemElectrumMagnet ELECTRUM_MAGNET = new ItemElectrumMagnet();
    //Shields
    public static final ItemLemuriteShield INVISIBILITY_SHIELD = new ItemLemuriteShield();
    //public static final ItemSanguiniteShield witherShield = new ItemSanguiniteShield();
    public static final ItemZincBuckler ZINC_BUCKLER = new ItemZincBuckler();
    public static final ItemVulcaniteBuckler VULCANITE_BUCKLER = new ItemVulcaniteBuckler();
    public static final ItemCeruclaseShield CERUCLASE_SHIELD = new ItemCeruclaseShield();
    public static final ItemOrichalcumBuckler ORICHALCUM_BUCKLER = new ItemOrichalcumBuckler();

    //Wiki Link Item ------------------------------------------------
    public static final ItemBase WIKI = new ItemExtra("wiki", MetallurgyTabs.tabSpecial) {
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
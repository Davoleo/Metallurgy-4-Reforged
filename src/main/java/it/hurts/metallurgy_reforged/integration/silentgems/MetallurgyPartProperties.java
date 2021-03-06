/*==============================================================================
 = Class: MetallurgyPartProperties
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.silentgems;

import com.google.common.base.CaseFormat;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.silentchaos512.gems.api.lib.EnumMaterialTier;
import net.silentchaos512.gems.api.tool.part.IPartProperties;

import java.util.stream.IntStream;

public class MetallurgyPartProperties implements IPartProperties {

    private MetalStats stats;
    private Item ingot;

    public MetallurgyPartProperties(MetalStats metalStats, Item ingot)
    {
        this.stats = metalStats;
        this.ingot = ingot;
    }

    public MetalStats getMetalStats()
    {
        return stats;
    }

    @Override
    public String getName()
    {
        return Utils.localizeEscapingCustomSequences("material.metallurgy." + stats.getName() + ".name");
    }

    @Override
    public String getNamePrefix()
    {
        return "";
    }

    @Override
    public int getColor()
    {
        return stats.getColorHex();
    }

    @Override
    public EnumMaterialTier getTier()
    {
        //A number that is:
        //- 0 in case the harvest level is between 1 and 3
        //- 1 in case the harvest level is between 4 and 6
        //- 2 in case the harvest level is more than 6
        int tier = (stats.getToolStats().getHarvestLevel() - 1) / 3;

        if (tier > 2)
            tier = 2;

        return EnumMaterialTier.values()[tier];
    }

    @Override
    public int getDurability()
    {
        return stats.getToolStats().getMaxUses();
    }

    @Override
    public float getMiningSpeed()
    {
        return stats.getToolStats().getEfficiency();
    }

    @Override
    public int getHarvestLevel()
    {
        return stats.getToolStats().getHarvestLevel();
    }

    @Override
    public float getMeleeDamage()
    {
        return stats.getToolStats().getDamage();
    }

    //Unused as of 15/07/2020
    @Override
    public float getMagicDamage()
    {
        return stats.getToolStats().getToolMagic() / 6F;
    }

    @Override
    public float getMeleeSpeed()
    {
        return stats.getToolStats().getEfficiency() / 6F;
    }

    @Override
    public int getEnchantability()
    {
        return stats.getToolStats().getToolMagic();
    }

    @Override
    public float getChargeSpeed()
    {
        return 3F;
    }

    @Override
    public float getProtection()
    {
        if (stats.getArmorStats() != null)
        {
            return IntStream.of(stats.getArmorStats().getDamageReduction()).sum();
        }
        else
            return 1;
    }

    @Override
    public ItemStack getCraftingStack()
    {
        return new ItemStack(ingot, 1);
    }

    @Override
    public String getCraftingOreName()
    {
        return "ingot" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, stats.getName());
    }

}

/*==============================================================================
 = Class: ItemPickaxeBase
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.tool;

import com.google.common.collect.Multimap;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.proxy.ClientProxy;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemPickaxeBase extends ItemPickaxe implements IToolEffect {

    private final Set<BaseMetallurgyEffect> effects = new HashSet<>();
    private Enchantment enchantment = null;
    private int enchantmentLevel = -1;

    private final MetalStats metalStats;

    public ItemPickaxeBase(ToolMaterial material, MetalStats metalStats)
    {
        super(material);
        this.metalStats = metalStats;
        ItemUtils.initItem(this, metalStats.getName() + "_pickaxe", MetallurgyTabs.tabTool);
    }

    @Override
    public EnumTools getToolClass()
    {
        return EnumTools.PICKAXE;
    }

    @Override
    public MetalStats getMetalStats()
    {
        return metalStats;
    }

    @Override
    public void addEffect(BaseMetallurgyEffect effect)
    {
        this.effects.add(effect);
    }

    @Override
    public int getMaxDamage(@Nonnull ItemStack stack)
    {
        NBTTagCompound compound = stack.getTagCompound();

        if (compound != null && compound.hasKey("durability_boost"))
        {
            float dBoost = compound.getFloat("durability_boost");
            return (int) (dBoost * super.getMaxDamage(stack));
        }

        return super.getMaxDamage(stack);
    }

    @Override
    public int getHarvestLevel(ItemStack stack, @Nonnull String toolClass, @Nullable EntityPlayer player, @Nullable IBlockState blockState)
    {
        NBTTagCompound data = stack.getTagCompound();

        if (data != null && data.hasKey("harvest_boost"))
            return super.getHarvestLevel(stack, toolClass, player, blockState) + data.getInteger("harvest_boost");

        return super.getHarvestLevel(stack, toolClass, player, blockState);
    }

    public void setEnchanted(Enchantment enchantment, int enchantmentLevel)
    {
        this.enchantment = enchantment;
        this.enchantmentLevel = enchantmentLevel;
    }

    @Override
    public boolean getIsRepairable(@Nonnull ItemStack toRepair, @Nonnull ItemStack repair)
    {
        Metal metal = ModMetals.metalMap.get(metalStats.getName());
        return (GeneralConfig.enableAnvilToolRepair && ItemUtils.equalsWildcard(new ItemStack(metal.getIngot()), repair))
                || super.getIsRepairable(toRepair, repair);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn)
    {
        ItemUtils.buildStatsTooltip(tooltip, EnumTools.PICKAXE, this.metalStats.getToolStats(), stack);
        ItemUtils.buildEffectTooltip(tooltip, effects);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        effects.forEach(effect -> {
            if (effect.isEnabled())
                effect.rightClickHandler(worldIn, playerIn, handIn);
        });
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onUpdate(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected)
    {
        effects.forEach(effect -> {
            if (effect.isEnabled())
                effect.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
        });
    }

    @SideOnly(Side.CLIENT)
    @Nullable
    @Override
    public FontRenderer getFontRenderer(@Nonnull ItemStack stack)
    {
        return ClientProxy.fontRenderer;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            ItemStack enchantedPA = new ItemStack(this);
            if (enchantment != null)
            {
                enchantedPA.addEnchantment(enchantment, enchantmentLevel);
            }
            items.add(enchantedPA);
        }
    }

    @Nonnull
    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(@Nonnull EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
        ItemUtils.setToolAttributes(equipmentSlot, multimap, metalStats);
        return multimap;
    }

}

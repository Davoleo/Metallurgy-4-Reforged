package it.hurts.metallurgy_reforged.item.tool;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date: 20/08/2018
 * Hour: 13.16
 * Project: Metallurgy 5
 * Copyright - © - Davoleo - 2018
 * 
 * Reworked by ItHurtsLikeHell
 **************************************************/

public class ItemAxeBase extends ItemAxe {

    private String name;
    private String tooltip;
    private Enchantment enchantment;
	private int enchantmentLevel;

    public ItemAxeBase(ToolMaterial material, String name)
    {
        this(material, name, null, -1);
    }
    
    public ItemAxeBase(ToolMaterial material, String name, Enchantment enchantment, int enchantmentLevel){
        super(material, material.getAttackDamage() + 2, -2.5F -(material.getAttackDamage()/10));
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(MetallurgyTabs.tabTool);
        this.name = name;
        this.enchantment = enchantment;
        this.enchantmentLevel = enchantmentLevel;
        ModTools.toolList.add(this);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
	public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items)
    {
        if(this.isInCreativeTab(tab)) {
            ItemStack enchantedAxe = new ItemStack(this);
            if(enchantment != null) {
                enchantedAxe.addEnchantment(enchantment, enchantmentLevel);
            }
            items.add(enchantedAxe);
        }
	}

    public ItemAxeBase setTooltip(String tooltip)
    {
        this.tooltip = tooltip;
        return this;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if(this.tooltip != null && ModTools.isAxeEffectActive(this))
            tooltip.add(this.tooltip);
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy.MODID + ":tool/" + name, "inventory"));
    }

}

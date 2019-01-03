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
        super(material, material.getAttackDamage() + 2, -2.5F -(material.getAttackDamage()/10));
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(MetallurgyTabs.tabTool);
        this.name = name;
        ModTools.toolList.add(this);
    }
    
    public ItemAxeBase(ToolMaterial material, String name,  String tooltip)
    {
       this(material, name, tooltip, null, 0);
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
    
    public ItemAxeBase(ToolMaterial material, String name, String tooltip, Enchantment enchantment, int enchantmentLevel){
        super(material, material.getAttackDamage() + 2, -2.5F -(material.getAttackDamage()/10));
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(MetallurgyTabs.tabTool);
        this.tooltip = tooltip;
        this.name = name;
        this.enchantment = enchantment;
        this.enchantmentLevel = enchantmentLevel;
        ModTools.toolList.add(this);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(this.isInCreativeTab(tab)) {
            ItemStack enchantedAxe = new ItemStack(this);
            if(enchantment != null) {
                enchantedAxe.addEnchantment(enchantment, enchantmentLevel);
            }
            items.add(enchantedAxe);
        }
	}

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if(this.tooltip != null)
            tooltip.add(this.tooltip);
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy.MODID + ":tool/" + name, "inventory"));
    }

}

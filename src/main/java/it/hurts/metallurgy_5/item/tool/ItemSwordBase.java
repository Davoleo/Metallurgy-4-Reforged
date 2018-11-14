package it.hurts.metallurgy_5.item.tool;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.util.MetallurgyTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
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
 * Hour: 11.25
 * Project: Metallurgy 5
 * Copyright - © - Davoleo - 2018
 * 
 * Reworked by ItHurtsLikeHell
 **************************************************/

public class ItemSwordBase extends ItemSword {

    private String name;
    private String tooltip;
	private Enchantment enchantment;
	private int enchantmentLevel;

    public ItemSwordBase(ToolMaterial material, String name)
    {
        this(material, name, "", null, 0);
    }

    public ItemSwordBase(ToolMaterial material, String name,  String tooltip)
    {
       this(material, name, tooltip, null, 0);
    }
    
    public ItemSwordBase(ToolMaterial material, String name, String tooltip, Enchantment enchantment, int enchantmentLevel)
    {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.name = name;
        this.tooltip = tooltip;
        this.enchantment = enchantment;
        this.enchantmentLevel = enchantmentLevel;
        setCreativeTab(MetallurgyTabs.tabTool);
        ModTools.toolList.add(this);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(this.isInCreativeTab(tab)) {
            ItemStack enchantedSword = new ItemStack(this);
            if(enchantment != null) {
                enchantedSword.addEnchantment(enchantment, enchantmentLevel);
            }
            items.add(enchantedSword);
        }
	}

	@Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(this.tooltip);
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy_5.MODID + ":tool/" + name, "inventory"));
    }
}

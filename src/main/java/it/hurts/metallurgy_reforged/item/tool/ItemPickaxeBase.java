package it.hurts.metallurgy_reforged.item.tool;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date: 20/08/2018
 * Hour: 13.23
 * Project: Metallurgy 5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemPickaxeBase extends ItemPickaxe {

    private String name;
    private String tooltip;

    public ItemPickaxeBase(ToolMaterial material, String name)
        {
            this(material, name, "");
        }

    public ItemPickaxeBase(ToolMaterial material, String name, String tooltip)
        {
            super(material);
            setUnlocalizedName(name);
        setRegistryName(name);
        this.name = name;
        this.tooltip = tooltip;
        setCreativeTab(MetallurgyTabs.tabTool);
        ModTools.toolList.add(this);
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy.MODID + ":tool/" + name, "inventory"));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(this.tooltip);
    }
}

package it.hurts.metallurgy_5.item.tool;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.util.MetallurgyTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*************************************************
 * Author: Davoleo
 * Date: 20/08/2018
 * Hour: 13.20
 * Project: Metallurgy 5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemHoeBase extends ItemHoe {

    private String name;

    public ItemHoeBase(ToolMaterial material, String name)
    {
        super(material);
        setUnlocalizedName(name)
        setRegistryName(name);
        this.name = name;
        setCreativeTab(MetallurgyTabs.tabTool);
        ModTools.toolList.add(this);
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy_5.MODID + ":tool/" + name, "inventory"));
    }

}

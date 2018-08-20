package hurts.metallurgy_5.item.tool;

import hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.item.ItemPickaxe;

/*************************************************
 * Author: Davoleo
 * Date: 20/08/2018
 * Hour: 13.23
 * Project: Metallurgy 5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemPickaxeBase extends ItemPickaxe {

    private String name;

    public ItemPickaxeBase(ToolMaterial material, String name)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        this.name = name;
        setCreativeTab(Metallurgy_5.tabTool);
    }

    public void registerItemModel()
    {
        Metallurgy_5.proxy.registerItemRenderer(this, 0, name);
    }

}

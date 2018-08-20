package hurts.metallurgy_5.item.tool;

import hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.item.ItemSpade;

/*************************************************
 * Author: Davoleo
 * Date: 20/08/2018
 * Hour: 13.26
 * Project: Metallurgy 5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemShovelBase extends ItemSpade {

    private String name;

    public ItemShovelBase(ToolMaterial material, String name)
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

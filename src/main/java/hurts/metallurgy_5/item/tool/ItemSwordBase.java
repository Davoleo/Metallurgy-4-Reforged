package hurts.metallurgy_5.item.tool;

import hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

/*************************************************
 * Author: Davoleo
 * Date: 20/08/2018
 * Hour: 11.25
 * Project: Metallurgy 5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemSwordBase extends ItemSword {

    private String name;

    public ItemSwordBase(ToolMaterial material, String name)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        this.name = name;
        setCreativeTab(Metallurgy_5.tabTool);
    }

    public void registerItemModel(Item item)
    {
        Metallurgy_5.proxy.registerItemRenderer(this, 0, name);
    }

}

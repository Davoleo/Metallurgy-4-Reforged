package hurts.metallurgy_5.item.tool;

import hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.item.ItemAxe;

/*************************************************
 * Author: Davoleo
 * Date: 20/08/2018
 * Hour: 13.16
 * Project: Metallurgy 5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemAxeBase extends ItemAxe {

    private String name;

    public ItemAxeBase(ToolMaterial material, String name)
    {
        super(material, material.getAttackDamage() + 2F, -3.1F);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Metallurgy_5.tabTool);
        this.name = name;
    }

    public void registerItemModel()
    {
        Metallurgy_5.proxy.registerItemRenderer(this, 0, name);
    }

}

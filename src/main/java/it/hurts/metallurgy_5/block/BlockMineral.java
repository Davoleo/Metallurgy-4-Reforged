package it.hurts.metallurgy_5.block;

import it.hurts.metallurgy_5.util.MetallurgyTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

/*************************************************
 * Author: Davoleo
 * Date: 21/08/2018
 * Hour: 19.41
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockMineral extends BlockOre{

    private Item item;

    public BlockMineral(String name, String oreName, Item drop)
    {
        super(name, oreName);
        setCreativeTab(MetallurgyTabs.tabOre);
        item = drop;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1 + random.nextInt(3);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune)
    {
        Item item;
        item = this.item;
        return item;
    }

}

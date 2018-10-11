package it.hurts.metallurgy_5.gui;

import it.hurts.metallurgy_5.container.ContainerAlloyer;
import it.hurts.metallurgy_5.container.ContainerCrusher;
import it.hurts.metallurgy_5.gui.GuiCrusher;
import it.hurts.metallurgy_5.tileentity.TileEntityAlloyer;
import it.hurts.metallurgy_5.tileentity.TileEntityCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/*************************************************
 * Author: Davoleo
 * Date: 03/09/2018
 * Hour: 22.40
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class GuiHandler implements IGuiHandler {

    public static final int CRUSHER = 0;
    public static final int ALLOYER = 1;

    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case CRUSHER:
                return new ContainerCrusher(player.inventory, (TileEntityCrusher)world.getTileEntity(new BlockPos(x,y,z)));
            case ALLOYER:
                return new ContainerAlloyer(player.inventory, (TileEntityAlloyer)world.getTileEntity(new BlockPos(x,y,z)));
            default:
                return null;
        }
    }


    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case CRUSHER:
                return new GuiCrusher(player.inventory, (TileEntityCrusher)world.getTileEntity(new BlockPos(x,y,z)));
            case ALLOYER:
                return new GuiAlloyer(player.inventory, (TileEntityAlloyer)world.getTileEntity(new BlockPos(x,y,z)));
            default:
                return null;
        }
    }
}

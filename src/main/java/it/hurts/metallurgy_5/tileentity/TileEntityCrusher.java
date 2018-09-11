package it.hurts.metallurgy_5.tileentity;

import it.hurts.metallurgy_5.block.BlockCrusher;
import it.hurts.metallurgy_5.util.recipe.BlockCrusherRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date: 01/09/2018
 * Hour: 22.47
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

//VOGLIO MORIRE EDITION

//ticking tile entity
public class TileEntityCrusher extends TileEntity implements ITickable {

    private ItemStackHandler inventory = new ItemStackHandler(3);
    private String customName;
    private ItemStack crushing = ItemStack.EMPTY;

    private int burnTime;
    private int currentBurnTime;
    private int crushTime;
    private int totalCrushTime = 200;

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        else return false;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.inventory;
        return super.getCapability(capability, facing);
    }

    public boolean hasCustomName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.crusher";
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName())
                : new TextComponentTranslation(this.getName());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        this.burnTime = compound.getInteger("burn_time");
        this.crushTime = compound.getInteger("crush_time");
        this.totalCrushTime = compound.getInteger("total_crush_time");
        this.currentBurnTime = getItemBurnTime(this.inventory.getStackInSlot(2));

        if(compound.hasKey("CustomName", 8))
            this.setCustomName(compound.getString("CustomName"));

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        if(inventory == null)
        {
            System.out.println("WARNING THE INVENTORY CANNOT BE SAVED TO NBT, WHAT'YA DID LITTLE BITCH!");
        }
        super.writeToNBT(compound);
        compound.setInteger("burn_time", (short)this.burnTime);
        compound.setInteger("crush_time", (short)this.crushTime);
        compound.setInteger("total_crush_time", (short)this.totalCrushTime);
        compound.setTag("inventory", inventory.serializeNBT());

        if(this.hasCustomName())
            compound.setString("custom_name", this.customName);

        return compound;

    }

    public boolean isBurning()
    {
        return this.burnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(TileEntityCrusher te)
    {
        return te.getField(0) > 0;
    }

    public void update()
    {
        //Fuel Usage
        if(this.isBurning())
        {
            --this.burnTime;
            BlockCrusher.setState(true, world, pos);
        }

        ItemStack input = inventory.getStackInSlot(0);
        ItemStack fuel = this.inventory.getStackInSlot(1);

        if(this.isBurning() || !fuel.isEmpty() && !this.inventory.getStackInSlot(0).isEmpty())
        {
         if(!this.isBurning() && this.canCrush())
         {
             this.burnTime = getItemBurnTime(fuel);
             this.currentBurnTime = burnTime;

             if(this.isBurning() && !fuel.isEmpty())
             {
                 Item item = fuel.getItem();
                 fuel.shrink(1);

                 if(fuel.isEmpty())
                 {
                     ItemStack item1 = item.getContainerItem(fuel);
                     this.inventory.setStackInSlot(1, item1);
                 }
             }
         }

        }

        if(this.isBurning() && this.canCrush() && crushTime > 0)
        {
            crushTime++;
            if(crushTime == totalCrushTime)
            {
                if(inventory.getStackInSlot(2).getCount() > 0)
                {
                    inventory.getStackInSlot(2).grow(2);
                }
                else
                {
                    inventory.insertItem(2, crushing, false);
                }

                crushing = ItemStack.EMPTY;
                crushTime = 0;
            }
        }
        else
        {
            if(this.canCrush() && this.isBurning())
            {
                ItemStack output = BlockCrusherRecipes.getInstance().getCrushingResult(input);
                if(!output.isEmpty())
                {
                    crushing = output;
                    crushTime++;
                    input.shrink(1);
                    inventory.setStackInSlot(0, input);
                }
            }
        }
    }


private boolean canCrush ()
{
    if((this.inventory.getStackInSlot(0)).isEmpty())
        return false;
    else
    {
        ItemStack result = BlockCrusherRecipes.getInstance().getCrushingResult(this.inventory.getStackInSlot(0));
        if(result.isEmpty())
            return false;
        else
        {
            ItemStack output = this.inventory.getStackInSlot(2);
            if(output.isEmpty())
                return true;
            if(!output.isItemEqual(result))
                return false;
            int limit = output.getCount() + result.getCount();
            return limit <= 64 && limit <= output.getMaxStackSize();

        }
    }
}

public static int getItemBurnTime(ItemStack fuel)
{
    if(fuel.isEmpty())
        return 0;
    else
    {
        Item item = fuel.getItem();

        if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR)
        {
            Block block = Block.getBlockFromItem(item);

            if(block == Blocks.WOODEN_SLAB)
                return 150;
            if(block.getDefaultState().getMaterial() == Material.WOOD)
                return 300;
            if(block == Blocks.COAL_BLOCK)
                return 16000;
        }

        if (item instanceof ItemTool && "WOOD".equals(((ItemTool) item).getToolMaterialName()))
            return 200;
        if (item instanceof ItemSword && "WOOD".equals(((ItemSword) item).getToolMaterialName()))
            return 200;
        if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe) item).getMaterialName()))
            return 200;
        if (item == Items.STICK)
            return 100;
        if (item == Items.COAL)
            return  1600;
        if (item == Items.LAVA_BUCKET)
            return 20000;
        if (item == Item.getItemFromBlock(Blocks.SAPLING))
            return 100;
        if (item == Items.BLAZE_ROD)
            return 2400;

        return ForgeEventFactory.getItemBurnTime(fuel);
    }
}

    public static boolean isItemFuel(ItemStack fuel)
    {
        return getItemBurnTime(fuel) > 0;
    }

    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.pos)==this && player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

public int getField(int id)
{
    switch (id)
    {
        case 0:
            return this.burnTime;
        case 1:
            return this.currentBurnTime;
        case 2:
            return this.crushTime;
        case 3:
            return this.totalCrushTime;
        default:
            return 0;
    }
}

public void setField(int id, int value)
{
    switch (id)
    {
        case 0:
            this.burnTime = value;
            break;
        case 1:
            this.currentBurnTime = value;
            break;
        case 2:
            this.crushTime = value;
            break;
        case 3:
            this.totalCrushTime = value;
    }
}

//provate pure a pensare di essere assolti, siete lo stesso coinvolti

}
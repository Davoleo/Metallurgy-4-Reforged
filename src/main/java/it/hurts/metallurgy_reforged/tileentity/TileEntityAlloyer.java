/*==============================================================================
 = Class: TileEntityAlloyer
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.tileentity;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import it.hurts.metallurgy_reforged.block.BlockAlloyer;
import it.hurts.metallurgy_reforged.container.ContainerAlloyer;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.recipe.AlloyerRecipes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.minecraft.tileentity.TileEntityFurnace.getItemBurnTime;

@Optional.Interface(modid = "immersiveengineering", iface = "blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler$IExternalHeatable")
public class TileEntityAlloyer extends TileEntityLockable implements ITickable, ISidedInventory, ExternalHeaterHandler.IExternalHeatable {

    private static final int[] slotsTop = Slots.INPUT_SLOT.slots();
    private static final int[] slotsBottom = Slots.OUTPUT_SLOT.slots();
    private static final int[] slotsSides = Slots.FUEL_SLOT.slots();
    private final IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
    private final IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
    private final IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);
    private NonNullList<ItemStack> inventory = NonNullList.withSize(4, ItemStack.EMPTY);

    private String customName;

    public static final int TOTAL_ALLOYING_TIME = 140;
    private int burnTime;
    private int totalBurnTime;
    private int alloyingTime;

    private boolean isPoweredByThermite;

    public static boolean isItemFuel(ItemStack fuel)
    {
        return getItemBurnTime(fuel) > 0;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }

    @Override
    public int getSizeInventory()
    {
        return this.inventory.size();
    }

    /**
     * @return true if the inventory is empty
     */
    @Override
    public boolean isEmpty()
    {
        for (ItemStack stack : this.inventory)
            if (!stack.isEmpty())
                return false;
        return true;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.inventory.get(index);
    }

    @Nonnull
    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }

    @Nonnull
    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }

    @Override
    public void setInventorySlotContents(int index, @Nonnull ItemStack stack)
    {
        ItemStack itemstack = this.inventory.get(index);
        boolean isEmptyOrNotEqualToTheSlotStack = stack.isEmpty() || !stack.isItemEqual(itemstack) || !ItemStack.areItemStackTagsEqual(stack, itemstack);
        //copies the stack you want to insert into the slot defined by the index
        this.inventory.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());

        //Resets the alloying time in case you put some different items in the input slots
        if (Slots.INPUT_SLOT.contains(index) && isEmptyOrNotEqualToTheSlotStack)
        {
            this.alloyingTime = 0;
            this.markDirty();
        }
    }

    /**
     * @return the name of the Tile Entity
     */
    @Override
    @Nonnull
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.alloyer";
    }

    /**
     * @return true if the tile entity has a custom name
     */
    @Override
    public boolean hasCustomName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    /**
     * If the name is not custom it translates the name
     * If it is, returns directly the name
     *
     * @return the name to be displayed
     */
    @Override
    @Nonnull
    public ITextComponent getDisplayName()
    {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    /**
     * Reads data from the NBT Tag
     */
    @Override
    public void readFromNBT(@Nonnull NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.inventory);
        this.burnTime = compound.getShort("burn_time");
        this.alloyingTime = compound.getShort("alloying_time");
        this.totalBurnTime = compound.getShort("total_burn_time");

        this.isPoweredByThermite = compound.getBoolean("powered_by_thermite");

        if (compound.hasKey("custom_name", 8))
            this.setCustomName(compound.getString("custom_name"));
    }

    /**
     * Writes data to the NBT Tag
     */
    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("burn_time", (short) this.burnTime);
        compound.setShort("alloying_time", (short) this.alloyingTime);
        compound.setShort("total_burn_time", (short) this.totalBurnTime);

        compound.setBoolean("powered_by_thermite", isPoweredByThermite);

        ItemStackHelper.saveAllItems(compound, this.inventory);

        if (this.hasCustomName())
            compound.setString("custom_name", this.customName);

        return compound;
    }

    /**
     * @return Returns the Stack Limit as an int
     */
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * @return true if the fuel is burning
     */
    public boolean isBurning()
    {
        return this.burnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(TileEntityAlloyer te)
    {
        return te.getField(0) > 0;
    }

    //---------------------------------------------------------------------------------------
    //Handle Client/Server Syncing
    //sync process when notifyBlockUpdate is called
    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound compound = new NBTTagCompound();
        writeToNBT(compound);
        return new SPacketUpdateTileEntity(this.getPos(), 0, compound);
    }

    @Override
    public void onDataPacket(@Nonnull NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.getNbtCompound());
    }

    public void sendUpdate()
    {
        if (world != null)
        {
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 8);
            markDirty();
        }
    }
    //---------------------------------------------------------------------------------------

    @Override
    public void update()
    {
        boolean oldBurningState = isBurning();

        if (isBurning())
        {
            this.burnTime--;

            if (canAlloy())
            {
                //When the alloyer is powered by thermite
                if (isPoweredByThermite)
                {
                    //if alloying time is higher than the total then return total alloying time otherwise increase by 2
                    this.alloyingTime = Math.min(this.alloyingTime += 2, TOTAL_ALLOYING_TIME);
                }
                else
                {
                    alloyingTime++;
                }

                if (alloyingTime >= TOTAL_ALLOYING_TIME)
                {
                    alloyItem();
                    this.alloyingTime = 0;
                    markDirty();
                }
            }
        }
        else
        {
            ItemStack fuelStack = inventory.get(Slots.FUEL_SLOT.slots[0]);
            Item fuelItem = fuelStack.getItem();

            if (!fuelStack.isEmpty() && canAlloy())
            {
                this.isPoweredByThermite = fuelStack.getItem() == ModItems.dustThermite;
                this.burnTime = getItemBurnTime(fuelStack);
                this.totalBurnTime = this.burnTime;
                fuelStack.shrink(1);

                //In case the fuel has a container item set it in the fuel slot after shrinking the fuel (i.e. lava bucket)
                if (fuelStack.isEmpty())
                {
                    ItemStack containerItem = fuelItem.getContainerItem(fuelStack);
                    inventory.set(Slots.FUEL_SLOT.slots[0], containerItem);
                }

                markDirty();
            }

            if (this.alloyingTime > 0)
            {
                //if alloying time is negative then alloying time should be zero, bitch
                this.alloyingTime = Math.max(this.alloyingTime - 3, 0);
            }
        }

        if (oldBurningState != isBurning())
        {
            BlockAlloyer.setState(isBurning(), this.world, this.pos);
        }
    }


    /**
     * Called Every tick when this block is powered by an External Heater from Immersive Engineering
     */
    @Override
    public int doHeatTick(int energyAvailable, boolean redstone)
    {
        if (canAlloy() || redstone)
        {
            totalBurnTime = 111;

            burnTime = Math.min(burnTime += 2, totalBurnTime);

            if (canAlloy() && totalBurnTime == burnTime && alloyingTime < TOTAL_ALLOYING_TIME)
                alloyingTime++;

            if (!world.getBlockState(pos).getValue(BlockAlloyer.BURNING))
            {
                BlockAlloyer.setState(true, world, pos);
            }

            sendUpdate();
        }

        if (!isBurning())
            return 0;
        else if (burnTime < totalBurnTime)
            return ExternalHeaterHandler.defaultFurnaceEnergyCost;
        else
            return ExternalHeaterHandler.defaultFurnaceSpeedupCost + ExternalHeaterHandler.defaultFurnaceEnergyCost;
    }


    /**
     * @return true if the recipe is valid and the alloy can be made
     */
    private boolean canAlloy()
    {
        ItemStack input1 = this.inventory.get(Slots.INPUT_SLOT.slots[0]);
        ItemStack input2 = this.inventory.get(Slots.INPUT_SLOT.slots[1]);

        ItemStack result = AlloyerRecipes.getInstance().getAlloyResult(input1, input2);

        boolean areInputCountsLessThanRecipeCounts =
                input1.getCount() < AlloyerRecipes.getInstance().getItemQuantity(result, input1)
                        || input2.getCount() < AlloyerRecipes.getInstance().getItemQuantity(result, input2);

        //Can't alloy if the recipe result is empty or the input counts are less than recipe counts
        if (result.isEmpty() || areInputCountsLessThanRecipeCounts)
        {
            return false;
        }
        else
        {
            ItemStack output = this.inventory.get(Slots.OUTPUT_SLOT.slots[0]);

            //Can alloy if the output slot is empty
            //Can't alloy if the output items or itemStacks are different
            if (output.isEmpty())
                return true;
            else if (!output.isItemEqual(result) || !ItemStack.areItemStackTagsEqual(output, result))
                return false;

            //Can alloy if the output is the same itemStack and the sum of the old output and the recipe results don't exceed the stack limit
            int resultCount = output.getCount() + result.getCount();
            return resultCount <= getInventoryStackLimit() && resultCount <= output.getMaxStackSize();
        }
    }

    /**
     * Handles input shrinking and output growing depending on the Alloying recipe
     */
    private void alloyItem()
    {
        if (this.canAlloy())
        {
            ItemStack input1 = this.inventory.get(0);
            ItemStack input2 = this.inventory.get(1);

            ItemStack result = AlloyerRecipes.getInstance().getAlloyResult(input1, input2);
            ItemStack output = this.inventory.get(3);

            if (output.isEmpty())
                this.inventory.set(3, result.copy());
            else if (output.isItemEqual(result) && ItemStack.areItemStackTagsEqual(output, result))
                output.grow(result.getCount());

            input1.shrink(AlloyerRecipes.getInstance().getItemQuantity(result, input1));
            input2.shrink(AlloyerRecipes.getInstance().getItemQuantity(result, input2));
        }
    }

    /**
     * used to block interactions with the block if the player is really far from it (avoids synchronization bugs)
     *
     * @return whether the player can interact with the inventory
     */
    @Override
    public boolean isUsableByPlayer(@Nonnull EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
            return false;
        else
            return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    /**
     * Server side stuff when you open the GUI
     */
    @Override
    public void openInventory(@Nonnull EntityPlayer player)
    {
    }

    /**
     * Server side stuff when you close the GUI
     */
    @Override
    public void closeInventory(@Nonnull EntityPlayer player)
    {
    }

    /**
     * Only called when stacks are inserted using automated methods (i.e. hopper)
     *
     * @param index the index of the slot this item is being put into
     * @param stack the stack that is put into the slot
     * @return Whether this item can be inserted in the slot
     */
    @Override
    public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack)
    {
        //if the slot is the output we don't want to accept any itemstack
        if (Slots.OUTPUT_SLOT.contains(index))
        {
            return false;
        }
        else if (Slots.FUEL_SLOT.contains(index))
        {
            ItemStack fuelStack = inventory.get(index);
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && fuelStack.getItem() != Items.BUCKET;
        }
        else if (Slots.INPUT_SLOT.contains(index))
        {
            if (AlloyerRecipes.getInstance().isAlloyMetal(stack))
            {
                ItemStack input1 = inventory.get(0);
                ItemStack input2 = inventory.get(1);

                if (input1.isEmpty())
                {
                    if (input2.isEmpty())
                        return true;
                    else
                    {
                        if (index == 1)
                            return input2.isItemEqual(stack) || ItemStack.areItemsEqual(input2, stack);
                        else
                            return !AlloyerRecipes.getInstance().getAlloyResult(input2, stack).isEmpty();
                    }
                }
                else if (input2.isEmpty())
                {
                    if (index == 0)
                        return input1.isItemEqual(stack) || ItemStack.areItemsEqual(input1, stack);
                    else
                        return !AlloyerRecipes.getInstance().getAlloyResult(input1, stack).isEmpty();
                }
                else
                {
                    ItemStack s = inventory.get(index);
                    return (s.isItemEqual(stack) || ItemStack.areItemsEqual(s, stack)) && s.getMaxStackSize() > s.getCount();
                }
            }
            else
                return false;
        }
        else
            return false;
    }

    @Nonnull
    @Override
    public String getGuiID()
    {
        return "minecraft:alloyer";
    }

    @Nonnull
    @Override
    public Container createContainer(@Nonnull InventoryPlayer inventory, @Nonnull EntityPlayer player)
    {
        return new ContainerAlloyer(inventory, this);
    }

    @Override
    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.burnTime;
            case 1:
                return this.totalBurnTime;
            case 2:
                return this.alloyingTime;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.burnTime = value;
                break;
            case 1:
                this.totalBurnTime = value;
                break;
            case 2:
                this.alloyingTime = value;
                break;
            default:
                break;
        }
    }

    @Override
    public int getFieldCount()
    {
        return 3;
    }

    /**
     * Clears the inventory
     */
    @Override
    public void clear()
    {
        this.inventory.clear();
    }

    @Override
    @Nonnull
    public int[] getSlotsForFace(@Nonnull EnumFacing side)
    {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    @Override
    public boolean canInsertItem(int index, @Nonnull ItemStack itemStackIn, @Nonnull EnumFacing direction)
    {
        return isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, @Nonnull ItemStack stack, @Nonnull EnumFacing direction)
    {
        return Slots.OUTPUT_SLOT.contains(index);
    }

    public enum Slots {

        INPUT_SLOT(0, 1), OUTPUT_SLOT(3), FUEL_SLOT(2);

        private final int[] slots;

        Slots(int... slots)
        {
            this.slots = slots;
        }

        public int[] slots()
        {
            return slots;
        }

        public boolean contains(int i)
        {
            return ArrayUtils.contains(slots, i);
        }
    }

}

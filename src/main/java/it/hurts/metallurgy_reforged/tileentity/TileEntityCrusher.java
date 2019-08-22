/*
 * -------------------------------------------------------------------------------------------------------
 * Class: TileEntityCrusher
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

//package it.hurts.metallurgy_reforged.tileentity;
//
//import it.hurts.metallurgy_reforged.block.BlockCrusher;
//import it.hurts.metallurgy_reforged.container.ContainerCrusher;
//import it.hurts.metallurgy_reforged.recipe.BlockCrusherRecipes;
//import net.minecraft.block.Block;
//import net.minecraft.block.material.Material;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.InventoryPlayer;
//import net.minecraft.init.Blocks;
//import net.minecraft.init.Items;
//import net.minecraft.inventory.Container;
//import net.minecraft.inventory.ISidedInventory;
//import net.minecraft.inventory.ItemStackHelper;
//import net.minecraft.inventory.SlotFurnaceFuel;
//import net.minecraft.item.*;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.tileentity.TileEntityFurnace;
//import net.minecraft.tileentity.TileEntityLockable;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.ITickable;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.datafix.DataFixer;
//import net.minecraft.util.datafix.FixTypes;
//import net.minecraft.util.datafix.walkers.ItemStackDataLists;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.TextComponentString;
//import net.minecraft.util.text.TextComponentTranslation;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.event.ForgeEventFactory;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.IItemHandler;
//import net.minecraftforge.items.wrapper.SidedInvWrapper;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//import java.util.Arrays;
//import java.util.Set;
//import java.util.stream.Collectors;
//
////VOGLIO MORIRE EDITION
//
////ticking tile entity
//public class TileEntityCrusher extends TileEntityLockable implements ITickable, ISidedInventory {
//    private static final int[] slotsTop = SlotEnum.INPUT_SLOT.slots();
//    private static final int[] slotsBottom = SlotEnum.OUTPUT_SLOT.slots();
//    private static final int[] slotsSides = SlotEnum.FUEL_SLOT.slots();
//    
//    private IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
//    private IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
//    private IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);
//    private NonNullList<ItemStack> inventory = NonNullList.withSize(5, ItemStack.EMPTY);
//    
//    private String customName;
//    
//    private static final int CRUSHING_TIME = 140;
//    private int burnTime;
//    private int currentBurnTime;
//    private int crushTime;
//    private int totalCrushTime = 200;
//
//
//    public static void registerFixesFurnace(DataFixer fixer) {
//        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityFurnace.class, "Items"));
//    }
//
//    private static int getItemBurnTime(ItemStack fuel) {
//        if (fuel.isEmpty())
//            return 0;
//        else {
//            Item item = fuel.getItem();
//
//            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
//                Block block = Block.getBlockFromItem(item);
//
//                if (block == Blocks.WOODEN_SLAB)
//                    return 150;
//                if (block.getDefaultState().getMaterial() == Material.WOOD)
//                    return 300;
//                if (block == Blocks.COAL_BLOCK)
//                    return 16000;
//            }
//
//            if (item instanceof ItemTool && "WOOD".equals(((ItemTool) item).getToolMaterialName()))
//                return 200;
//            if (item instanceof ItemSword && "WOOD".equals(((ItemSword) item).getToolMaterialName()))
//                return 200;
//            if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe) item).getMaterialName()))
//                return 200;
//            if (item == Items.STICK)
//                return 100;
//            if (item == Items.COAL)
//                return 1600;
//            if (item == Items.LAVA_BUCKET)
//                return 20000;
//            if (item == Item.getItemFromBlock(Blocks.SAPLING))
//                return 100;
//            if (item == Items.BLAZE_ROD)
//                return 2400;
//
//            return ForgeEventFactory.getItemBurnTime(fuel);
//        }
//    }
//
//    public static boolean isItemFuel(ItemStack fuel) {
//        return getItemBurnTime(fuel) > 0;
//    }
//
//    @Override
//    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
//        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
//        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
//            if (facing == EnumFacing.DOWN)
//                return (T) handlerBottom;
//            else if (facing == EnumFacing.UP)
//                return (T) handlerTop;
//            else
//                return (T) handlerSide;
//        return super.getCapability(capability, facing);
//    }
//
//    @Override
//    public int getSizeInventory() {
//        return this.inventory.size();
//    }
//
//    @Override
//    public boolean isEmpty() {
//        for (ItemStack stack : this.inventory) {
//            if (!stack.isEmpty()) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    @Nonnull
//    public ItemStack getStackInSlot(int index) {
//        return this.inventory.get(index);
//    }
//
//    @Override
//    @Nonnull
//    public ItemStack decrStackSize(int index, int count) {
//        return ItemStackHelper.getAndSplit(this.inventory, index, count);
//    }
//
//    @Override
//    @Nonnull
//    public ItemStack removeStackFromSlot(int index) {
//        return ItemStackHelper.getAndRemove(this.inventory, index);
//    }
//
//    @Override
//    public void setInventorySlotContents(int index, @Nonnull ItemStack stack) {
//        ItemStack itemstack = this.inventory.get(index);
//        boolean flag = stack.isEmpty() || !stack.isItemEqual(itemstack) || !ItemStack.areItemStackTagsEqual(stack, itemstack);
//        this.inventory.set(index, stack);
//
//        if (stack.getCount() > this.getInventoryStackLimit()) {
//            stack.setCount(this.getInventoryStackLimit());
//        }
//
//        if (SlotEnum.INPUT_SLOT.contains(index) && flag) {
//            this.totalCrushTime = this.getCrushingTime(stack);
//            this.crushTime = 0;
//            this.markDirty();
//        }
//    }
//
//    @Override
//    @Nonnull
//    public String getName() {
//        return this.hasCustomName() ? this.customName : "container.crusher";
//    }
//
//    @Override
//    public boolean hasCustomName() {
//        return this.customName != null && !this.customName.isEmpty();
//    }
//
//    public void setCustomName(String customName) {
//        this.customName = customName;
//    }
//
//    @Override
//    @Nonnull
//    public ITextComponent getDisplayName() {
//        return this.hasCustomName() ? new TextComponentString(this.getName())
//                : new TextComponentTranslation(this.getName());
//    }
//
//    @Override
//    public void readFromNBT(NBTTagCompound compound) {
//        super.readFromNBT(compound);
//        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
//        ItemStackHelper.loadAllItems(compound, this.inventory);
//        this.burnTime = compound.getInteger("burn_time");
//        this.crushTime = compound.getInteger("crush_time");
//        this.totalCrushTime = compound.getInteger("total_crush_time");
//        this.currentBurnTime = compound.getInteger("current_burn_time");
//
//        if (compound.hasKey("CustomName", 8))
//            this.setCustomName(compound.getString("CustomName"));
//
//    }
//
//    @Override
//    @Nonnull
//    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
//        super.writeToNBT(compound);
//        compound.setInteger("burn_time", (short) this.burnTime);
//        compound.setInteger("current_burn_time", (short) this.currentBurnTime);
//        compound.setInteger("crush_time", (short) this.crushTime);
//        compound.setInteger("total_crush_time", (short) this.totalCrushTime);
//        ItemStackHelper.saveAllItems(compound, this.inventory);
//
//        if (this.hasCustomName())
//            compound.setString("custom_name", this.customName);
//
//        return compound;
//
//    }
//
//    @Override
//    public int getInventoryStackLimit() {
//        return 64;
//    }
//
//    public boolean isBurning() {
//        return this.burnTime > 0;
//    }
//
//    @Override
//    public void update() {
//        boolean flag = this.isBurning();
//        boolean flag1 = false;
//
//        //Fuel Usage
//        if (this.isBurning()) {
//            --this.burnTime;
//        }
//
//        if (!this.world.isRemote) {
//
//            ItemStack input = inventory.get(0);
//            ItemStack fuel = this.inventory.get(1);
//
//            if (this.isBurning() || !fuel.isEmpty() && !input.isEmpty()) {
//                if (!this.isBurning() && this.canCrush()) {
//                    this.burnTime = getItemBurnTime(fuel);
//                    this.currentBurnTime = burnTime;
//
//                    if (this.isBurning()) {
//                        flag1 = true;
//
//                        if (!fuel.isEmpty()) {
//                            Item item = fuel.getItem();
//                            fuel.shrink(1);
//
//                            if (fuel.isEmpty()) {
//                                ItemStack item1 = item.getContainerItem(fuel);
//                                this.inventory.set(1, item1);
//                            }
//                        }
//                    }
//
//                }
//
//                if (this.isBurning() && this.canCrush()) {
//                    ++crushTime;
//
//                    if (crushTime == totalCrushTime) {
//                        crushTime = 0;
//                        totalCrushTime = this.getCrushingTime(this.inventory.get(0));
//                        this.crushItem();
//                        flag1 = true;
//                    }
//                } else {
//                    //When the crusher is burning in idle
//                    this.crushTime = 0;
//                }
//            } else if (!this.isBurning() && this.crushTime > 0) {
//                //When the fuel is not enough
//                this.crushTime = MathHelper.clamp(this.crushTime - 2, 0, this.totalCrushTime);
//            }
//
//            if (flag != this.isBurning()) {
//                flag1 = true;
//                BlockCrusher.setState(this.isBurning(), this.world, this.pos);
//            }
//        }
//
//        if (flag1) {
//            this.markDirty();
//        }
//
//    }
//
//    //IL PROBLEMA è QUI [#23]
//    private boolean canCrush() {
//        if ((this.inventory.get(0)).isEmpty())
//            return false;
//        else {
//            ItemStack result = BlockCrusherRecipes.getInstance().getCrushingResult(this.inventory.get(0));
//            if (result.isEmpty())
//                return false;
//            else {
//                ItemStack output = this.inventory.get(2);
//                ItemStack output1 = this.inventory.get(3);
//                ItemStack output2 = this.inventory.get(4);
//                int limit = output.getCount() + result.getCount();
//                int limit1 = output1.getCount() + result.getCount();
//                int limit2 = output2.getCount() + result.getCount();
//
//                if (output.isEmpty() || output1.isEmpty() || output2.isEmpty())
//                    return true;
//                else if (!output.isItemEqual(result) && !output1.isItemEqual(result) && !output2.isItemEqual(result))
//                    return false;
////                else if (!output1.isItemEqual(result))
////                    return false;
////                else if (!output2.isItemEqual(result))
////                    return false;
//                else if (limit <= this.getInventoryStackLimit() && limit <= output.getMaxStackSize())
//                    return true;
//                else if (limit1 <= this.getInventoryStackLimit() && limit1 <= output1.getMaxStackSize())
//                    return true;
//                else if (limit2 <= this.getInventoryStackLimit() && limit2 <= output.getMaxStackSize())
//                    return true;
//                else
//                    return limit <= 64 && limit <= output.getMaxStackSize();
//
//            }
//        }
//    }
//
//    //  Called the instant when the item is ready to get crushed and to output the result
//    private void crushItem() {
//        if (this.canCrush()) {
//            ItemStack input = this.inventory.get(0);
//            ItemStack recipeResult = BlockCrusherRecipes.getInstance().getCrushingResult(input);
//            ItemStack output = this.inventory.get(2);
//            ItemStack output1 = this.inventory.get(3);
//            ItemStack output2 = this.inventory.get(4);
//
//            int limit = output.getCount() + recipeResult.getCount();
//            int limit1 = output1.getCount() + recipeResult.getCount();
//            int limit2 = output2.getCount() + recipeResult.getCount();
//
//            //TODO check if it's possible to improve this using switch or index
//            if (output.isEmpty())
//                this.inventory.set(2, recipeResult.copy());
//            else if (output.getItem() == recipeResult.getItem() && limit <= this.getInventoryStackLimit() && limit <= output.getMaxStackSize())
//                output.grow(recipeResult.getCount());
//
//            else if (output1.isEmpty())
//                this.inventory.set(3, recipeResult.copy());
//            else if (output1.getItem() == recipeResult.getItem() && limit1 <= this.getInventoryStackLimit() && limit1 <= output1.getMaxStackSize())
//                output1.grow(recipeResult.getCount());
//
//            else if (output2.isEmpty())
//                this.inventory.set(4, recipeResult.copy());
//            else if (output2.getItem() == recipeResult.getItem() && limit2 <= this.getInventoryStackLimit() && limit2 <= output.getMaxStackSize())
//                output2.grow(recipeResult.getCount());
//
//            if (input.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && input.getMetadata() == 1 && !this.inventory.get(1).isEmpty() && this.inventory.get(1).getItem() == Items.BUCKET)
//                this.inventory.set(1, new ItemStack(Items.WATER_BUCKET));
//
//            input.shrink(1);
//
//        }
//    }
//
//    private int getCrushingTime(@SuppressWarnings("unused") ItemStack stack) {
//        return CRUSHING_TIME;
//    }
//
//    @Override
//    public boolean isUsableByPlayer(@Nonnull EntityPlayer player) {
//        if (this.world.getTileEntity(this.pos) != this)
//            return false;
//        else
//            return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
//    }
//
//    @Override
//    public void openInventory(@Nonnull EntityPlayer player) {
//    }
//
//    @Override
//    public void closeInventory(@Nonnull EntityPlayer player) {
//    }
//
//    @Override
//    public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack) {
//        if (SlotEnum.OUTPUT_SLOT.contains(index))
//            return false;
//        else if (SlotEnum.FUEL_SLOT.contains(index)) {
//            ItemStack stack1 = inventory.get(index);
//            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && stack1.getItem() != Items.BUCKET;
//        } else if (SlotEnum.INPUT_SLOT.contains(index)) {
//            return !BlockCrusherRecipes.getInstance().getCrushingResult(stack).isEmpty();
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    @Nonnull
//    public String getGuiID() {
//        return "minecraft:crusher";
//    }
//
//    @Override
//    @Nonnull
//    public Container createContainer(@Nonnull InventoryPlayer inventory, @Nonnull EntityPlayer player) {
//        return new ContainerCrusher(inventory, this);
//    }
//
//    @Override
//    public int getField(int id) {
//        switch (id) {
//            case 0:
//                return this.burnTime;
//            case 1:
//                return this.currentBurnTime;
//            case 2:
//                return this.crushTime;
//            case 3:
//                return this.totalCrushTime;
//            default:
//                return 0;
//        }
//    }
//
//    /*
//     * serve per trasferire le informazioni di a che punto è il processo, quanto carburante ha, quanto tempo ha bisogno un processo
//     * private tile entity variables getter [process and combustion info]
//     * getField(getFieldCount)
//     * setField(getField, valore)
//     */
//
//    @Override
//    public void setField(int id, int value) {
//        switch (id) {
//            case 0:
//                this.burnTime = value;
//                break;
//            case 1:
//                this.currentBurnTime = value;
//                break;
//            case 2:
//                this.crushTime = value;
//                break;
//            case 3:
//                this.totalCrushTime = value;
//        }
//    }
//
//    @Override
//    public int getFieldCount() {
//        return 4;
//    }
//
//    @Override
//    public void clear() {
//        this.inventory.clear();
//    }
//
//    @Override
//    @Nonnull
//    public int[] getSlotsForFace(@Nonnull EnumFacing side) {
//        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
//    }
//
//    @Override
//    public boolean canInsertItem(int index, @Nonnull ItemStack itemStackIn, @Nonnull EnumFacing direction) {
//        return isItemValidForSlot(index, itemStackIn);
//    }
//
//    @Override
//    public boolean canExtractItem(int index, @Nonnull ItemStack stack, @Nonnull EnumFacing direction) {
//        return SlotEnum.OUTPUT_SLOT.contains(index);
//    }
//
//    public enum SlotEnum {// 	enumerate the slots
//        INPUT_SLOT(0), OUTPUT_SLOT(2, 3, 4), FUEL_SLOT(1);
//
//        private final int[] slots;
//        private Set<Integer> slotSet;
//
//        SlotEnum(int... slots) {
//            this.slots = slots;
//            slotSet = Arrays.stream(slots).boxed().collect(Collectors.toSet());
//        }
//
//        public int[] slots() {
//            return Arrays.copyOf(slots, slots.length);
//        }
//
//        public boolean contains(int i) {
//            return slotSet.contains(i);
//        }
//    }
//
////provate pure a pensare di essere assolti, siete lo stesso coinvolti
//
//}
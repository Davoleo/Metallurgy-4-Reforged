/*
 * -------------------------------------------------------------------------------------------------------
 * Class: TileEntityCrusher
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.tileentity;

import it.hurts.metallurgy_reforged.block.BlockCrusher;
import it.hurts.metallurgy_reforged.container.ContainerCrusher;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.recipe.CrusherRecipes;
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
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

import static net.minecraft.tileentity.TileEntityFurnace.getItemBurnTime;

public class TileEntityCrusher extends TileEntityLockable implements ITickable, ISidedInventory {

	private static final int[] slotsTop = Slots.INPUT_SLOT.slots();
	private static final int[] slotsBottom = Slots.OUTPUT_SLOT.slots();
	private static final int[] slotsSides = Slots.FUEL_SLOT.slots();
	private IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
	private IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
	private IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);
	private NonNullList<ItemStack> inventory = NonNullList.withSize(5, ItemStack.EMPTY);

	private String customName;

	private int burnTime;
	private int totalBurnTime;
	private int crushTime;
	public static final int TOTAL_CRUSHING_TIME = 140;

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
		if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
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
		{
			if (!stack.isEmpty())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	@Nonnull
	public ItemStack getStackInSlot(int index)
	{
		return this.inventory.get(index);
	}

	@Override
	@Nonnull
	public ItemStack decrStackSize(int index, int count)
	{
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	@Nonnull
	public ItemStack removeStackFromSlot(int index)
	{
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, @Nonnull ItemStack stack)
	{
		ItemStack itemstack = this.inventory.get(index);
		boolean isEmptyOrNotEqualToTheSlotStack = stack.isEmpty() || !stack.isItemEqual(itemstack) || !ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);

		if (stack.getCount() > this.getInventoryStackLimit())
		{
			stack.setCount(this.getInventoryStackLimit());
		}

		if (Slots.INPUT_SLOT.contains(index) && isEmptyOrNotEqualToTheSlotStack)
		{
			this.crushTime = 0;
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
		return this.hasCustomName() ? this.customName : "container.crusher";
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
		return this.hasCustomName() ? new TextComponentString(this.getName())
				: new TextComponentTranslation(this.getName());
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
		this.burnTime = compound.getInteger("burn_time");
		this.crushTime = compound.getInteger("crush_time");
		this.totalBurnTime = compound.getInteger("total_burn_time");

		this.isPoweredByThermite = compound.getBoolean("powered_by_thermite");

		if (compound.hasKey("custom_name", 8))
			this.setCustomName(compound.getString("custom_name"));

	}

	/**
	 * Writes data to the NBT Tag
	 */
	@Override
	@Nonnull
	public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("burn_time", (short) this.burnTime);
		compound.setInteger("total_burn_time", (short) this.totalBurnTime);
		compound.setInteger("crush_time", (short) this.crushTime);

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

	@Override
	public void update()
	{
		boolean oldBurningState = isBurning();

		if (isBurning())
		{
			this.burnTime--;

			if (canCrush())
			{

				if (!isPoweredByThermite)
				{
					this.crushTime++;
				}
				else
				{
					//if crushing time is higher than the total then return total crushing time otherwise increase by 2
					this.crushTime = Math.min(this.crushTime += 2, TOTAL_CRUSHING_TIME);
				}

				if (crushTime >= TOTAL_CRUSHING_TIME)
				{
					crushItem();
					this.crushTime = 0;
					markDirty();
				}
			}
		}
		else
		{
			ItemStack fuelStack = inventory.get(TileEntityCrusher.Slots.FUEL_SLOT.slots[0]);
			Item fuelItem = fuelStack.getItem();

			if (!fuelStack.isEmpty() && canCrush())
			{
				this.isPoweredByThermite = fuelStack.getItem() == ModItems.dustThermite;
				this.burnTime = getItemBurnTime(fuelStack);
				this.totalBurnTime = this.burnTime;
				fuelStack.shrink(1);

				//In case the fuel has a container item set it in the fuel slot after shrinking the fuel (i.e. lava bucket)
				if (fuelStack.isEmpty())
				{
					ItemStack containerItem = fuelItem.getContainerItem(fuelStack);
					inventory.set(TileEntityCrusher.Slots.FUEL_SLOT.slots[0], containerItem);
				}

				markDirty();
			}

			if (this.crushTime > 0)
			{
				//if crushing time is negative then crushing time should be zero, bitch
				this.crushTime = Math.max(this.crushTime - 2, 0);
			}
		}

		if (oldBurningState != isBurning())
		{
			BlockCrusher.setState(isBurning(), this.world, this.pos);
		}

	}

	/**
	 * @return true if the recipe is valid and the item can be crushed
	 */
	private boolean canCrush()
	{
		ItemStack input = this.inventory.get(Slots.INPUT_SLOT.slots[0]);

		if (input.isEmpty())
			return false;
		else
		{
			ItemStack result = CrusherRecipes.getInstance().getCrushingResult(input);
			if (result.isEmpty())
				return false;
			else
			{
				ItemStack output = this.inventory.get(Slots.OUTPUT_SLOT.slots[0]);
				ItemStack output1 = this.inventory.get(Slots.OUTPUT_SLOT.slots[1]);
				ItemStack output2 = this.inventory.get(Slots.OUTPUT_SLOT.slots[2]);

				int totalAmount = output.getCount() + result.getCount();
				int totalAmount1 = output1.getCount() + result.getCount();
				int totalAmount2 = output2.getCount() + result.getCount();

				//can crush if one of the various outputs is empty
				if (output.isEmpty() || output1.isEmpty() || output2.isEmpty())
					return true;

				//Can't crush if all of the output slots are filled with an item that is different from the recipe result
				if (!output.isItemEqual(result) && !output1.isItemEqual(result) && !output2.isItemEqual(result))
					return false;
					//if the previous condition is not met check whether the sum of the recipe result count and the slot amount doesn't overcome the stack limit
				else if (totalAmount <= this.getInventoryStackLimit() && totalAmount <= output.getMaxStackSize())
					return true;
				else if (totalAmount1 <= this.getInventoryStackLimit() && totalAmount1 <= output1.getMaxStackSize())
					return true;
				else
					return (totalAmount2 <= this.getInventoryStackLimit() && totalAmount2 <= output2.getMaxStackSize());

			}
		}
	}

	/**
	 * Handles input shrinking and output growing depending on the Crushing recipe
	 */
	private void crushItem()
	{
		if (this.canCrush())
		{
			ItemStack input = this.inventory.get(Slots.INPUT_SLOT.slots[0]);
			ItemStack recipeResult = CrusherRecipes.getInstance().getCrushingResult(input);
			ItemStack output = this.inventory.get(Slots.OUTPUT_SLOT.slots[0]);
			ItemStack output1 = this.inventory.get(Slots.OUTPUT_SLOT.slots[1]);
			ItemStack output2 = this.inventory.get(Slots.OUTPUT_SLOT.slots[2]);

			int limit = output.getCount() + recipeResult.getCount();
			int limit1 = output1.getCount() + recipeResult.getCount();
			int limit2 = output2.getCount() + recipeResult.getCount();

			if (output.isEmpty())
				this.inventory.set(Slots.OUTPUT_SLOT.slots[0], recipeResult.copy());
			else if (ItemStack.areItemsEqual(output, recipeResult) && limit <= this.getInventoryStackLimit() && limit <= output.getMaxStackSize())
				output.grow(recipeResult.getCount());

			else if (output1.isEmpty())
				this.inventory.set(Slots.OUTPUT_SLOT.slots[1], recipeResult.copy());
			else if (ItemStack.areItemsEqual(output1, recipeResult) && limit1 <= this.getInventoryStackLimit() && limit1 <= output1.getMaxStackSize())
				output1.grow(recipeResult.getCount());

			else if (output2.isEmpty())
				this.inventory.set(Slots.OUTPUT_SLOT.slots[2], recipeResult.copy());
			else if (ItemStack.areItemsEqual(output2, recipeResult) && limit2 <= this.getInventoryStackLimit() && limit2 <= output2.getMaxStackSize())
				output2.grow(recipeResult.getCount());

			input.shrink(1);
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
	{}

	/**
	 * Server side stuff when you close the GUI
	 */
	@Override
	public void closeInventory(@Nonnull EntityPlayer player)
	{}

	/**
	 * Only called when stacks are inserted using automated methods (i.e. hopper)
	 *
	 * @param index the index of the slot this item is being put into
	 * @param stack the stack that is put into the slot
	 *
	 * @return Whether this item can be inserted in the slot
	 */
	@Override
	public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack)
	{
		if (Slots.OUTPUT_SLOT.contains(index))
			return false;
		else if (Slots.FUEL_SLOT.contains(index))
		{
			ItemStack fuelStack = inventory.get(index);
			return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && fuelStack.getItem() != Items.BUCKET;
		}
		else if (Slots.INPUT_SLOT.contains(index))
		{
			return !CrusherRecipes.getInstance().getCrushingResult(stack).isEmpty();
		}
		else
		{
			return false;
		}
	}

	@Override
	@Nonnull
	public String getGuiID()
	{
		return "minecraft:crusher";
	}

	@Override
	@Nonnull
	public Container createContainer(@Nonnull InventoryPlayer inventory, @Nonnull EntityPlayer player)
	{
		return new ContainerCrusher(inventory, this);
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
				return this.crushTime;
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
				this.crushTime = value;
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
		INPUT_SLOT(0), OUTPUT_SLOT(2, 3, 4), FUEL_SLOT(1);

		private final int[] slots;

		Slots(int... slots)
		{
			this.slots = slots;
		}

		public int[] slots()
		{
			return Arrays.copyOf(slots, slots.length);
		}

		public boolean contains(int i)
		{
			return ArrayUtils.contains(slots, i);
		}
	}

	//provate pure a pensare di essere assolti, siete lo stesso coinvolti
}